/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.bean;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author anusha
 */
@Remote
public interface ProfessorFacadeRemote {

    void create(Professor professor);

    void edit(Professor professor);

    void remove(Professor professor);

    Professor find(Object id);

    List<Professor> findAll();

    List<Professor> findRange(int[] range);

    int count();
    
}
