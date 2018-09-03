package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    public static void main(String[] args) {

        System.out.println("Simple Echo Client");

        try {

            System.out.println("Waiting for a connection....");
            InetAddress localAddress = InetAddress.getLocalHost();

            Socket clientSocket = new Socket(localAddress, 6000);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Connected to Server");
            Scanner sc = new Scanner(System.in);
            
            Thread sendmsg = new Thread(new Runnable(){
               
                public void run() {
                    
                    System.out.println("Enter text:");
                    
                    while (true) {
                        
                        String inputLine = sc.nextLine();

                        if ("quit".equalsIgnoreCase(inputLine)) {

                            break;

                        }

                        out.println(inputLine);
                        
                    }
                    
                }
                
            });
            
            Thread readmsg = new Thread(new Runnable() {
                
               public void run() {
                   
                   while (true) {
                       
                       try {
                           
                           String response = br.readLine();
                           
                           System.out.println("Server response: ");
                           System.out.println(response);
                           System.out.println("Enter text:");
                           
                       } catch (IOException e) {
                           
                           e.printStackTrace();
                           
                       }
                   }
               } 
            });
            
            sendmsg.start();
            readmsg.start();
            
        } catch (Exception e) {

            System.out.println(e);

        }

    }

}
