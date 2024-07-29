package server.server;

import server.client.ClientController;
import server.client.ClientGUI;
import server.client.ClientView;

public interface ServerViev {

    boolean connectUser(ClientController clientController);
    void disconnectUser(ClientController clientController);

}
