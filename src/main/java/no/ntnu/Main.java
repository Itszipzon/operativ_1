package no.ntnu;

import no.ntnu.server.ServerLogic;

public class Main {
    public static void main(String[] args) {
        ServerLogic logic = new ServerLogic();
        System.out.println(logic.getResult("A1 2, S3 4, M5 6, D7 8").toString());
    }
}