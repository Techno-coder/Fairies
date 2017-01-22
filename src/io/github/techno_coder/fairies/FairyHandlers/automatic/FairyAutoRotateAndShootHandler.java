package io.github.techno_coder.fairies.FairyHandlers.automatic;

import io.github.techno_coder.fairies.Fairy;
import io.github.techno_coder.fairies.FairyHandlers.IFairyHandler;

public class FairyAutoRotateAndShootHandler implements IFairyHandler {
    @Override
    public void runHandler(Fairy fairy, String command) {
        fairy.rotate(5);
        fairy.move(5);
        if(fairy.getEnergy() > 50) {
            fairy.cast(50, 0);
        }
    }
}
