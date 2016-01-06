/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import javax.swing.Icon;

/**
 *
 * @author marwa
 */
public class RequestList {
    private String name;
    
    public RequestList(String text){
        this.name=text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
