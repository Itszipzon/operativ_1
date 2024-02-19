package no.ntnu;

import no.ntnu.message.AdditionCommand;
import no.ntnu.message.AdditionMessage;
import no.ntnu.message.DivisionCommand;
import no.ntnu.message.DivisionMessage;
import no.ntnu.message.MultiplicationCommand;
import no.ntnu.message.MultiplicationMessage;
import no.ntnu.message.SubtractionCommand;
import no.ntnu.message.SubtractionMessage;

public class MessageSerializer {
    public static final String MULTIPLICATION_COMMAND = "M";
    public static final String DIVISION_COMMAND = "D";
    public static final String ADDITION_COMMAND = "A";
    public static final String SUBTRACTION_COMMAND = "S";

    private MessageSerializer() {

    }

    public static String toString(Message message) {
        String s = "";
        if (message instanceof MultiplicationMessage) {
            s = MULTIPLICATION_COMMAND + ((MultiplicationMessage) message).getFactor1() + " "
                    + ((MultiplicationMessage) message).getFactor2();
        }
        if (message instanceof AdditionMessage) {
            s = ADDITION_COMMAND + ((AdditionMessage) message).getFactor1() + " "
                    + ((AdditionMessage) message).getFactor2();
        }
        if (message instanceof DivisionMessage) {
            s = DIVISION_COMMAND + ((DivisionMessage) message).getFactor1() + " "
                    + ((DivisionMessage) message).getFactor2();
        }
        if (message instanceof SubtractionMessage) {
            s = SUBTRACTION_COMMAND + ((SubtractionMessage) message).getFactor1() + " "
                    + ((SubtractionMessage) message).getFactor2();
        }

        return s;
    }

    public static Message fromString(String s) {
        Message m = null;
        if (s != null) {
            if (s.startsWith(MULTIPLICATION_COMMAND)) {
                m = new MultiplicationCommand(factor(s, 0), factor(s, 1));
            } else if (s.startsWith(DIVISION_COMMAND)) {
                m = new DivisionCommand(factor(s, 0), factor(s, 1));
            } else if (s.startsWith(ADDITION_COMMAND)) {
                m = new AdditionCommand(factor(s, 0), factor(s, 1));
            } else if (s.startsWith(SUBTRACTION_COMMAND)) {
                m = new SubtractionCommand(factor(s, 0), factor(s, 1));
            }
        }

        return m;
    }

    private static int factor(String s, int n) {
        return Integer.parseInt(s.substring(1).split(" ")[n]);
    }
}
