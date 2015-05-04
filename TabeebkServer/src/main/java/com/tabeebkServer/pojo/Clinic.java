package com.tabeebkServer.pojo;
// Generated May 4, 2015 11:33:07 AM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Clinic generated by hbm2java
 */
@Entity
@Table(name="clinic"
    ,catalog="graduationprojecttabeebakdb"
)
public class Clinic  implements java.io.Serializable {


     private Integer clinicId;
     private String clinicName;
     private String clinicNameAr;
     private String clinicImagepath;
     private int deleted;
     private Set<Doctor> doctors = new HashSet<Doctor>(0);

    public Clinic() {
    }

	
    public Clinic(int deleted) {
        this.deleted = deleted;
    }
    public Clinic(String clinicName, String clinicNameAr, String clinicImagepath, int deleted, Set<Doctor> doctors) {
       this.clinicName = clinicName;
       this.clinicNameAr = clinicNameAr;
       this.clinicImagepath = clinicImagepath;
       this.deleted = deleted;
       this.doctors = doctors;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="clinic_id", unique=true, nullable=false)
    public Integer getClinicId() {
        return this.clinicId;
    }
    
    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    
    @Column(name="clinic_name", length=45)
    public String getClinicName() {
        return this.clinicName;
    }
    
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    
    @Column(name="clinic_name_ar", length=45)
    public String getClinicNameAr() {
        return this.clinicNameAr;
    }
    
    public void setClinicNameAr(String clinicNameAr) {
        this.clinicNameAr = clinicNameAr;
    }

    
    @Column(name="clinic_imagepath", length=45)
    public String getClinicImagepath() {
        return this.clinicImagepath;
    }
    
    public void setClinicImagepath(String clinicImagepath) {
        this.clinicImagepath = clinicImagepath;
    }

    
    @Column(name="deleted", nullable=false)
    public int getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

@ManyToMany(fetch=FetchType.LAZY, mappedBy="clinics")
    public Set<Doctor> getDoctors() {
        return this.doctors;
    }
    
    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }




}


