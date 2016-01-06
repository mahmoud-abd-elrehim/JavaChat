/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import client.OnlineUsers;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author dono
 */
public class RenderList extends DefaultListCellRenderer implements ListCellRenderer<Object> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        OnlineUsers users = (OnlineUsers) value;
        String statusName = null;
        int status=users.getStatues();
        switch(status)
        {
            case 1:
                statusName = "Available";
                break;
            case 2:
                statusName = "Offline";
                break;
            case 3:

                statusName = "Busy";
                break;
            case 4:
                statusName = "Away";
                break;

        
        }
        
        setText("      " + users.getName()+"                "+statusName);
        //setText("                     " + String.valueOf(users.getStatues()) + "        ");
        

        setIcon(users.getImg());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setEnabled(true);
        setFont(list.getFont());
        return this;

    }

}
