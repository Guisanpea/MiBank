/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibank.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mibank.entities.Account;
import mibank.entities.User;

/**
 *
 * @author ubuntie
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "MiBank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    public Account findByAccountNumber(String number){
        int bank = Integer.valueOf(number.substring(0,4));
        int office = Integer.valueOf(number.substring(4,8));
        int control = Integer.valueOf(number.substring(8,10));
        int id = Integer.valueOf(number.substring(10,20));
        
        Query query = em.createNamedQuery("Account.findByFullNumber");
        query.setParameter("bank", bank);
        query.setParameter("office", office);
        query.setParameter("control", control);
        query.setParameter("id", id);
        
        return (Account) query.getSingleResult();
    }
    
    public Account findByUser(User user){
        Query query = em.createNamedQuery("Account.findByUser");
        query.setParameter("user", user);
        
        return (Account) query.getSingleResult();
    }
}
