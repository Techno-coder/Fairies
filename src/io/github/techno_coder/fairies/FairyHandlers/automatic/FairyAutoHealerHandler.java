package io.github.techno_coder.fairies.FairyHandlers.automatic;

import io.github.techno_coder.fairies.Fairy;
import io.github.techno_coder.fairies.FairyHandlers.IFairyHandler;

public class FairyAutoHealerHandler implements IFairyHandler {
    @Override
    public void runHandler(Fairy fairy, String command) {
        System.out.println("Healed : " + fairy.getEnergy() / 5);
        fairy.restore(fairy.getEnergy());
    }
}
