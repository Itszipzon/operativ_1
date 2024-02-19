package no.ntnu.message;

import no.ntnu.Command;
import no.ntnu.Message;
import no.ntnu.server.ServerLogic;

public class DivisionCommand extends Command{

    private final int factor1;
    private final int factor2;

    public DivisionCommand(int factor1, int factor2) {
        this.factor1 = factor1;
        this.factor2 = factor2;
    }

    @Override
    public Message execute(ServerLogic logic) {
        return new DivisionMessage(this.factor1, this.factor2, logic.add(this.factor1, this.factor2));
    }

    public int getFactor1() {
        return factor1;
    }

    public int getFactor2() {
        return factor2;
    }
    
}
