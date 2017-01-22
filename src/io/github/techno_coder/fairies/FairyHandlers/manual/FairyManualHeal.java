package io.github.techno_coder.fairies.FairyHandlers.manual;

import io.github.techno_coder.fairies.Fairy;
import io.github.techno_coder.fairies.FairyHandlers.IFairyHandler;

public class FairyManualHeal implements IFairyHandler {
    @Override
    public void runHandler(Fairy fairy, String command) {
        if (command.equals("HEAL")) {
            fairy.restore(fairy.getEnergy());
            System.out.println("Health is now : " + fairy.getHealth());
        }
    }
}
