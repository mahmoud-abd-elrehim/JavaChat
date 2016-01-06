/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Client.Controller;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import Client.demo.*;
import java.io.FileOutputStream;
import javax.swing.ImageIcon;
import sun.org.mozilla.javascript.internal.xml.XMLLib;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import javax.xml.bind.Validator;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author marwa
 */
public class View extends JFrame implements ViewInt {

    //layout
    JPanel first;
    JPanel second;
    JTextArea ta;
    JTextField tf;
    JButton send;
    JButton sendFile;
    JButton saveFile;
    JScrollPane myscroll;

    Controller c;
    Thread open;
    public int xfrom, yto;
    public String userFrom,userTo;

    public View(Controller cc) {
        this.c = cc;
        //layout
        first = new JPanel();
        first.setLayout(new FlowLayout());
        second = new JPanel();
        second.setLayout(new FlowLayout());
        ta = new JTextArea();
        tf = new JTextField(30);
        send = new JButton("Send");
        sendFile = new JButton("Send File");
        saveFile = new JButton("Save Chat");
        myscroll = new JScrollPane(ta);

        //saveFile.setIcon(new ImageIcon(getClass().getResource("Xml.png")));
        //sendFile.setIcon(new ImageIcon(getClass().getResource("addFile.png")));
        //send.setIcon(new ImageIcon(getClass().getResource("send.png")));
        
        add(myscroll, BorderLayout.CENTER);
        //add(first);
        add(second, BorderLayout.SOUTH);
        second.add(tf);
        second.add(send);
        second.add(sendFile);
        second.add(saveFile);

        send.addActionListener(new Myact());
        sendFile.addActionListener(new mysendFileListener());
        saveFile.addActionListener(new saveFile());
        //setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                c.flag = 0;
            }
        });

    }

    public void display(String msg) {

        ta.append(msg + "\n");
    }

    public void fun(int idd, int idto) {
        xfrom = idd;
        yto = idto;
    }
    
    

    public class Myact implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == send) {

                String text = tf.getText();
                Controller.msgtotellone(text, xfrom, yto);
                System.out.println(xfrom + " " + yto);
                tf.setText("");
                System.out.println();

            }
        }
    }

    class mysendFileListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            JFileChooser fC = new JFileChooser();
            if (fC.showOpenDialog(View.this) == JFileChooser.APPROVE_OPTION) {
                final String path = fC.getSelectedFile().getPath();
                try {
                    open = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            FileInputStream fis = null;
                            try {
                                fis = new FileInputStream(path);
                                int size = fis.available();
                                byte[] b = new byte[size];
                                fis.read(b);
                                System.out.println("seeeeeeeeeeeeeeeend");
                                Controller.helloref.sendFileToOthers(b, yto);
                                fis.close();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                            } finally {
                                try {
                                    fis.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    });
                    open.start();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    class saveFile implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser f = new JFileChooser();
           JFileChooser fC = new JFileChooser();
            if (fC.showSaveDialog(View.this) == JFileChooser.APPROVE_OPTION) {
                final String path = fC.getSelectedFile().getPath();
            try {
                SchemaFactory Factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = Factory.newSchema(new StreamSource(new File("C:\\Users\\Mahmoud\\Documents\\NetBeansProjects\\JaxbChat\\src\\jaxbchat\\demo.xsd")));
                JAXBContext Context = JAXBContext.newInstance("Client.demo");
                ObjectFactory factory = new ObjectFactory();
                Marshaller marsh = Context.createMarshaller();
                marsh.setSchema(schema);

                MyMsg Msg = factory.createMyMsg();
                Msg.setHeader("file name");
               // Msgtype massage = factory.createMsgtype();
                // JAXBElement msg = factory.createMsgtype(massage);
                //  massage.getTo().add("ahmed");
                String split[] = null;
                int i = 0;
                List MyList = null;
                MyList = Msg.getMsg();
                for (String line : ta.getText().split("\\n")) {
                    userFrom=Controller.helloref.returnUserofSender(xfrom);
                    userTo=Controller.helloref.returnUserofSender(yto);
                    Msgtype massage = factory.createMsgtype();
                    massage.getTo().add(userTo);
                    split = line.split("\\:");
                    massage.setBody(split[i + 1]);
                    massage.setFrom(i);
                    split = null;
                    
                    MyList.add(massage);

                }
                

                //Msg.setHeader(marsh);
                JAXBElement<MyMsg> element = factory.createMyMsg(Msg);

                Marshaller marshaller = Context.createMarshaller();
                marsh.marshal(element, new DefaultHandler());

                marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);

                // output pretty printed
                marshaller.marshal(element, new FileOutputStream(path+".xml"));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        }
    }
}
