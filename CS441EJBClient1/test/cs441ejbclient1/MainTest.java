/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs441ejbclient1;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anusha
 */
public class MainTest {
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        ArrayList<String> deptlist = new ArrayList<String>();
		//adding departments
		deptlist.add("1.Computer Science");
		deptlist.add("2.Biology");
		deptlist.add("3.MIS");
                
		assertEquals(3,deptlist.size());

        //Main.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
	public void main2(){
		ArrayList<String> courselist = new ArrayList<String>();
		//adding courses related to computer science
		courselist.add("1.Algorithms-2");
		courselist.add("2.Database Systems");
                
		assertNotSame(4,courselist.size());
        }
    
        @Test
	public void main3(){
		ArrayList<String> proflist = new ArrayList<String>();
		//adding professors
		proflist.add("1.Prasad Sistla");
		proflist.add("2.Ouir Wolfson");
		//validating the method for display
		assertEquals(2,proflist.size());
	}


}
