package com.tabeebkServer.pojo;
// Generated Apr 25, 2015 2:02:55 PM by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Account generated by hbm2java
 */
@Entity
@Table(name="account"
    ,catalog="graduationprojecttabeebakdb"
    , uniqueConstraints = @UniqueConstraint(columnNames="username") 
)
public class Account  implements java.io.Serializable {


     private AccountId id;
     private Accounttype accounttype;
     private String username;
     private String password;
     private String displayName;

    public Account() {
    }

    public Account(AccountId id, Accounttype accounttype, String username, String password, String displayName) {
       this.id = id;
       this.accounttype = accounttype;
       this.username = username;
       this.password = password;
       this.displayName = displayName;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="accountId", column=@Column(name="account_id", nullable=false) ), 
        @AttributeOverride(name="accountTypeId", column=@Column(name="accountType_id", nullable=false) ) } )
    public AccountId getId() {
        return this.id;
    }
    
    public void setId(AccountId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="accountType_id", nullable=false, insertable=false, updatable=false)
    public Accounttype getAccounttype() {
        return this.accounttype;
    }
    
    public void setAccounttype(Accounttype accounttype) {
        this.accounttype = accounttype;
    }

    
    @Column(name="username", unique=true, nullable=false, length=45)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="password", nullable=false, length=45)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="display_name", nullable=false, length=45)
    public String getDisplayName() {
        return this.displayName;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }




}


