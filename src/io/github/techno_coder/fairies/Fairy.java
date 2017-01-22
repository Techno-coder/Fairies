package io.github.techno_coder.fairies;

import io.github.techno_coder.fairies.FairyHandlers.IFairyHandler;

import java.io.IOException;
import java.util.*;

public class Fairy {
    private int energy;
    private int health;
    private boolean died = false;
    private Scanner userInput = new Scanner(System.in);
    private List<RadarLocation> radar = new ArrayList<>();
    private NetworkHandler handler;
    boolean[] upgrades = new boolean[256];

    private Map<IFairyHandler, Boolean> handlers = new HashMap<>();

    public Fairy(int energy, int health, NetworkHandler handler) {
        this.energy = energy;
        this.health = health;
        this.handler = handler;
    }

    public void registerHandler(IFairyHandler handler) {
        handlers.put(handler, true);
    }

    public void removeHandler(IFairyHandler handler) {
        handlers.remove(handler);
    }

    public boolean getHandlerStatus(IFairyHandler handler) {
        return handlers.get(handler);
    }

    public void setHandlerStatus(IFairyHandler handler, boolean status) {
        handlers.put(handler, status);
    }

    public void move(int energyUse) {
        handler.getOut().println("MOVE " + energyUse);
        scan();
    }

    public void rotate(int degrees) {
        handler.getOut().println("ROTATE " + degrees);
        scan();
    }

    public void cast(int energyUse, int directionDegrees) {
        handler.getOut().println("CAST " + energyUse + " " + directionDegrees);
        scan();
    }

    public void restore(int energyUse) {
        handler.getOut().println("RESTORE " + energyUse);
        scan();
    }

    public void upgrade(int upgradeType) {
        handler.getOut().println("UPGRADE " + upgradeType);
        upgrades[upgradeType] = true;
        scan();
    }

    private void scan() {
        String[] input;
        try {
            input = handler.getIn().readLine().split(" ");
        } catch (IOException | NullPointerException e) {
            died = true;
            System.out.println("You have died! Type \"respawn\" to respawn");
            return;
        }

        scanBase(input);
        if (upgrades[1]) scanUpgradeOne(input);

    }

    private void scanBase(String[] input) {
        energy = Integer.parseInt(input[0]);
        health = Integer.parseInt(input[1]);
        int numOfEnemies = Integer.parseInt(input[2]);
        radar.clear();
        for (int i = 0; i < numOfEnemies; i++) {
            int bearing, distance;
            bearing = Integer.parseInt(input[(i * 2) + 3]);
            distance = Integer.parseInt(input[(i * 2) + 4]);
            radar.add(new RadarLocation(bearing, distance));
        }
    }
    private void scanUpgradeOne(String[] input) {
        int startSectionInclusive = (Integer.parseInt(input[2]) * 2) + 5;
        int numNewBullets = Integer.parseInt(input[startSectionInclusive]);
        for(int i = 0; i < numNewBullets; ++i) {
            int id, origin_bearing, origin_distance, spell_bearing;
            id = Integer.parseInt(input[startSectionInclusive + (i * 4) + 1]);
            origin_bearing = Integer.parseInt(input[startSectionInclusive + (i * 4) + 2]);
            origin_distance = Integer.parseInt(input[startSectionInclusive + (i * 4) + 3]);
            spell_bearing = Integer.parseInt(input[startSectionInclusive + (i * 4) + 4]);
            //TODO Add to some sort of list
        }
        int numRemovedBullets = Integer.parseInt(input[startSectionInclusive + (numNewBullets * 4) + 1]);
        for(int i = 0; i < numRemovedBullets; ++i) {
            int id;
            id = Integer.parseInt(input[startSectionInclusive + (numNewBullets * 4) + 1 + i]);
            //TODO Remove from some sort of list
        }
    }


    public boolean start(int networkTickSpeed) throws InterruptedException, IOException {
        move(0);
        synchronized (this) {
            while (true) {
                wait(networkTickSpeed);
                String input = checkForInput();
                input = input.toUpperCase();
                if (input.equals("QUIT")) {
                    System.out.println("Exiting interface ...");
                    return false;
                }
                if (input.equals("RESPAWN")) {
                    return true;
                }
                if(handler.getOut().checkError() || died) {
                    if(!input.equals("NULL")) {
                        System.out.println("You have died! Type \"respawn\" to respawn");
                    }
                    continue;
                }
                rotate(0);
                for (IFairyHandler fairyHandler : handlers.keySet()) {
                    if (!handlers.get(fairyHandler)) continue;
                    fairyHandler.runHandler(this, input);
                }
            }
        }
    }

    private String checkForInput() throws IOException {
        if (System.in.available() > 0) {
            return userInput.nextLine();
        }
        return "NULL";
    }

    public List<RadarLocation> getRadar() {
        return radar;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHealth() {
        return health;
    }
}
