package io.github.techno_coder.fairies.FairyHandlers.manual;

import io.github.techno_coder.fairies.Fairy;
import io.github.techno_coder.fairies.FairyHandlers.IFairyHandler;

public class FairyManualQuickMove implements IFairyHandler {

    int currentDegree = 0;

    @Override
    public void runHandler(Fairy fairy, String command) {
        if (command.equals("QL")) {
            if(currentDegree == 90) fairy.rotate(180);
            if(currentDegree == 0) fairy.rotate(-90);
            if(currentDegree == 180) fairy.rotate(90);
            fairy.move(fairy.getEnergy());
            currentDegree = -90;
        }

        if(command.equals("QR")) {
            if(currentDegree == -90) fairy.rotate(180);
            if(currentDegree == 0) fairy.rotate(90);
            if(currentDegree == 180) fairy.rotate(-90);
            fairy.move(fairy.getEnergy());
            currentDegree = 90;
        }

        if(command.equals("QU")) {
            if(currentDegree == -90) fairy.rotate(90);
            if(currentDegree == 180) fairy.rotate(180);
            if(currentDegree == 90) fairy.rotate(-90);
            fairy.move(fairy.getEnergy());
            currentDegree = 0;
        }

        if (command.equals("QD")) {
            if(currentDegree == -90) fairy.rotate(-90);
            if(currentDegree == 0) fairy.rotate(180);
            if(currentDegree == 90) fairy.rotate(90);
            fairy.move(fairy.getEnergy());
            currentDegree = 180;
        }

    }
}
