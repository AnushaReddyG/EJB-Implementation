/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs441ejbclient1;

import entity.bean.Professor;
import entity.bean.ProfessorFacadeRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javax.annotation.Resource;
import javax.ejb.ConcurrentAccessTimeoutException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.accesstimeout.TestAccessTimeoutRemote;
import session.statelessbean.Session_StatelessBeanRemote;

/**
 *
 * @author anusha
 */
public class Main {
    //connection info used for Message driven beans
    @Resource(mappedName = "jms/dest")
    private static Queue dest;
    @Resource(mappedName = "jms/queue")
    private static ConnectionFactory queue;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        ArrayList<String> depts = new ArrayList<>();
        
         ArrayList<String> proflist = new ArrayList<>();
        // TODO code application logic here
        try{
       
        Session_StatelessBeanRemote stateless_bean_object;//reference obj for stateless bean class
        
        //Session_StatefulBeanRemote stateful_bean_object;
        ProfessorFacadeRemote professor_course;//reference for entity class
        
        TestAccessTimeoutRemote accesstest;//reference for access timeout class
        
        InitialContext sessioninitialContext = new InitialContext();
        InitialContext accessinitialContext = new InitialContext();
        InitialContext entityinitialContext = new InitialContext();
        
        //obtaining references for remote interface through JNDI lookups
        stateless_bean_object = (Session_StatelessBeanRemote) sessioninitialContext.lookup("java:global/CS441EJB/CS441EJB-ejb/Session_StatelessBean");
        
        accesstest = (TestAccessTimeoutRemote) accessinitialContext.lookup("java:global/CS441EJB/CS441EJB-ejb/TestAccessTimeout");
        
        professor_course=(ProfessorFacadeRemote) entityinitialContext.lookup("java:global/CS441EJB/CS441EJB-ejb/ProfessorFacade");
        
        //choose an option for testing bean
        System.out.println("Choose typeId below for bean you want to work with :");
        System.out.println("1 Session Bean");
        System.out.println("2 Entity Bean");
        System.out.println("3 Message Driven Bean");
        System.out.println("4 Access Timeout testing");
        
        Scanner input = new Scanner(System.in);
        String beanid=input.next();
        
        //if Session bean is chosen for test
        if(beanid.equals("1")){
        depts=stateless_bean_object.getDepartmentList();
        System.out.println("Select department ID for which you want to check the list of courses offered");
        
        
        for (String value: depts) {
		System.out.println(value);
		}
        //System.out.println("another invocation");
        
          
          String deptid;
          deptid = input.next();
        //obtaining stateful bean method using stateless interface reference
        proflist=stateless_bean_object.getCourseList(Integer.parseInt(deptid));
        
        for (String value: proflist) {
		System.out.println(value);
		}
        
        }
        // if entity bean is chosen for test
        if(beanid.equals("2")){
            
        Professor prof=new Professor();
        System.out.println("Choose ID from the list of options provided below");
        System.out.println("1. List all professors and their courses available using entity bean");
        System.out.println("2. Insert professor name and course into database using entity bean");
        
        String entitybeanid=input.next();
            switch (entitybeanid) {
                case "1"://gets list of professors and their courses offered
                    System.out.println("List of all Courses and Corresponding Professors are : ");
                    List<Professor> l = professor_course.findAll();
                    for(Professor b:l)
                    {
                        System.out.println("*******************************");
                        System.out.println("Professor Name : "+b.getProfessor());
                        System.out.println("Course Name : "+b.getCourse());
                        
                    }
                    break;
                case "2"://inserts a row into Professor Table
                    System.out.println("Enter Professor name");
                    BufferedReader profname = new BufferedReader(new InputStreamReader(System.in));
                    String profInput = profname.readLine();
                    System.out.println("Enter the course name taken ");
                    BufferedReader coursename = new BufferedReader(new InputStreamReader(System.in));
                    String courseInput = coursename.readLine();
                    //Set the object value with setter methods
                    prof.setProfessor(profInput);
                    prof.setCourse(courseInput);
                    //Insert into database for professor table
                    professor_course.create(prof);
                    System.out.println("Check for record in java database...");
                    break;
            }
        }
        
        //if message bean is chose for test
        if(beanid.equals("3")){
         BufferedReader msgs = null;
         Main m = new Main();
         int no_of_msgs = 0;
         System.out.println("Enter the number of messages you want to send....");
         //input the number of msgs to be sent
         msgs = new BufferedReader(new InputStreamReader(System.in));
         try {
             no_of_msgs = Integer.parseInt(msgs.readLine());
         } catch (IOException ex) {
             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         }
         List<String> msgString = new ArrayList<>();
         String input_string="";
         for(int i=0;i<no_of_msgs;i++)
         {
             int count = i+1;
             System.out.println("Enter the message "+count+":");
             try {
                 input_string = msgs.readLine();
             } catch (IOException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             msgString.add(input_string);
         }
         
         //sending messages to server
         for(String sendmsg:msgString) {
             try {
                        m.sendJMSMessageToDest(sendmsg);
             } catch (JMSException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         System.out.println("Messages sent to server. Check Server log for output ");
        }
        
        //testing Access Timeout annotation in beans
        if(beanid.equals("4")){
            String values = accesstest.accessTimeoutTest();
            
            System.out.println(values);
        }
        
        if(!beanid.equals("1") && !beanid.equals("2") && !beanid.equals("3") &&!beanid.equals("4")){
				System.out.println("Not a valid number");
				System.out.println("Please Return to Application");
				exit();
			}
        
        }catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }catch (ConcurrentAccessTimeoutException e){
            System.out.println("Concurrent Access timeout exception occured");
        }
    }

    private Message createJMSMessageForjmsDest(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToDest(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = queue.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(dest);
            messageProducer.send(createJMSMessageForjmsDest(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
       }
    }
    
}
