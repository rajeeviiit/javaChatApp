/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.util.Debug;

/**
 *
 * @author Rajeev Kumar
 */
public class MessageListner extends Thread {
    ServerSocket server;
    int port=8877;
    WritableGUI gui;
    
    private Object line;
    
    public MessageListner(WritableGUI gui,int port){
        this.port=port;
        this.gui=gui;
        try {
            server=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public MessageListner(){
        try {
            server=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListner.class.getName()).log(Level.SEVERE, null, ex);
        }
   } 
   @Override
   public void run(){
       Socket clientSocket;
        try {
            
         //   Debug.println("instancof socket", clientSocket.toString());
             while((clientSocket = server.accept())!=null){
                 
                 // Debug.println("in socket","");
             InputStream is=clientSocket.getInputStream();
             BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String  line=br.readLine();
             if(line !=null){
                 Debug.println("received", line);
                 gui.write(line);
             }
             
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageListner.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}