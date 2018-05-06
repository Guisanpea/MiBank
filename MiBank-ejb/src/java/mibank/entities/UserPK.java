/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibank.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ubuntie
 */
@Embeddable
public class UserPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "account_bank")
    private int accountBank;
    @Basic(optional = false)
    @NotNull
    @Column(name = "account_office")
    private int accountOffice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "account_control")
    private int accountControl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "account_id")
    private int accountId;

    public UserPK() {
    }

    public UserPK(int accountBank, int accountOffice, int accountControl, int accountId) {
        this.accountBank = accountBank;
        this.accountOffice = accountOffice;
        this.accountControl = accountControl;
        this.accountId = accountId;
    }

    public int getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(int accountBank) {
        this.accountBank = accountBank;
    }

    public int getAccountOffice() {
        return accountOffice;
    }

    public void setAccountOffice(int accountOffice) {
        this.accountOffice = accountOffice;
    }

    public int getAccountControl() {
        return accountControl;
    }

    public void setAccountControl(int accountControl) {
        this.accountControl = accountControl;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) accountBank;
        hash += (int) accountOffice;
        hash += (int) accountControl;
        hash += (int) accountId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPK)) {
            return false;
        }
        UserPK other = (UserPK) object;
        if (this.accountBank != other.accountBank) {
            return false;
        }
        if (this.accountOffice != other.accountOffice) {
            return false;
        }
        if (this.accountControl != other.accountControl) {
            return false;
        }
        if (this.accountId != other.accountId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mibank.entities.UserPK[ accountBank=" + accountBank + ", accountOffice=" + accountOffice + ", accountControl=" + accountControl + ", accountId=" + accountId + " ]";
    }
    
}
