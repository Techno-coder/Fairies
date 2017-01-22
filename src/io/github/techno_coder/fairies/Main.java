package io.github.techno_coder.fairies;

import io.github.techno_coder.fairies.FairyHandlers.automatic.*;
import io.github.techno_coder.fairies.FairyHandlers.manual.FairyInfoHandler;
import io.github.techno_coder.fairies.FairyHandlers.manual.FairyManualHeal;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static Main main = new Main();
    private NetworkHandler netHandler = new NetworkHandler();

    public static void main(String[] args) {
//        while (main.start()) {}
        synchronized (Main.class) {
            for (int i = 0; i < 100; i++) {
                try {
                    Main.class.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Thread(() -> main.start()).start();
            }
        }
    }

    public boolean start() {
        netHandler = new NetworkHandler();
        String randomUsername = String.valueOf(ThreadLocalRandom.current().nextInt());
        Fairy fairy = netHandler.init(randomUsername, "SamplePass;)", "INSERT IP HERE", 23182183);

        if (fairy == null) return false;
        try {
            registerHandlers(fairy);
            return fairy.start(1);
        } catch (InterruptedException | IOException e) {
            System.out.println("An error occurred.");
        }
        return false;
    }

    public void registerHandlers(Fairy fairy) {
        fairy.registerHandler(new FairyAutoStrafeHandler());
//        fairy.registerHandler(new FairyAutoHealerHandler());
//        fairy.registerHandler(new FairySmartShooterHandler());
//        fairy.registerHandler(new FairyInfoHandler());
//        fairy.registerHandler(new FairyStrafeShootSynchronizer(fairy));
//        fairy.registerHandler(new FairyManualBurstShootHandler());
//        fairy.registerHandler(new FairyManualQuickMove());
//        fairy.registerHandler(new FairyManualHeal());
//        fairy.registerHandler(new FairyAutoRotateAndShootHandler());
//        fairy.registerHandler(new FairyBulletHellHandler());
    }
}
