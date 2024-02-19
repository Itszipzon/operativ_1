package no.ntnu.client;

import static no.ntnu.server.Server.PORT_NUMBER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import no.ntnu.Command;
import no.ntnu.Message;
import no.ntnu.MessageSerializer;
import no.ntnu.message.AdditionMessage;
import no.ntnu.message.DivisionMessage;
import no.ntnu.message.MultiplicationMessage;
import no.ntnu.message.SubtractionMessage;

public class TcpClient {
    private static final String SERVER_HOST = "localhost";
    private Socket socket;
    private PrintWriter socketWriter;
    private BufferedReader socketReader;

    public boolean start() {
        boolean connected = false;
        try {
            socket = new Socket(SERVER_HOST, PORT_NUMBER);
            socketWriter = new PrintWriter(socket.getOutputStream(), true);
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connected;
    }

    public void startListening(MessageListener listener) {
        new Thread(() -> {
            Message message = null;
            do {
                try {
                    if (socketReader != null) {
                        String plainMessage = socketReader.readLine();
                        message = MessageSerializer.fromString(plainMessage);
                        handleIncomingMessage(message, listener);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (message != null);
        }).start();
    }

    private void handleIncomingMessage(Message message, MessageListener listener) {
        if (message instanceof AdditionMessage) {
            listener.messageReceived("Addition result: " + ((AdditionMessage) message).getResult());
        } else if (message instanceof MultiplicationMessage) {
            listener.messageReceived("Multiplication result: " + ((MultiplicationMessage) message).getResult());
        } else if (message instanceof DivisionMessage) {
            listener.messageReceived("Division result: " + ((DivisionMessage) message).getResult());
        } else if (message instanceof SubtractionMessage) {
            listener.messageReceived("Subtraction result: " + ((SubtractionMessage) message).getResult());
        }
    }

    public void stop() {
        if (socket != null) {
            try {
                socket.close();
                socket = null;
                socketReader = null;
                socketWriter = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean sendCommand(Command command) {
        boolean sent = false;
        if (socketWriter != null && socketReader != null) {
            try {
                socketWriter.println(MessageSerializer.toString(command));
                sent = true;
            } catch (Exception e) {
                System.err.println("Could not send a command: " + e.getMessage());
            }
        }
        return sent;
    }

}
