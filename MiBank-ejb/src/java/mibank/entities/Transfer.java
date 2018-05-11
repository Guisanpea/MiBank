/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibank.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import static java.util.Objects.nonNull;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ubuntie
 */
@Entity
@Table(name = "Transfer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t")
    , @NamedQuery(name = "Transfer.findById", query = "SELECT t FROM Transfer t WHERE t.id = :id")
    , @NamedQuery(name = "Transfer.findByDNI", query = "SELECT t FROM Transfer t WHERE t.account.user.dni = :dni")
    , @NamedQuery(name = "Transfer.findByAccountFrom", query = "SELECT t FROM Transfer t WHERE t.accountFrom = :accountFrom")
    , @NamedQuery(name = "Transfer.findByAmount", query = "SELECT t FROM Transfer t WHERE t.amount = :amount")
    , @NamedQuery(name = "Transfer.findByDescription", query = "SELECT t FROM Transfer t WHERE t.description = :description")
    , @NamedQuery(name = "Transfer.findByCreatedAt", query = "SELECT t FROM Transfer t WHERE t.createdAt = :createdAt")})
public class Transfer implements Serializable {

    @Column(name = "amount")
    private BigInteger amount;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumns({
        @JoinColumn(name = "account_bank", referencedColumnName = "bank")
        , @JoinColumn(name = "account_office", referencedColumnName = "office")
        , @JoinColumn(name = "account_control", referencedColumnName = "control")
        , @JoinColumn(name = "account_id", referencedColumnName = "id")})
    @ManyToOne(optional = false)
    private Account account;
    @JoinColumns({
        @JoinColumn(name = "from_account_bank", referencedColumnName = "bank")
        , @JoinColumn(name = "from_account_office", referencedColumnName = "office")
        , @JoinColumn(name = "from_account_control", referencedColumnName = "control")
        , @JoinColumn(name = "from_account_id", referencedColumnName = "id")})
    @ManyToOne
    private Account accountFrom;
    @JoinColumn(name = "employee_involved", referencedColumnName = "id")
    @ManyToOne
    private Employee employeeInvolved;

    public Transfer() {
    }

    public Transfer(Integer id) {
        this.id = id;
    }

    public Transfer(Integer id, BigInteger amount) {
        this.id = id;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }
    public String getOrigin(){
        return nonNull(accountFrom) ? accountFrom.toString() : getType();
    }

    private String getType() {
        return amount.compareTo(BigInteger.ZERO) < 0 ? "STAKE" : "SETTLEMENT";
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Employee getEmployeeInvolved() {
        return employeeInvolved;
    }

    public void setEmployeeInvolved(Employee employeeInvolved) {
        this.employeeInvolved = employeeInvolved;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mibank.ejb.Transfer[ id=" + id + " ]";
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }
    
}
