package no.ntnu.server;

public class ServerStarter {
    
    public static void main(String[] args) {
        ServerLogic logic = new ServerLogic();
        Server server = new Server(logic);
        server.startServer();
    }

}
