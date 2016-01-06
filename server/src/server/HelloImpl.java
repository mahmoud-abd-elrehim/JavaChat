/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Client.ClientInt;
import Client.User;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.OracleDriver;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author marwa
 */
public class HelloImpl extends UnicastRemoteObject implements HelloInt {

    Vector<ClientInt> vclient = new Vector<ClientInt>();
    Vector<Integer> ID = new Vector<>();
    int counter = 0;
    int curr;

    HelloImpl() throws RemoteException {
    }

    public void tellstop() {

        for (ClientInt cint : vclient) {
            try {
                cint.tellstopcon();
            } catch (RemoteException e) {
                System.out.println("do not send to client");
                e.printStackTrace();
            }
        }

    }

    ///chat group
    public void tellOther(String msg) {
        System.out.print(" message received " + msg);
        for (ClientInt cint : vclient) {
            try {
                cint.receive(msg);
            } catch (RemoteException e) {
                System.out.println("do not send msg to client");
                e.printStackTrace();
            }
        }
    }

    ///chatgoup
    /*public void registerrr(ClientInt cref) {
     vclient.add(cref);
     System.out.print("Client added");
     }*/
    @Override
    public void register(ClientInt clientRef, int idd) throws RemoteException {
        vclient.insertElementAt(clientRef, counter);
        ID.insertElementAt(idd, counter);
        System.out.println("client added    " + idd);
        curr = idd;

        counter++;

    }

    ///chatgoup
   /* public void unregisterrr(ClientInt cref) {
     vclient.remove(cref);
     System.out.print("Client removed");
     }*/
    public void unRegister(ClientInt clientRef) throws RemoteException {
        vclient.removeElementAt(counter - 1);
        ID.remove(counter - 1);
        counter--;
        System.out.println("client removed");
    }

