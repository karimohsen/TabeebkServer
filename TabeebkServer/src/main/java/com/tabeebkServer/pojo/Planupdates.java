package com.tabeebkServer.pojo;
<<<<<<< HEAD
// Generated May 24, 2015 9:40:13 AM by Hibernate Tools 4.3.1
=======
// Generated May 23, 2015 9:23:29 AM by Hibernate Tools 3.6.0
>>>>>>> 6689aef6dda3c5207775e14ccd6ef12c7e2f185a


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Planupdates generated by hbm2java
 */
@Entity
@Table(name="planupdates"
    ,catalog="graduationprojecttabeebakdb"
)
public class Planupdates  implements java.io.Serializable {


     private Integer updateId;
     private int version;
<<<<<<< HEAD
     private Msp msp;
     private Msptype msptype;
     private Plan plan;
     private UpdateStatus updateStatus;
=======
     private Msptype msptype;
     private UpdateStatus updateStatus;
     private Msp msp;
     private Plan plan;
>>>>>>> 6689aef6dda3c5207775e14ccd6ef12c7e2f185a

    public Planupdates() {
    }

	
<<<<<<< HEAD
    public Planupdates(Plan plan, UpdateStatus updateStatus) {
        this.plan = plan;
        this.updateStatus = updateStatus;
    }
    public Planupdates(Msp msp, Msptype msptype, Plan plan, UpdateStatus updateStatus) {
       this.msp = msp;
       this.msptype = msptype;
       this.plan = plan;
       this.updateStatus = updateStatus;
=======
    public Planupdates(UpdateStatus updateStatus, Plan plan) {
        this.updateStatus = updateStatus;
        this.plan = plan;
    }
    public Planupdates(Msptype msptype, UpdateStatus updateStatus, Msp msp, Plan plan) {
       this.msptype = msptype;
       this.updateStatus = updateStatus;
       this.msp = msp;
       this.plan = plan;
>>>>>>> 6689aef6dda3c5207775e14ccd6ef12c7e2f185a
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="update_id", unique=true, nullable=false)
    public Integer getUpdateId() {
        return this.updateId;
    }
    
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

<<<<<<< HEAD
    @Version
=======
    
>>>>>>> 6689aef6dda3c5207775e14ccd6ef12c7e2f185a
    @Column(name="version", nullable=false)
    public int getVersion() {
        return this.version;
    }
    
    public void setVersion(int version) {
        this.version = version;
    }

@ManyToOne(fetch=FetchType.LAZY)
<<<<<<< HEAD
    @JoinColumn(name="msp_id")
    public Msp getMsp() {
        return this.msp;
    }
    
    public void setMsp(Msp msp) {
        this.msp = msp;
    }

@ManyToOne(fetch=FetchType.LAZY)
=======
>>>>>>> 6689aef6dda3c5207775e14ccd6ef12c7e2f185a
    @JoinColumn(name="msp_type_id")
    public Msptype getMsptype() {
        return this.msptype;
    }
    
    public void setMsptype(Msptype msptype) {
        this.msptype = msptype;
    }

@ManyToOne(fetch=FetchType.LAZY)
<<<<<<< HEAD
    @JoinColumn(name="plan_id", nullable=false)
    public Plan getPlan() {
        return this.plan;
    }
    
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

@ManyToOne(fetch=FetchType.LAZY)
=======
>>>>>>> 6689aef6dda3c5207775e14ccd6ef12c7e2f185a
    @JoinColumn(name="status", nullable=false)
    public UpdateStatus getUpdateStatus() {
        return this.updateStatus;
    }
    
    public void setUpdateStatus(UpdateStatus updateStatus) {
        this.updateStatus = updateStatus;
    }

<<<<<<< HEAD
=======
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="msp_id")
    public Msp getMsp() {
        return this.msp;
    }
    
    public void setMsp(Msp msp) {
        this.msp = msp;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="plan_id", nullable=false)
    public Plan getPlan() {
        return this.plan;
    }
    
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

>>>>>>> 6689aef6dda3c5207775e14ccd6ef12c7e2f185a



}


