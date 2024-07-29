package server.server;

import server.client.ClientGUI;
import server.repository.Repositable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

//класс требуется разделить на GUI, controller и repository (смотри схему проекта)
public class ServerWindow extends JFrame{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private ServerController serverController;
    private JButton btnStart, btnStop;
    private JTextArea log;
    private static final String LOG_PATH = "src/server/log.txt";


    public ServerWindow() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);
    }

    public JTextArea getLog() {
        return log;
    }

    private void appendLog(String text) {
        log.append(text + "\n");
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverController.isWork()) {
                    appendLog("Сервер уже был запущен");
                } else {
                    serverController.setWork(true);
                    appendLog("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!serverController.isWork()) {
                    appendLog("Сервер уже был остановлен");
                } else {
                    serverController.setWork(false);
                    while (!serverController.clientGUIList.isEmpty()) {
                        serverController.disconnectUser(serverController.clientGUIList.
                                get(serverController.clientGUIList.size() - 1));
                    }
                    appendLog("Сервер остановлен!");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }


    public void showMessage(String message) {
        serverController.message(message);
    }
}