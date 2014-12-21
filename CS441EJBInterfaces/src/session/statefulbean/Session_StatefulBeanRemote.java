/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.statefulbean;

import entity.bean.Professor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author anusha
 */
@Remote
//Remote interface for Session_StatefulBean
public interface Session_StatefulBeanRemote {
    
    public ArrayList<String> listOfCourses(int deptid);
    public List<Professor> listofProfessors();
}
