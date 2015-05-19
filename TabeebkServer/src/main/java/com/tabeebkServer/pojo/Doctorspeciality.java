package com.tabeebkServer.pojo;
// Generated May 15, 2015 3:04:27 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Objects;
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
 * Doctorspeciality generated by hbm2java
 */
@Entity
@Table(name="doctorspeciality"
    ,catalog="graduationprojecttabeebakdb"
)
public class Doctorspeciality  implements java.io.Serializable {


     private Integer doctorSpecialityId;
     private String doctorSpecialityName;
     private String doctorSpecialityNameAr;
     private String doctorSpecialityDescription;
     private String doctorSpecialityDescriptionAr;
     private Set<Hospitalspeciality> hospitalspecialities = new HashSet<Hospitalspeciality>(0);
     private Set<Doctor> doctors = new HashSet<Doctor>(0);

    public Doctorspeciality() {
    }

    public Doctorspeciality(String doctorSpecialityName, String doctorSpecialityNameAr, String doctorSpecialityDescription, String doctorSpecialityDescriptionAr, Set<Hospitalspeciality> hospitalspecialities, Set<Doctor> doctors) {
       this.doctorSpecialityName = doctorSpecialityName;
       this.doctorSpecialityNameAr = doctorSpecialityNameAr;
       this.doctorSpecialityDescription = doctorSpecialityDescription;
       this.doctorSpecialityDescriptionAr = doctorSpecialityDescriptionAr;
       this.hospitalspecialities = hospitalspecialities;
       this.doctors = doctors;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="doctorSpeciality_id", unique=true, nullable=false)
    public Integer getDoctorSpecialityId() {
        return this.doctorSpecialityId;
    }
    
    public void setDoctorSpecialityId(Integer doctorSpecialityId) {
        this.doctorSpecialityId = doctorSpecialityId;
    }

    
    @Column(name="doctorSpeciality_name", length=45)
    public String getDoctorSpecialityName() {
        return this.doctorSpecialityName;
    }
    
    public void setDoctorSpecialityName(String doctorSpecialityName) {
        this.doctorSpecialityName = doctorSpecialityName;
    }

    
    @Column(name="doctorSpeciality_name_ar", length=45)
    public String getDoctorSpecialityNameAr() {
        return this.doctorSpecialityNameAr;
    }
    
    public void setDoctorSpecialityNameAr(String doctorSpecialityNameAr) {
        this.doctorSpecialityNameAr = doctorSpecialityNameAr;
    }

    
    @Column(name="doctorSpeciality_description", length=200)
    public String getDoctorSpecialityDescription() {
        return this.doctorSpecialityDescription;
    }
    
    public void setDoctorSpecialityDescription(String doctorSpecialityDescription) {
        this.doctorSpecialityDescription = doctorSpecialityDescription;
    }

    
    @Column(name="doctorSpeciality_description_ar", length=200)
    public String getDoctorSpecialityDescriptionAr() {
        return this.doctorSpecialityDescriptionAr;
    }
    
    public void setDoctorSpecialityDescriptionAr(String doctorSpecialityDescriptionAr) {
        this.doctorSpecialityDescriptionAr = doctorSpecialityDescriptionAr;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="doctorspeciality")
    public Set<Hospitalspeciality> getHospitalspecialities() {
        return this.hospitalspecialities;
    }
    
    public void setHospitalspecialities(Set<Hospitalspeciality> hospitalspecialities) {
        this.hospitalspecialities = hospitalspecialities;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="doctorspeciality")
    public Set<Doctor> getDoctors() {
        return this.doctors;
    }
    
    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

}


