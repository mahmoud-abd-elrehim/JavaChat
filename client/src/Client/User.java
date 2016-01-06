/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.Serializable;

/**
 *
 * @author Mahmoud
 */
public class User implements Serializable{
    int ID;
    String name;

    public User(String email) {
        this.email = email;
    }
    String email;
    String password;
    String Uname;
    String Ugender;
    String UCountry;
    int status_ID;

    public User(int ID, String name, String email, String password, String Uname, String Ugender, String UCountry, int status_ID) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.Uname = Uname;
        this.Ugender = Ugender;
        this.UCountry = UCountry;
        this.status_ID = status_ID;
    }

    public User(String Uname,String password) {
        this.password = password;
        this.Uname = Uname;
    }
    
    

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUname(String Uname) {
        this.Uname = Uname;
    }

    public void setUgender(String Ugender) {
        this.Ugender = Ugender;
    }

    public void setUCountry(String UCountry) {
        this.UCountry = UCountry;
    }

    public void setStatus_ID(int status_ID) {
        this.status_ID = status_ID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUname() {
        return Uname;
    }

    public String getUgender() {
        return Ugender;
    }

    public String getUCountry() {
        return UCountry;
    }

    public int getStatus_ID() {
        return status_ID;
    }

   
}
