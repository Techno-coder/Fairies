package io.github.techno_coder.fairies.FairyHandlers.manual;

import io.github.techno_coder.fairies.Fairy;
import io.github.techno_coder.fairies.FairyHandlers.IFairyHandler;

public class FairyInfoHandler implements IFairyHandler {
    @Override
    public void runHandler(Fairy fairy, String command) {
        if (command.equals("INFO")) {
            System.out.println("Energy : " + fairy.getEnergy() + " Health : " + fairy.getHealth());
        }
    }
}
