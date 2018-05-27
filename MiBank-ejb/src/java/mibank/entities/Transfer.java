/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibank.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    , @NamedQuery(name = "Transfer.findByAmount", query = "SELECT t FROM Transfer t WHERE t.amount = :amount")
    , @NamedQuery(name = "Transfer.findByDescription", query = "SELECT t FROM Transfer t WHERE t.description = :description")
    , @NamedQuery(name = "Transfer.findByAccountBank", query = "SELECT t FROM Transfer t WHERE t.accountBank = :accountBank")
    , @NamedQuery(name = "Transfer.findByAccountOffice", query = "SELECT t FROM Transfer t WHERE t.accountOffice = :accountOffice")
    , @NamedQuery(name = "Transfer.findByAccountControl", query = "SELECT t FROM Transfer t WHERE t.accountControl = :accountControl")
    , @NamedQuery(name = "Transfer.findByFromAccountBank", query = "SELECT t FROM Transfer t WHERE t.fromAccountBank = :fromAccountBank")
    , @NamedQuery(name = "Transfer.findByFromAccountOffice", query = "SELECT t FROM Transfer t WHERE t.fromAccountOffice = :fromAccountOffice")
    , @NamedQuery(name = "Transfer.findByFromAccountControl", query = "SELECT t FROM Transfer t WHERE t.fromAccountControl = :fromAccountControl")
    , @NamedQuery(name = "Transfer.findByFromAccountId", query = "SELECT t FROM Transfer t WHERE t.fromAccountId = :fromAccountId")
    , @NamedQuery(name = "Transfer.findByCreatedAt", query = "SELECT t FROM Transfer t WHERE t.createdAt = :createdAt")})
public class Transfer implements Serializable {

    static final public int BANKID = 9313;
    static final public int OFFICEID = 1998;
    static final public int CONTROL = 05;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private long amount;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
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
    @Column(name = "from_account_bank")
    private Integer fromAccountBank;
    @Column(name = "from_account_office")
    private Integer fromAccountOffice;
    @Column(name = "from_account_control")
    private Integer fromAccountControl;
    @Column(name = "from_account_id")
    private Integer fromAccountId;
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account account;
    @JoinColumn(name = "employee_involved", referencedColumnName = "id")
    @ManyToOne
    private Employee employeeInvolved;

    public Transfer() {
    }

    public Transfer(Integer id) {
        this.id = id;
    }

    public Transfer(Integer id, long amount, int accountBank, int accountOffice, int accountControl) {
        this.id = id;
        this.amount = amount;
        this.accountBank = accountBank;
        this.accountOffice = accountOffice;
        this.accountControl = accountControl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getFromAccountBank() {
        return fromAccountBank;
    }

    public void setFromAccountBank(Integer fromAccountBank) {
        this.fromAccountBank = fromAccountBank;
    }

    public Integer getFromAccountOffice() {
        return fromAccountOffice;
    }

    public void setFromAccountOffice(Integer fromAccountOffice) {
        this.fromAccountOffice = fromAccountOffice;
    }

    public Integer getFromAccountControl() {
        return fromAccountControl;
    }

    public void setFromAccountControl(Integer fromAccountControl) {
        this.fromAccountControl = fromAccountControl;
    }

    public Integer getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Integer fromAccountId) {
        this.fromAccountId = fromAccountId;
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

    public void setFromLocalAccount() {
        this.fromAccountBank = BANKID;
        this.fromAccountOffice = OFFICEID;
        this.fromAccountControl = CONTROL;
    }
}
