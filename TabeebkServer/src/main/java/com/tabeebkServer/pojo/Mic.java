package com.tabeebkServer.pojo;
// Generated Apr 25, 2015 2:02:55 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Mic generated by hbm2java
 */
@Entity
@Table(name="mic"
    ,catalog="graduationprojecttabeebakdb"
)
public class Mic  implements java.io.Serializable {


     private Integer micId;
     private String micName;
     private String micNameAr;
     private String micDescription;
     private String micDescriptionAr;
     private String micUrl;
     private String micEmail;
     private String micImageurl;
     private int deleted;
     private Set<Micuser> micusers = new HashSet<Micuser>(0);
     private Set<Plan> plans = new HashSet<Plan>(0);
     private Set<Msp> msps = new HashSet<Msp>(0);
     private Set<Micratting> micrattings = new HashSet<Micratting>(0);

    public Mic() {
    }

	
    public Mic(int deleted) {
        this.deleted = deleted;
    }
    public Mic(String micName, String micNameAr, String micDescription, String micDescriptionAr, String micUrl, String micEmail, String micImageurl, int deleted, Set<Micuser> micusers, Set<Plan> plans, Set<Msp> msps, Set<Micratting> micrattings) {
       this.micName = micName;
       this.micNameAr = micNameAr;
       this.micDescription = micDescription;
       this.micDescriptionAr = micDescriptionAr;
       this.micUrl = micUrl;
       this.micEmail = micEmail;
       this.micImageurl = micImageurl;
       this.deleted = deleted;
       this.micusers = micusers;
       this.plans = plans;
       this.msps = msps;
       this.micrattings = micrattings;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="mic_id", unique=true, nullable=false)
    public Integer getMicId() {
        return this.micId;
    }
    
    public void setMicId(Integer micId) {
        this.micId = micId;
    }

    
    @Column(name="mic_name", length=45)
    public String getMicName() {
        return this.micName;
    }
    
    public void setMicName(String micName) {
        this.micName = micName;
    }

    
    @Column(name="mic_name_ar", length=45)
    public String getMicNameAr() {
        return this.micNameAr;
    }
    
    public void setMicNameAr(String micNameAr) {
        this.micNameAr = micNameAr;
    }

    
    @Column(name="mic_description", length=45)
    public String getMicDescription() {
        return this.micDescription;
    }
    
    public void setMicDescription(String micDescription) {
        this.micDescription = micDescription;
    }

    
    @Column(name="mic_description_ar", length=45)
    public String getMicDescriptionAr() {
        return this.micDescriptionAr;
    }
    
    public void setMicDescriptionAr(String micDescriptionAr) {
        this.micDescriptionAr = micDescriptionAr;
    }

    
    @Column(name="mic_url", length=45)
    public String getMicUrl() {
        return this.micUrl;
    }
    
    public void setMicUrl(String micUrl) {
        this.micUrl = micUrl;
    }

    
    @Column(name="mic_email", length=45)
    public String getMicEmail() {
        return this.micEmail;
    }
    
    public void setMicEmail(String micEmail) {
        this.micEmail = micEmail;
    }

    
    @Column(name="mic_imageurl", length=45)
    public String getMicImageurl() {
        return this.micImageurl;
    }
    
    public void setMicImageurl(String micImageurl) {
        this.micImageurl = micImageurl;
    }

    
    @Column(name="deleted", nullable=false)
    public int getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="mic")
    public Set<Micuser> getMicusers() {
        return this.micusers;
    }
    
    public void setMicusers(Set<Micuser> micusers) {
        this.micusers = micusers;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="mic")
    public Set<Plan> getPlans() {
        return this.plans;
    }
    
    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }

@ManyToMany(fetch=FetchType.LAZY, mappedBy="mics")
    public Set<Msp> getMsps() {
        return this.msps;
    }
    
    public void setMsps(Set<Msp> msps) {
        this.msps = msps;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="mic")
    public Set<Micratting> getMicrattings() {
        return this.micrattings;
    }
    
    public void setMicrattings(Set<Micratting> micrattings) {
        this.micrattings = micrattings;
    }




}


