/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.accesstimeout;

import java.util.concurrent.TimeUnit;
import javax.ejb.AccessTimeout;
import javax.ejb.Singleton;

/**
 *
 * @author anusha
 * testing access timeouts annotation working
 */
@Singleton
@AccessTimeout(value=2, unit=TimeUnit.SECONDS)//timeout after 2 seconds if the methods in class are not accesable
public class TestAccessTimeout implements TestAccessTimeoutRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String accessTimeoutTest(){
    try{
            Thread.sleep(10000);
        
       }
        catch(Exception e){
            System.out.println("Access time out");
        }
         return "I am able to run";
        
    }
}
