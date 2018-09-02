package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {

    private final Socket clientSocket;
    private final int clientID;

    public Client(Socket client, int clientID) {

        this.clientSocket = client;
        this.clientID = clientID;

    }

    public void run() {

        String line;
        BufferedReader in = null;
        PrintWriter out = null;

        try {

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;

            while ((inputLine = in.readLine()) != null) {

                System.out.println("Client " + clientID + ":" + inputLine);
                inputLine = inputLine.toUpperCase();
                out.println(inputLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        while (true) {

            try {

                line = in.readLine();
                out.println(line);

            } catch (IOException e) {

                e.printStackTrace();

            }
        }

    }

}
