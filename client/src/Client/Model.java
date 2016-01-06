/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Client.Controller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author marwa
 */
public class Model extends UnicastRemoteObject implements ClientInt {

    
    Thread open;
    Controller c;
    public Model(Controller cc) throws RemoteException{
        this.c = cc;
    }
    @Override
    public void receive(String msg) throws RemoteException {
        c.displayMsg(msg);
        System.out.print(msg);
    }
    
    public void tellstopcon() throws RemoteException
    {
        System.out.print(" Server Stopped ");
        c.showstopdialog();
        
    }

    @Override
    public void notifyReq() throws RemoteException {
        c.showdialoguereq();
    }

    @Override
    public void openFriendChat(int x,int y) throws RemoteException {
        if (c.flag!=1)
        {
        c.showView();
        c.funCall(x,y);
        c.flag=1;
        }
    
    }

    @Override
    public void receiveAnnouncement(String msg) throws RemoteException {
        c.displayAnnouncement(msg);
    }
    
    //sendFile
    public void recieveFile (final byte[] data)
	{
		System.out.println("File send");
		
			JFileChooser fC = new JFileChooser();
			if(fC.showSaveDialog(c.v) == JFileChooser.APPROVE_OPTION )
			{
				final String path = fC.getSelectedFile().getPath();
				try
				{
                                     open = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            FileOutputStream fos = null;
                                            try {
                                                fos = new FileOutputStream(path);
                                                //fos.flush();
                                                fos.write(data);
                                            } catch (FileNotFoundException ex) {
                                                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (IOException ex) {
                                                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                                            } finally {
                                                try {
                                                    fos.close();
                                                } catch (IOException ex) {
                                                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                        }
                                    });
					
					open.start();
				}
				catch(Exception e)
				{}
				
			}
                   System.out.println("File sendedededed");

		
		
		
	}
}
