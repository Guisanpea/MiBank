/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibank.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mibank.entities.Transfer;

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
    
}
