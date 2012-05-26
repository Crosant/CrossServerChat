
package me.crosant.crossserverchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

/**
 *
 * @author Florian
 */
class CSCServerSocket implements Listener {
   public static ServerSocket Socket;
   public static PrintWriter out;
   public static BufferedReader in;
   public static Socket clientSocket;
    

   
   
/*  @EventHandler
   public void onPlayerChat(PlayerChatEvent event) {
      String Player;
      String messange;
      String complete;
      Player = event.getPlayer().getName();
      messange = event.getMessage();
      complete = "[" + Player + "] : " + messange;
      send(complete);
   }
    
   public static void send(String messange) {
      
       out.print(messange);
       
   }*/
   
   
    public static boolean listen() {
        try {
            String fromRemoteServer;

            while ((fromRemoteServer = in.readLine()) != null) {
                
                Bukkit.broadcastMessage("[" + CrossServerChat.server_name + "] " + fromRemoteServer);
                        
            }
            out.close();
            in.close();
            Socket.close();
            
            return true;
        } catch (IOException ex) {
            Logger.getLogger(CSCClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    
    }
    
    public static boolean createServerSocket(int Port) {
        try {
            try {
                Socket = new ServerSocket(Port);
            } catch (IOException e) {
                System.err.println("Could not listen on port: " + Port);
                System.exit(1);
            }

            
            try {
                clientSocket = Socket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                return false;
            }
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            listen();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(CSCServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        

    }


    
}
