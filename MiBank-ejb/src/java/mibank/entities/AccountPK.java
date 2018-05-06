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
public class AccountPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "bank")
    private int bank;
    @Basic(optional = false)
    @NotNull
    @Column(name = "office")
    private int office;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control")
    private int control;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;

    public AccountPK() {
    }

    public AccountPK(int bank, int office, int control, int id) {
        this.bank = bank;
        this.office = office;
        this.control = control;
        this.id = id;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) bank;
        hash += (int) office;
        hash += (int) control;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountPK)) {
            return false;
        }
        AccountPK other = (AccountPK) object;
        if (this.bank != other.bank) {
            return false;
        }
        if (this.office != other.office) {
            return false;
        }
        if (this.control != other.control) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mibank.entities.AccountPK[ bank=" + bank + ", office=" + office + ", control=" + control + ", id=" + id + " ]";
    }
    
}
