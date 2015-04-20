package com.tabeebkServer.pojo;
// Generated Apr 19, 2015 2:31:24 PM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AccountId generated by hbm2java
 */
@Embeddable
public class AccountId  implements java.io.Serializable {


     private int accountTypeId;
     private int accountId;

    public AccountId() {
    }

    public AccountId(int accountTypeId, int accountId) {
       this.accountTypeId = accountTypeId;
       this.accountId = accountId;
    }
   


    @Column(name="accountType_id", nullable=false)
    public int getAccountTypeId() {
        return this.accountTypeId;
    }
    
    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }


    @Column(name="account_id", nullable=false)
    public int getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AccountId) ) return false;
		 AccountId castOther = ( AccountId ) other; 
         
		 return (this.getAccountTypeId()==castOther.getAccountTypeId())
 && (this.getAccountId()==castOther.getAccountId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getAccountTypeId();
         result = 37 * result + this.getAccountId();
         return result;
   }   


}


