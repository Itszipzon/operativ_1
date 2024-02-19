package no.ntnu.message;

import no.ntnu.Message;

public class AdditionMessage implements Message {
    private final int factor1;
    private final int factor2;
    private final int result;

    public AdditionMessage(int factor1, int factor2, int result) {
        this.factor1 = factor1;
        this.factor2 = factor2;
        this.result = result;
    }

    public int getFactor1() {
        return factor1;
    }

    public int getFactor2() {
        return factor2;
    }

    public int getResult() {
        return this.result;
    }
}