    @Override
    public void insert(User b) throws RemoteException {
        try {
            DriverManager.registerDriver(new OracleDriver());

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            PreparedStatement ps = con.prepareStatement("insert into users (Full_name,Email,User_name,Password,Gender, Country,Status_ID) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, b.getName());//FullName
            ps.setString(2, b.getEmail());//Email
            ps.setString(3, b.getUname());//user_name
            ps.setString(4, b.getPassword());//password
            ps.setString(5, b.getUgender());//Gender
            ps.setString(6, b.getUCountry());//Country*/
            ps.setInt(7, b.getStatus_ID());
            

            ps.executeUpdate();
            System.out.println(Statement.RETURN_GENERATED_KEYS);

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
      }
    
    @Override
    public int idOfUser(String str) throws RemoteException {
        PreparedStatement Is;
        int idlogin = 0;
        try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            Is = con.prepareStatement("select ID from users where Email=?");
            Is.setString(1, str);
            ResultSet result = Is.executeQuery();
            if (result.next()) {
                System.out.println("success ");
                idlogin = result.getInt(1);

            } else {

                System.out.println("failed ");
                idlogin = 0;
            }

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        return idlogin;
    }

    @Override
    public int select(User c) throws RemoteException {
        PreparedStatement Is;
        boolean flag = false;
        int idlogin = 0;
        try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            Is = con.prepareStatement("select ID from users where User_name=? AND Password=?");
            Is.setString(1, c.getUname());
            Is.setString(2, c.getPassword());
            ResultSet result = Is.executeQuery();
            if (result.next()) {
                System.out.println("success ");
                flag = true;
                idlogin = result.getInt(1);

            } else {

                System.out.println("failed ");
                idlogin = 0;
                flag = false;

            }

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        return idlogin;
    }
    
    /*@Override
    public boolean IsMailFounded(s3)
    {
        int flag=0;
        
    }*/
    

    @Override
    public int checkEmail(User c) throws RemoteException {

        int id = 0;
        PreparedStatement Cs;
        try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            Cs = con.prepareStatement("select ID from users where Email=?");
            Cs.setString(1, c.getEmail());
            ResultSet result = Cs.executeQuery();
            if (result.next()) {
                System.out.println("finded ");

                id = result.getInt(1);
                System.out.println(result.getInt(1));

            } else {

                System.out.println("not failed ");

            }

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
        return id;
    }

    
    //addFreind
    @Override
    public void sendAddById(ClientInt fromclnt,int id) throws RemoteException {
        int fromid;
        /*for (int i = 0; i < counter; i++) {
            if (fromclnt == vclient.elementAt(i)) {
                try {
                    fromid=ID.elementAt(i);
                    
                   //clntt.notifyReq();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/

        for (int i = 0; i < counter; i++) {
            if (id == ID.elementAt(i)) {
                try {
                    System.out.println(vclient.size());
                    System.out.println(ID.size());
                    ClientInt clntt=vclient.elementAt(i);
                    
                   clntt.notifyReq();
            

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void updateReqTable(int idd, int id)throws RemoteException
    {
        try {
            DriverManager.registerDriver(new OracleDriver());

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            //PreparedStatement ps = con.prepareStatement("select * from users");
            PreparedStatement ps = con.prepareStatement("insert into request (Req_From,Req_To) values (?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, idd);
            ps.setInt(2, id);
           
            ps.executeUpdate();
           
            System.out.println(Statement.RETURN_GENERATED_KEYS);

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void callchangestatus(int idd, int status) throws RemoteException {
        PreparedStatement Is;
        int flag=0;
       try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            Is = con.prepareStatement("update users set Status_ID=? where ID=?");
            Is.setInt(1, status);
            Is.setInt(2, idd);
            Is.executeUpdate();
            flag=1;
       }
       catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    @Override
    public Vector<Integer> select_freind(int c) throws RemoteException {
        int count=0;
        Vector <Integer> vec=new Vector<Integer>();
        PreparedStatement Is;
        boolean flag = false;
        int idlogin = 0;
        try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            Is = con.prepareStatement("select Friend_ID from user_friend where user_id=?");
            Is.setInt(1, c);
           
            ResultSet result = Is.executeQuery();
            while(result.next()) {
                //flag = true;
                idlogin = result.getInt(1);
                System.out.println(idlogin);
                vec.insertElementAt(idlogin,count);
                count++;
            } 
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        return vec;
    
    
    }
    
    
    @Override
    public Vector<Integer> select_request(int c) throws RemoteException {
        int count=0;
        Vector <Integer> vec=new Vector<Integer>();
        PreparedStatement Is;
        boolean flag = false;
        int idlogin = 0;
        try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            Is = con.prepareStatement("select Req_From from request where Req_To=?");
            Is.setInt(1, c);
           
            ResultSet result = Is.executeQuery();
            while(result.next()) {
                
                idlogin = result.getInt(1);
                System.out.println(idlogin);
                vec.insertElementAt(idlogin,count);
                count++;
            } 

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        return vec;
    
    
    }
    
    @Override
    public void privateChat(String name,int id,int my_id) throws RemoteException {
        System.out.println("send Message  to  "+my_id);
    
        Vector<ClientInt> onlineClient=new Vector<>();
        for(int i=0;i<counter;i++)
        {  
        if(my_id==ID.elementAt(i))
        {
               onlineClient.add(vclient.elementAt(i));
              
               vclient.elementAt(i).openFriendChat(my_id,id);
              
               onlineClient.add(vclient.elementAt(ID.indexOf(id)));
        }        
                
        }
      
      String userNameSender=returnUserofSender(id);
        
      for(ClientInt cc:onlineClient)
      {
          cc.receive(userNameSender+":"+name);
      }
     
      
    }
    
    
    @Override
    public String returnUserofSender(int senderId)throws RemoteException
    {
        String senderName=null;
        Vector<String> userNameFreinds=new Vector<>();
        PreparedStatement Is;
          try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            
            
                
            
            Is = con.prepareStatement("select User_name from users where ID=?");
            Is.setInt(1, senderId);
           
            ResultSet result = Is.executeQuery();
            if (result.next()) {
                System.out.println("success ");
               
                
                senderName=result.getString("User_name");
            } else {

                System.out.println("failed ");
               
            }

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        
        return senderName;
    }
    
    @Override
    public HashMap<String,Integer> userStatusMap(Vector<Integer> friends)
    {
        HashMap<String,Integer> userStats=new HashMap<>();
        PreparedStatement Is;
          try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            
            for(int i=0;i<friends.size();i++){
                
            
            Is = con.prepareStatement("select User_name,Status_ID from users where ID=?");
            Is.setInt(1, friends.elementAt(i));
           
            ResultSet result = Is.executeQuery();
            
            
            
            if (result.next()) {
                System.out.println("success ");
               
                
                userStats.put(result.getString("User_name"), result.getInt("Status_ID"));
            } else {

                System.out.println("failed ");
               
            }
}
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        
        return userStats;
    
    }
    
    ///to get user name of freinds
    @Override
    public Vector<String> userName(Vector<Integer> friends)
    {
        Vector<String> userNameFreinds=new Vector<>();
        PreparedStatement Is;
          try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            
            for(int i=0;i<friends.size();i++){
                
            
            Is = con.prepareStatement("select User_name from users where ID=?");
            Is.setInt(1, friends.elementAt(i));
           
            ResultSet result = Is.executeQuery();
            
            
            
            if (result.next()) {
                System.out.println("success ");
               
                
                userNameFreinds.insertElementAt(result.getString("User_name"), i);
            } else {

                System.out.println("failed ");
               
            }
}
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        
        return userNameFreinds;
    
    }
    
    //to get status_id of freinds
    @Override
    public Vector<Integer> Statusoffriends(Vector<Integer> friends)
    {
        Vector<Integer> StatsOfFreiends=new Vector<>();
        PreparedStatement Is;
          try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            
            for(int i=0;i<friends.size();i++){
                
            
            Is = con.prepareStatement("select Status_ID from users where ID=?");
            Is.setInt(1, friends.elementAt(i));
           
            ResultSet result = Is.executeQuery();
            if (result.next()) {
                System.out.println("success ");
               
                
                StatsOfFreiends.insertElementAt(result.getInt("Status_ID"), i);
            } else {

                System.out.println("failed ");
               
            }
}
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        
        return StatsOfFreiends;
    
    }
    
    
    @Override
    public Vector<String> userReqMap(Vector<Integer> ids_Req)
    {
        Vector<String> usernameReq=new Vector<>();
        PreparedStatement Is;
          try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            
            for(int i=0;i<ids_Req.size();i++){
                
            
            Is = con.prepareStatement("select User_name from users where ID=?");
            Is.setInt(1, ids_Req.elementAt(i));
           
            ResultSet result = Is.executeQuery();
            
            
            
            if (result.next()) {
                System.out.println("success ");
               
                
                usernameReq.insertElementAt(result.getString("User_name"),i);
            } else {

                System.out.println("failed ");
               
            }
}
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        
        return usernameReq;
    
    }

    @Override
    public void removeReqfromDB(int idd) throws RemoteException {
        PreparedStatement Is;
        try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            
            Is = con.prepareStatement("DELETE FROM request WHERE Req_To=?");
            Is.setInt(1, idd);
           
            Is.executeUpdate();
    
            System.out.println("Remove successfully");
            

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

    }

    @Override
    public void removeReqAndAddFromDB(int from,int to) throws RemoteException {
         try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            PreparedStatement ps = con.prepareStatement("insert into user_friend (user_id,Friend_ID) values (?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, from);
            ps.setInt(2, to);
      
            ps.executeUpdate();
            
            PreparedStatement Is = con.prepareStatement("insert into user_friend (user_id,Friend_ID) values (?,?)", Statement.RETURN_GENERATED_KEYS);

            Is.setInt(1, to);
            Is.setInt(2, from);
      
            ps.executeUpdate();
            System.out.println(Statement.RETURN_GENERATED_KEYS);

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    public void sendAnnouncement(String announce) throws RemoteException  {
        //String msg=new String("Hello client");
        System.out.print(" Announcement received Hello client");
        for (ClientInt cint : vclient) {
            try {
                cint.receiveAnnouncement(announce);
            } catch (RemoteException e) {
                System.out.println("do not send msg to client");
                e.printStackTrace();
            }
        }
    }
    
    
    //sendFile
    public void sendFileToOthers(byte[] data,int IdFriend)
	{
            
            for (int i = 0; i < vclient.size(); i++) 
            {
                if (IdFriend == ID.elementAt(i)) 
                {
                  try {
                      ClientInt clntt=vclient.elementAt(i);
                      clntt.recieveFile(data);
            

                  } catch (Exception e) {
                    System.out.println("Cant't send file to client");
                    e.printStackTrace();
                  }
                }
            
         				
            }}

  @Override
    public void drawChart()
    {
  
        
        PreparedStatement pOnLine,pOffLine;
        int OnLine = 0,OffLine = 0;
        try{
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            pOnLine = con.prepareStatement("select count(Status_ID) from users where Status_ID=1 or Status_ID=3 or Status_ID=4");
            
            ResultSet result1 = pOnLine.executeQuery();
            
            pOffLine = con.prepareStatement("select count(Status_ID) from users where Status_ID=2");
            
            ResultSet result2 = pOffLine.executeQuery();
            
            
            //System.out.println("finded ");

            if(result1.next())
            {
                OnLine = result1.getInt(1);
            }
            if(result2.next())
            {
                OffLine = result2.getInt(1);
            }   

    //System.out.println(result.getInt(1));

            
                
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
        
        
        
        PieChartStat myPieChart=new PieChartStat("Statistic", OnLine, OffLine);
        myPieChart.pack();
        RefineryUtilities.centerFrameOnScreen(myPieChart);
        myPieChart.setVisible(true);
        }
    

        
}
