package io.github.techno_coder.fairies;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class NetworkHandler {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    void initNetwork(String ip, short port) throws IOException {
        socket = new Socket(InetAddress.getByName(ip), port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    Fairy init(String username, String password, String ip, int port) {
        try {
            this.initNetwork(ip, (short) port);
            String tempString = "NULL";
            int numOfAttempts = 0;
            while(!tempString.equals("USER?")) {
                tempString = this.getIn().readLine();
                if(numOfAttempts >= 1) {
                    System.out.println("Trying to receive authentication login handshake attempt " + numOfAttempts);
                }
                numOfAttempts++;
            }
            this.getOut().println(username + ":" + password);
            int tempEnergy, tempHealth;
            System.out.println("Waiting for server data ...");
            String[] input = this.getIn().readLine().split(" ");
            tempEnergy = Integer.parseInt(input[0]);
            tempHealth = Integer.parseInt(input[1]);
            System.out.println("Authenticated.");
            return new Fairy(tempEnergy, tempHealth, this);
        } catch (IOException e) {
            System.out.println("An error occurred while attempting to connect to the hostname.");
        }
        return null;
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }

}
