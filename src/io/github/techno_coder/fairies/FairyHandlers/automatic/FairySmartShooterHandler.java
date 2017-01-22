package io.github.techno_coder.fairies.FairyHandlers.automatic;

import io.github.techno_coder.fairies.Fairy;
import io.github.techno_coder.fairies.FairyHandlers.IFairyHandler;
import io.github.techno_coder.fairies.RadarLocation;

public class FairySmartShooterHandler implements IFairyHandler {

    int offset = 0;

    @Override
    public void runHandler(Fairy fairy, String command) {
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

        if (fairy.getEnergy() < 300) return;

        System.out.println("Smart Shooting target at : " + bearing + " degrees and " + smallestDistance + " units away");
        for (int i = 0; i < fairy.getEnergy() / 50; ++i) {
            fairy.cast(50, bearing);
        }

    }
}
