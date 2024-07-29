package server.server;
import server.client.ClientController;
import server.client.ClientGUI;
import server.client.ClientView;
import server.repository.Repositable;
import server.repository.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class ServerController implements ServerViev {

    Repository repository;


    private boolean work;
    private ServerWindow server;
    private ServerViev serverViev;
    public List<ClientController> clientGUIList;

    public ServerController() {
        clientGUIList = new ArrayList<>();
        repository = new Repository();

    }

    public void message(String text){
        if (!work){
            return;
        }
        appendLog(text);
        answerAll(text);
        repository.saveLogMessage(text);

    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

    private void answerAll(String text){
        for (ClientController clientController: clientGUIList){
            clientController.answerFromServer(text);
        }
    }


    private void appendLog(String text){
        server.getLog().append(text + "\n");
    }


    @Override
    public boolean connectUser(ClientController client) {
        if (!work){
            return false;
        }
        clientGUIList.add(client);
        return true;
    }

    @Override
    public void disconnectUser(ClientController client) {
        clientGUIList.remove(client);
        if (client != null){
            client.disconnectedFromServer();
        }
    }

    public void setServerView(ServerWindow serverWindow) {
        this.server = serverWindow;
    }

    public Repository getRepository() {
        return repository;
    }
}
