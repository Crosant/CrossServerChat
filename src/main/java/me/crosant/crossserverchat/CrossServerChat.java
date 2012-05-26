package me.crosant.crossserverchat;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Florian
 */
public class CrossServerChat extends JavaPlugin 
{
    public static int listen_port;
    public static int stream_port;
    public static String server_name;
    public static String server_address;
    public static boolean connected = false;
    private static final Logger log = Logger.getLogger("Minecraft");
    
    @Override
       public void onDisable() {
           
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
        
       }
       
    
    @Override
       public void onEnable() {

            if(checkConfig()) {
                
                getCSCConfig();
            
            }
            else {
             
                createConfig();
                
            }
                CSCThread CSCThread;

                CSCThread = new CSCThread();
                CSCThread.start();
                
                //getServer().getPluginManager().registerEvents(new CSCServerSocket(), this);
                getServer().getPluginManager().registerEvents(new CSCClientSocket(), this);
                

                    

                

                    
            
            log.info(String.format("[%s] Enabled Version %s", getDescription().getName(), getDescription().getVersion()));

       }
    
    public boolean getCSCConfig() {

        listen_port = this.getConfig().getInt("RemoteServer.Port");
        stream_port = this.getConfig().getInt("LocalServer.Port");;
        server_name = this.getConfig().getString("RemoteServer.Name");
        server_address = this.getConfig().getString("RemoteServer.Address");
        return false;
        
    }
    
    public static boolean checkConfig() {
        
        boolean exists = (new File("plugins/CrossServerChat/config.yml")).exists();
        if(!exists) {
            return false;
        }
        else {
            return true;
        }
            
    }

    public boolean createConfig() {
        
        this.getConfig().set("LocalServer.port", 4444);
        this.getConfig().set("RemoteServer.Name", "test");
        this.getConfig().set("RemoteServer.Address", "test.tdl");
        this.getConfig().set("RemoteServer.Port", 4444);
        this.saveConfig();

        
        
        return true;
        
    }
    
}



    
    
    

    
