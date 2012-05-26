
package me.crosant.crossserverchat;

import java.io.*;
import java.net.*;
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
public class CSCClientSocket implements Listener {
   public static Socket Socket;
   public static PrintWriter out;
   public static BufferedReader in;
    
   @EventHandler
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
       
   }
   
   
   
   
/*    public static boolean listen() {
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
        
    
    }*/
    
    public static boolean createClientSocket(String Server, int Port) {
        
        try {
            Socket = new Socket(Server, Port);
            out = new PrintWriter(Socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
            CrossServerChat.connected = true;
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: Server.".replace("Server", Server) + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: Server.".replace("Server", Server) + e.getMessage());
            return false;
        }
        System.out.println("LOLOLOLOLOL");
        
        return true;
        

    }


    
}
