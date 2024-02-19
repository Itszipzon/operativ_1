package no.ntnu.client;

public class ClientRun {
    public static void main(String[] args) {
        TcpClient client = new TcpClient();
        client.start();
    }
}
