package com.tabeebkServer.pojo;
// Generated Jun 8, 2015 8:12:48 PM by Hibernate Tools 3.6.0


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Planmsp generated by hbm2java
 */
@Entity
@Table(name="planmsp"
    ,catalog="graduationprojecttabeebakdb"
)
public class Planmsp  implements java.io.Serializable {


     private PlanmspId id;
     private Msptype msptype;
     private Plan plan;
     private Integer deleted;

    public Planmsp() {
    }

	
    public Planmsp(PlanmspId id, Msptype msptype, Plan plan) {
        this.id = id;
        this.msptype = msptype;
        this.plan = plan;
    }
    public Planmsp(PlanmspId id, Msptype msptype, Plan plan, Integer deleted) {
       this.id = id;
       this.msptype = msptype;
       this.plan = plan;
       this.deleted = deleted;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="planId", column=@Column(name="plan_id", nullable=false) ), 
        @AttributeOverride(name="msptypeTypeId", column=@Column(name="msptype_type_id", nullable=false) ), 
        @AttributeOverride(name="typeId", column=@Column(name="type_id", nullable=false) ) } )
    public PlanmspId getId() {
        return this.id;
    }
    
    public void setId(PlanmspId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="msptype_type_id", nullable=false, insertable=false, updatable=false)
    public Msptype getMsptype() {
        return this.msptype;
    }
    
    public void setMsptype(Msptype msptype) {
        this.msptype = msptype;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="plan_id", nullable=false, insertable=false, updatable=false)
    public Plan getPlan() {
        return this.plan;
    }
    
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    
    @Column(name="deleted")
    public Integer getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }




}


