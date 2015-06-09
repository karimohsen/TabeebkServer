package com.tabeebkServer.pojo;
// Generated Jun 8, 2015 8:12:48 PM by Hibernate Tools 3.6.0


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
import javax.persistence.UniqueConstraint;

/**
 * Hospital generated by hbm2java
 */
@Entity
@Table(name="hospital"
    ,catalog="graduationprojecttabeebakdb"
    , uniqueConstraints = @UniqueConstraint(columnNames="hospital_name") 
)
public class Hospital  implements java.io.Serializable {


     private Integer hospitalId;
     private String hospitalName;
     private String hospitalNameAr;
     private byte[] hospitalImagepath;
     private int deleted;
     private Set<Lab> labs = new HashSet<Lab>(0);
     private Set<Hospitalspeciality> hospitalspecialities = new HashSet<Hospitalspeciality>(0);
     private Set<Pharamacy> pharamacies = new HashSet<Pharamacy>(0);

    public Hospital() {
    }

	
    public Hospital(int deleted) {
        this.deleted = deleted;
    }
    public Hospital(String hospitalName, String hospitalNameAr, byte[] hospitalImagepath, int deleted, Set<Lab> labs, Set<Hospitalspeciality> hospitalspecialities, Set<Pharamacy> pharamacies) {
       this.hospitalName = hospitalName;
       this.hospitalNameAr = hospitalNameAr;
       this.hospitalImagepath = hospitalImagepath;
       this.deleted = deleted;
       this.labs = labs;
       this.hospitalspecialities = hospitalspecialities;
       this.pharamacies = pharamacies;
    }
   
    public Hospital(String hospitalName, String hospitalNameAr, int deleted) {
       this.hospitalName = hospitalName;
       this.hospitalNameAr = hospitalNameAr;
       this.deleted = deleted;
    }
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="hospital_id", unique=true, nullable=false)
    public Integer getHospitalId() {
        return this.hospitalId;
    }
    
    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    
    @Column(name="hospital_name", unique=true, length=45)
    public String getHospitalName() {
        return this.hospitalName;
    }
    
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    
    @Column(name="hospital_name_ar", length=45)
    public String getHospitalNameAr() {
        return this.hospitalNameAr;
    }
    
    public void setHospitalNameAr(String hospitalNameAr) {
        this.hospitalNameAr = hospitalNameAr;
    }

    
    @Column(name="hospital_imagepath")
    public byte[] getHospitalImagepath() {
        return this.hospitalImagepath;
    }
    
    public void setHospitalImagepath(byte[] hospitalImagepath) {
        this.hospitalImagepath = hospitalImagepath;
    }

    
    @Column(name="deleted", nullable=false)
    public int getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="hospital")
    public Set<Lab> getLabs() {
        return this.labs;
    }
    
    public void setLabs(Set<Lab> labs) {
        this.labs = labs;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="hospital")
    public Set<Hospitalspeciality> getHospitalspecialities() {
        return this.hospitalspecialities;
    }
    
    public void setHospitalspecialities(Set<Hospitalspeciality> hospitalspecialities) {
        this.hospitalspecialities = hospitalspecialities;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="hospital")
    public Set<Pharamacy> getPharamacies() {
        return this.pharamacies;
    }
    
    public void setPharamacies(Set<Pharamacy> pharamacies) {
        this.pharamacies = pharamacies;
    }




}


