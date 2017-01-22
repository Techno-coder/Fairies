package io.github.techno_coder.fairies.FairyHandlers.automatic;

import io.github.techno_coder.fairies.Fairy;
import io.github.techno_coder.fairies.FairyHandlers.IFairyHandler;

public class FairyStrafeShootSynchronizer implements IFairyHandler {

    FairySmartShooterHandler shooterHandler = new FairySmartShooterHandler();
    FairyAutoStrafeHandler strafeHandler =  new FairyAutoStrafeHandler();

    boolean turn = false;
    long lastMillis = System.currentTimeMillis();

    public FairyStrafeShootSynchronizer(Fairy fairy) {
        fairy.registerHandler(strafeHandler);
        fairy.registerHandler(shooterHandler);
    }

    @Override
    public void runHandler(Fairy fairy, String command) {
        fairy.setHandlerStatus(strafeHandler, turn);
        fairy.setHandlerStatus(shooterHandler, !turn);
        if(turn) {
            if (System.currentTimeMillis() > lastMillis + 5000) {
                turn = !turn;
                lastMillis = System.currentTimeMillis();
            }
        } else {
            if (System.currentTimeMillis() > lastMillis + 5000) {
                turn = !turn;
                lastMillis = System.currentTimeMillis();
            }
        }
    }
}
