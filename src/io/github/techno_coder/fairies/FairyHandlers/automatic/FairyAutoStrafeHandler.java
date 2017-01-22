package io.github.techno_coder.fairies.FairyHandlers.automatic;

import io.github.techno_coder.fairies.Fairy;
import io.github.techno_coder.fairies.FairyHandlers.IFairyHandler;
import io.github.techno_coder.fairies.RadarLocation;

public class FairyAutoStrafeHandler implements IFairyHandler {

    int rotateTimer = 0;

    @Override
    public void runHandler(Fairy fairy, String command) {
        if (fairy.getEnergy() < 500) return;
        int smallestDistance = 2000000000;
        int bearing = 0;
        for (RadarLocation radarLocation : fairy.getRadar()) {
            if (radarLocation.distance < smallestDistance) {
                smallestDistance = radarLocation.distance;
                bearing = radarLocation.bearing;
            }
        }
        if(bearing == 0) {
            return;
        }

        fairy.rotate(bearing);
        fairy.move(fairy.getEnergy());
    }
}
