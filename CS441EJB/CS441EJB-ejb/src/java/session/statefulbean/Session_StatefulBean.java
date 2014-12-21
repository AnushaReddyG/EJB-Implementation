/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.statefulbean;

import entity.bean.Professor;
import entity.bean.ProfessorFacadeRemote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.concurrent.TimeUnit;
//import javax.ejb.AccessTimeout;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author anusha
 * Uses Stateful session bean
 * Acts as server to Session_StatelessBean and client to Professor Entity Bean
 */
@Stateful
public class Session_StatefulBean implements Session_StatefulBeanRemote {
    HashMap<Integer, ArrayList<String>> deptcourses=new HashMap<>();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    //@AccessTimeout(value=0, unit=TimeUnit.SECONDS)
    //adds and returns list of courses department wise
    public ArrayList<String> listOfCourses(int deptid) {
        ArrayList<String> cscourses=new ArrayList<>();
		cscourses.add("1.Algorithms-2");
		cscourses.add("2.Database Systems");
		deptcourses.put(1, cscourses);
		
		//add courses related to Biology
		ArrayList<String> biocourses = new ArrayList<>();
		biocourses.add("1.BioChemistry-2");
		biocourses.add("2.Analysis of Biological Stuctures");
		deptcourses.put(2, biocourses);
		
		//add courses related to MIS
		ArrayList<String> miscourses = new ArrayList<>();
		miscourses.add("1.Operations Management");
		miscourses.add("2.Datamining for Business");
		deptcourses.put(3,miscourses);
                
                return deptcourses.get(deptid);

    }
    
    @Override
    //list of professor records in Professor table from Entity Bean
    public List<Professor> listofProfessors(){
        List<Professor> listprof=new ArrayList<>();
        try {
            ProfessorFacadeRemote prof;//reference
            InitialContext entityinitialContext = new InitialContext();
            //portable jndi lookup to obtain remote interface of bean
            prof=(ProfessorFacadeRemote) entityinitialContext.lookup("java:global/CS441EJB/CS441EJB-ejb/ProfessorFacade");
            
            listprof=prof.findAll();

            
        } catch (NamingException ex) {
            Logger.getLogger(Session_StatefulBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    return listprof;
}
}
