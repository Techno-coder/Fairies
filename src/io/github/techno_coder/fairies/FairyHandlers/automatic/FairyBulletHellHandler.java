package io.github.techno_coder.fairies.FairyHandlers.automatic;

import io.github.techno_coder.fairies.Fairy;
import io.github.techno_coder.fairies.FairyHandlers.IFairyHandler;

public class FairyBulletHellHandler implements IFairyHandler {

    static boolean hell = false;
    int amount = 0;

    @Override
    public void runHandler(Fairy fairy, String command) {
        if (amount > 500) {
            hell = !hell;
            fairy.rotate(180);
            amount = -1000000;
        }

        amount++;

        if (hell) {
            for (int i = 0; i < fairy.getEnergy() / 50; i++) {
                fairy.cast(50, 0);
            }
            return;
        }

        fairy.move(fairy.getEnergy());

    }
}
