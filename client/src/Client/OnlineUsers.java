/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.swing.Icon;

/**
 *
 * @author 3yad
 */
public class OnlineUsers {
   private String name;
   private Icon img;
   private int statues;
    /*public OnlineUsers(String text,Icon icons){
        this.name=text;
        this.img=icons;
                
    
    
    }*/
    
    
     public OnlineUsers(String text,Icon icons,int myStatues){
        this.name=text;
        this.img=icons;
        this.statues=myStatues;
                
    
    
    }

    public int getStatues() {
        return statues;
    }

    public void setStatues(int statues) {
        this.statues = statues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getImg() {
        return img;
    }

    public void setImg(Icon img) {
        this.img = img;
    }
    
}
