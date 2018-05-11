/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibank.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mibank.entities.Transfer;
import mibank.entities.User;

/**
 *
 * @author ubuntie
 */
@Stateless
public class TransferFacade extends AbstractFacade<Transfer> {

    @PersistenceContext(unitName = "MiBank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransferFacade() {
        super(Transfer.class);
    }
    
    public List<Transfer> getByUser(User user){
        return em.createNamedQuery("Transfer.findByDNI").setParameter("dni", user.getDni()).getResultList();
    }
}
