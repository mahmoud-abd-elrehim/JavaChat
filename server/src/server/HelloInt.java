/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Client.ClientInt;
import Client.User;
import java.rmi.*;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author marwa
 */
public interface HelloInt extends Remote{
        public void tellOther(String msg)  throws RemoteException;
        public void register(ClientInt cref, int id) throws RemoteException;
        public void unRegister(ClientInt cref) throws RemoteException;
        public void tellstop()  throws RemoteException;
        public void insert(User b)throws RemoteException;
        public int select(User c)throws RemoteException;
        public int checkEmail(User c)throws RemoteException;
        public void sendAddById(ClientInt fromclnt,int id)throws RemoteException;
        public void callchangestatus(int idd, int status) throws RemoteException;
        public Vector<Integer> select_freind(int idd)throws RemoteException;
        public Vector<Integer> select_request(int c) throws RemoteException;
        public void privateChat(String name,int id,int my_id) throws RemoteException;
        public HashMap<String,Integer> userStatusMap(Vector<Integer> friends)throws RemoteException;
        public void updateReqTable(int idd, int id)throws RemoteException;
         public Vector<String> userReqMap(Vector<Integer> ids_Req) throws RemoteException;
         public void removeReqfromDB(int idd)throws RemoteException;
        public void removeReqAndAddFromDB(int from,int to) throws RemoteException;
        public int idOfUser(String str) throws RemoteException;
        public Vector<String> userName(Vector<Integer> friends)  throws RemoteException;
        public Vector<Integer> Statusoffriends(Vector<Integer> friends) throws RemoteException;
        
        //sendFile
        //void sendFileToOthers(byte[] data) throws RemoteException;
        public void sendFileToOthers(byte[] data,int myId) throws RemoteException;
        
        
        public String returnUserofSender(int senderId)throws RemoteException;
        
        //chart
        public void drawChart()throws RemoteException;
}
