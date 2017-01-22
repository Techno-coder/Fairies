package io.github.techno_coder.fairies.FairyHandlers.manual;

import io.github.techno_coder.fairies.Fairy;
import io.github.techno_coder.fairies.FairyHandlers.IFairyHandler;

public class FairyManualBurstShootHandler implements IFairyHandler {

    @Override
    public void runHandler(Fairy fairy, String command) {
        if (command.equals("BS")) {
            for (int i = 0; i < fairy.getEnergy() / 50; ++i) {
                fairy.cast(50, 0);
            }
        }
    }
}
