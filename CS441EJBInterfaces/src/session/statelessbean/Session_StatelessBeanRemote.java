/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.statelessbean;

import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author anusha
 */
@Remote
//Remote interface for Session_StatelessBean
public interface Session_StatelessBeanRemote {
    
    ArrayList<String> getDepartmentList() ;
    
    ArrayList<String> getCourseList(int deptid) ;
    
}
