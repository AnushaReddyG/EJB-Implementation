/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message.bean;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author anusha
 * 
 * Sending messages on server using message driven bean
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/dest"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MsgBean implements MessageListener {
    
    public MsgBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        TextMessage msg = null;
        try{
        if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                //message received on glassfish server
                System.out.println("Message Driven Bean: Message received: " +msg.getText());
            } 
        else {
            System.out.println("Message is of Wrong type: " +message.getClass().getName());
        }
        }catch (JMSException ex) {
                Logger.getLogger(MsgBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
