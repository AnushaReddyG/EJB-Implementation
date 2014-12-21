/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anusha
 */
@Stateless
public class ProfessorFacade extends AbstractFacade<Professor> implements ProfessorFacadeRemote {
    @PersistenceContext(unitName = "CS441EJB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfessorFacade() {
        super(Professor.class);
    }
    
}
