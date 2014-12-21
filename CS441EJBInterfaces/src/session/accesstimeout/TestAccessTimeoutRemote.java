/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.accesstimeout;

import javax.ejb.Remote;

/**
 *
 * @author anusha
 */
@Remote
//Remote interface for TestAccessTimeout class
public interface TestAccessTimeoutRemote {
    
    public String accessTimeoutTest();
}
