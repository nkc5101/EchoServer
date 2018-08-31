
package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client implements Runnable {
    
    private Socket clientSocket;
    
    public Client(Socket client) {
        
        this.clientSocket = client;
        
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
                    
                    System.out.println("Server: " + inputLine);
                    inputLine = inputLine.toUpperCase();
                    out.println(inputLine);
                }
            
        } catch (IOException e) {
            
            e.printStackTrace();
            
        }
        
        while(true) {
            
            try {
                
                line = in.readLine();
                out.println(line);
                
            } catch (IOException e) {
                
                e.printStackTrace();
                
            }
        }
        
    }
    
}
