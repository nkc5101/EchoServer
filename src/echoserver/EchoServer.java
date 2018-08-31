
package echoserver;

import java.io.IOException;
import java.net.ServerSocket;


public class EchoServer {


    public static void main(String[] args) {
        
        System.out.println("Simple Echo Server");
        
        try (ServerSocket serverSocket = new ServerSocket(6000)) {
            
            System.out.println("Waiting for connection....");
            
            while(true) {
                
            Client c;
            c = new Client(serverSocket.accept());
            Thread t = new Thread(c);
            t.start();
            
            System.out.println("Connected to client");
            
            }
            
        } catch (IOException ex) {
            
            System.out.println(ex);
            
        }

    }

}
