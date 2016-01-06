/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author marwa
 */
public interface ClientInt extends Remote {

    public void receive(String msg) throws RemoteException;

    public void tellstopcon() throws RemoteException;

    public void openFriendChat(int x ,int y) throws RemoteException;

    public void notifyReq() throws RemoteException;
     public void receiveAnnouncement(String msg) throws RemoteException;
     
     //sendFile
             void recieveFile (byte[] data) throws RemoteException;
    
}
