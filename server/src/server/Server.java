/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author marwa
 */
public class Server {

    HelloImpl obj;
    static Registry reg = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Server();
        
    }
    
    public Server()
    {
        ServerGUI gui=new ServerGUI(this);
        gui.setVisible(true);
        
    }
    
    public void StartServer()
    {
        try{
        obj=new HelloImpl();
        reg=LocateRegistry.createRegistry(5005);
        reg.rebind("HelloServise", obj);
        }
        catch(RemoteException ex){
            ex.printStackTrace();
        }
    }
    public  void StopServer()
    {
        try
        {
            reg.unbind("HelloServise");
            System.out.println("Server Stopped");
            obj.tellstop();
        }
        catch (RemoteException ex)
        {
            ex.printStackTrace();
        }
        catch (NotBoundException ex)
        {
            ex.printStackTrace();
        }        
    }

    public void printChart() {
         obj.drawChart();
    }
    
}
