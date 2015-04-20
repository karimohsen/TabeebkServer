package com.tabeebkServer.pojo;
// Generated Apr 19, 2015 2:31:24 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Msptype generated by hbm2java
 */
@Entity
@Table(name="msptype"
    ,catalog="graduationprojecttabeebakdb"
)
public class Msptype  implements java.io.Serializable {


     private Integer typeId;
     private String typeName;
     private Set<Msp> msps = new HashSet<Msp>(0);
     private Set<Speciality> specialities = new HashSet<Speciality>(0);
     private Set<Branche> branches = new HashSet<Branche>(0);
     private Set<Ratting> rattings = new HashSet<Ratting>(0);
     private Set<Schedule> schedules = new HashSet<Schedule>(0);
     private Set<Planmsp> planmsps = new HashSet<Planmsp>(0);
     private Set<Telephone> telephones = new HashSet<Telephone>(0);

    public Msptype() {
    }

    public Msptype(String typeName, Set<Msp> msps, Set<Speciality> specialities, Set<Branche> branches, Set<Ratting> rattings, Set<Schedule> schedules, Set<Planmsp> planmsps, Set<Telephone> telephones) {
       this.typeName = typeName;
       this.msps = msps;
       this.specialities = specialities;
       this.branches = branches;
       this.rattings = rattings;
       this.schedules = schedules;
       this.planmsps = planmsps;
       this.telephones = telephones;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="type_id", unique=true, nullable=false)
    public Integer getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    
    @Column(name="type_name", length=45)
    public String getTypeName() {
        return this.typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="msptype")
    public Set<Msp> getMsps() {
        return this.msps;
    }
    
    public void setMsps(Set<Msp> msps) {
        this.msps = msps;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="msptype")
    public Set<Speciality> getSpecialities() {
        return this.specialities;
    }
    
    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="msptype")
    public Set<Branche> getBranches() {
        return this.branches;
    }
    
    public void setBranches(Set<Branche> branches) {
        this.branches = branches;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="msptype")
    public Set<Ratting> getRattings() {
        return this.rattings;
    }
    
    public void setRattings(Set<Ratting> rattings) {
        this.rattings = rattings;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="msptype")
    public Set<Schedule> getSchedules() {
        return this.schedules;
    }
    
    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="msptype")
    public Set<Planmsp> getPlanmsps() {
        return this.planmsps;
    }
    
    public void setPlanmsps(Set<Planmsp> planmsps) {
        this.planmsps = planmsps;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="msptype")
    public Set<Telephone> getTelephones() {
        return this.telephones;
    }
    
    public void setTelephones(Set<Telephone> telephones) {
        this.telephones = telephones;
    }




}


