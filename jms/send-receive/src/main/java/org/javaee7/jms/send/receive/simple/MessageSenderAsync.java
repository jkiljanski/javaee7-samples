package org.javaee7.jms.send.receive.simple;

import org.javaee7.jms.send.receive.Resources;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Asynchronous message sending is not supported in Java EE 7.
 * @author Arun Gupta
 */
@Stateless
public class MessageSenderAsync {

    @Inject
    //    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    JMSContext context;

    @Resource(lookup = Resources.ASYNC_QUEUE)
    Queue asyncQueue;

    public void sendMessage(String message) {
        try {
            context.createProducer().setAsync(new CompletionListener() {
                @Override
                public void onCompletion(Message msg) {
                    try {
                        System.out.println("Finished sending message!");
                        System.out.println(msg.getBody(String.class));
                    } catch (JMSException ex) {
                        Logger.getLogger(MessageSenderAsync.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void onException(Message msg, Exception e) {
                    try {
                        System.out.println("Error while sending message!");
                        System.out.println(msg.getBody(String.class));
                    } catch (JMSException ex) {
                        Logger.getLogger(MessageSenderAsync.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).send(asyncQueue, message);
        } catch (RuntimeException e) {
            System.out.println("Caught RuntimeException trying to invoke setAsync - not permitted in Java EE");
        }

        //context.createProducer().send(asyncQueue, message);
    }
}
