package no.ntnu.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import no.ntnu.Message;

public class Server {

    public static final int PORT_NUMBER = 1250;
    private final ServerLogic logic;

    boolean isRunning;
    private final List<ClientHandler> clients = new ArrayList<>();

    public Server(ServerLogic serverLogic) {
        this.logic = serverLogic;
    }

    public void startServer() {
        ServerSocket serverSocket = openListeningSocket();
        if (serverSocket != null) {
            isRunning = true;
            while (isRunning) {
                ClientHandler clientHandler = acceptNextClientConnection(serverSocket);
                if (clientHandler != null) {
                    clients.add(clientHandler);
                    clientHandler.start();
                }
            }
        }
    }

    private ServerSocket openListeningSocket() {
        ServerSocket listeningSocket = null;
        try {
            listeningSocket = new ServerSocket(PORT_NUMBER);
        } catch (IOException e) {
            System.err.println("Could not open server socket: " + e.getMessage());
        }
        return listeningSocket;
    }

    private ClientHandler acceptNextClientConnection(ServerSocket listeningSocket) {
        ClientHandler clientHandler = null;
        try {
            Socket clientSocket = listeningSocket.accept();
            System.out.println("New client connected from " + clientSocket.getRemoteSocketAddress());
            clientHandler = new ClientHandler(clientSocket, this);
        } catch (IOException e) {
            System.err.println("Could not accept client connection: " + e.getMessage());
        }
        return clientHandler;
    }

    public ServerLogic getLogic() {
        return logic;
    }

    public void sendResponseToAllClients(Message message) {
        for (ClientHandler clientHandler : clients) {
          clientHandler.sendToClient(message);
        }
    }

    public void clientDisconnected(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

}
