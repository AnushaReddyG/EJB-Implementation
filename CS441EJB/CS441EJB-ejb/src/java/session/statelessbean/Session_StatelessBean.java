/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.statelessbean;
// used stateless bean
//acts as client for Sesssion_StatefulBean and Server for Client

import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.ejb.AccessTimeout;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.statefulbean.Session_StatefulBeanRemote;

/**
 *
 * @author anusha
 */
@Stateless
public class Session_StatelessBean implements Session_StatelessBeanRemote {
    public ArrayList<String> departments = new ArrayList<>();
    public ArrayList<String> courselist = new ArrayList<>();
    
    /**
     *
     * @return
     */
    @Override
    //@AccessTimeout(10)
    //add and return list of departments
    public ArrayList<String> getDepartmentList() {
        departments.add("1.Computer Science");
	departments.add("2.Biology");
	departments.add("3.MIS");
                
        return departments;

    }

    @Override
    //get list of courses using JNDI lookup for Session_StatefulBeanRemote interface
    public ArrayList<String> getCourseList(int deptid) {
        Session_StatefulBeanRemote stateful_bean_object; //reference   
        //String return_value="";
        System.out.println("calling second bean...");

            try {
           
            InitialContext initialContext = new InitialContext();

            // Portable JNDI names for bean Session_StatelessBean
             //jndi lookup to obtain remote interface of bean
            
            stateful_bean_object = (Session_StatefulBeanRemote) initialContext.lookup("java:global/CS441EJB/CS441EJB-ejb/Session_StatefulBean");
            System.out.println("Calling method on the stateless bean from stateful bean");
            courselist = stateful_bean_object.listOfCourses(deptid);
            } catch (NamingException ex) {
                   Logger.getLogger(Session_StatelessBean.class.getName()).log(Level.SEVERE, null, ex);
                   System.exit(1);
            }
            System.out.println("Exiting stateful bean ");

        
        
        return courselist;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
