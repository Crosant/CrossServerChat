/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.crosant.crossserverchat;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class CSCThread extends Thread {
    @Override
    public void run() {
        
        CSCServerSocket.createServerSocket(CrossServerChat.stream_port);
        System.out.println("-.-");
        while (true)
        {try {
            Thread.sleep(10000);
            if(!CrossServerChat.connected){
       CSCClientSocket.createClientSocket(CrossServerChat.server_address, CrossServerChat.listen_port);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CSCThread.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
    }
    
    
    
    
}
