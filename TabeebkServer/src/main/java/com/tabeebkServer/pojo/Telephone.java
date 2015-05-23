package com.tabeebkServer.pojo;
// Generated May 23, 2015 9:23:29 AM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Telephone generated by hbm2java
 */
@Entity
@Table(name="telephone"
    ,catalog="graduationprojecttabeebakdb"
    , uniqueConstraints = @UniqueConstraint(columnNames="Telephone_number") 
)
public class Telephone  implements java.io.Serializable {


     private Integer telephoneId;
     private Msptype msptype;
     private Integer typeId;
     private String telephoneNumber;

    public Telephone() {
    }

    public Telephone(Msptype msptype, Integer typeId, String telephoneNumber) {
       this.msptype = msptype;
       this.typeId = typeId;
       this.telephoneNumber = telephoneNumber;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="telephone_id", unique=true, nullable=false)
    public Integer getTelephoneId() {
        return this.telephoneId;
    }
    
    public void setTelephoneId(Integer telephoneId) {
        this.telephoneId = telephoneId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="msptype_type_id")
    public Msptype getMsptype() {
        return this.msptype;
    }
    
    public void setMsptype(Msptype msptype) {
        this.msptype = msptype;
    }

    
    @Column(name="type_id")
    public Integer getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    
    @Column(name="Telephone_number", unique=true, length=15)
    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }
    
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }




}


