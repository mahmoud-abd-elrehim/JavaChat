/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import View.Client;
import View.NewJFrame;
import View.View;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import server.HelloInt;

/**
 *
 * @author marwa
 */
public class Controller {
   // public int flag;
    public static HelloInt helloref;
    public static Model objclt;
    
    public Vector<Integer> freinds;
    //public HashMap<String,Integer> usrsts;
    public Vector<Integer> stausOfFriends;
    public Vector<String>  usernameOfFriends;
    
    public Vector<Integer>req_from_ids;
    public Vector<String>req_from_username;
            
    //public boolean exsistUser = false;
    public int flag=0;
    
    
    
    //get user object by idd
    public int idd;
    
    public View v;
    Model m;
    NewJFrame N;
    Client clnt;
    Thread th;

    
    public Controller()
    {
        try
        {
        Registry reg=LocateRegistry.getRegistry("127.0.0.1",5005);
        helloref=(HelloInt)reg.lookup("HelloServise");
        objclt=new Model(this);
        //helloref.register(objclt);
        
        clnt=new Client(this);
        clnt.setSize(410,700);
	clnt.setVisible(true);
        
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(v,"Server not found");
        }
        
    }
    
    //private chat
    public static void msgtotellone(String text, int xfrom, int yto) {
        try {
            helloref.privateChat(text, xfrom, yto);
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ///chat group
    public static void msgtotell(String text) {
        try {
            helloref.tellOther(text);
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public void displayMsg(String msg)
   {
       v.display(msg);
   }
   public void showstopdialog()
   {
       JOptionPane.showMessageDialog(N,"Server not found");
       N.dispose();
       try{
       v.dispose();
       flag=0;
       }
       catch(Exception e){
           e.printStackTrace();
       }
   }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new Controller();
        
    }

    public void showView() 
    {
        if(flag==0)
        {
           v=new View(this);
           v.setSize(640,380);
           v.setVisible(true);
           flag=1;
        }   
    }

    public void showframe() {
        try{
       clnt.dispose();}
       catch(Exception e){}
        
        N=new NewJFrame(this);
        N.setSize(410,670);
	N.setVisible(true); 
    }

    public void showSignup() {
        try{
       N.dispose();}
       catch(Exception e){}
         
        clnt=new Client(this);
        clnt.setSize(410,700);
	clnt.setVisible(true);    
    }

    public void update2lists() throws RemoteException
    {
        freinds =helloref.select_freind(idd);
        req_from_ids=helloref.select_request(idd);
        stausOfFriends=helloref.Statusoffriends(freinds);
        usernameOfFriends=helloref.userName(freinds);
        N.showFreindname();
        req_from_username=helloref.userReqMap(req_from_ids);
        N.showRequests(req_from_username);
    }
    public void showapp() {
       try{
           freinds =helloref.select_freind(idd);
           req_from_ids=helloref.select_request(idd);
           try{
               clnt.dispose();
           }
           catch(Exception e){}
           
           N=new NewJFrame(this); 
           
           stausOfFriends=helloref.Statusoffriends(freinds);
           usernameOfFriends=helloref.userName(freinds);
           //usrsts=helloref.userStatusMap(freinds);
           N.showFreindname();
           
           req_from_username=helloref.userReqMap(req_from_ids);
           N.showRequests(req_from_username);
           
           N.setSize(410,670);
           N.setVisible(true);
       }
       catch(RemoteException ex){Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
}
    }

    public void sendAdd(int idd,int id) throws RemoteException {
        helloref.updateReqTable(idd,id);
        helloref.sendAddById(objclt,idd);
    }

    public void showdialoguereq() {
        System.out.println("send ok");
        //showapp();
        //JOptionPane.showMessageDialog(N,"folan want to add you");
    }

    public void changestatus(int status) {
        try {
            helloref.callchangestatus(idd,status);
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 

    public void funCall(int idd, int my_frien_id) {
        
        v.fun(idd,my_frien_id);
    
    }

    public void removeReq() {
        try {
            helloref.removeReqfromDB(idd);
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeReqAndAdd(int reqFromindex) throws RemoteException {
        
            helloref.removeReqfromDB(idd);
            
            int from_id=  req_from_ids.elementAt(reqFromindex) ;
           
            helloref.removeReqAndAddFromDB(from_id,idd);
    }

    public void displayAnnouncement(String msg)throws RemoteException {
        N.showAnnouncement(msg);
    }
    
}
