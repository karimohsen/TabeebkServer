package com.tabeebkServer.pojo;
// Generated May 2, 2015 1:54:41 AM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Msprecommendations generated by hbm2java
 */
@Entity
@Table(name="msprecommendations"
    ,catalog="graduationprojecttabeebakdb"
)
public class Msprecommendations  implements java.io.Serializable {


     private Integer mspRecommendationsId;
     private User user;
     private Msptype msptype;
     private String mspName;
     private String mspPhone;
     private String mspCountry;
     private String mspCity;
     private String mspArea;
     private String mspStreetName;
     private String mspNotes;

    public Msprecommendations() {
    }

	
    public Msprecommendations(User user, Msptype msptype, String mspName, String mspPhone) {
        this.user = user;
        this.msptype = msptype;
        this.mspName = mspName;
        this.mspPhone = mspPhone;
    }
    public Msprecommendations(User user, Msptype msptype, String mspName, String mspPhone, String mspCountry, String mspCity, String mspArea, String mspStreetName, String mspNotes) {
       this.user = user;
       this.msptype = msptype;
       this.mspName = mspName;
       this.mspPhone = mspPhone;
       this.mspCountry = mspCountry;
       this.mspCity = mspCity;
       this.mspArea = mspArea;
       this.mspStreetName = mspStreetName;
       this.mspNotes = mspNotes;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="Msp_recommendations_id", unique=true, nullable=false)
    public Integer getMspRecommendationsId() {
        return this.mspRecommendationsId;
    }
    
    public void setMspRecommendationsId(Integer mspRecommendationsId) {
        this.mspRecommendationsId = mspRecommendationsId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="msptype_type_id", nullable=false)
    public Msptype getMsptype() {
        return this.msptype;
    }
    
    public void setMsptype(Msptype msptype) {
        this.msptype = msptype;
    }

    
    @Column(name="Msp_Name", nullable=false, length=45)
    public String getMspName() {
        return this.mspName;
    }
    
    public void setMspName(String mspName) {
        this.mspName = mspName;
    }

    
    @Column(name="Msp_phone", nullable=false, length=15)
    public String getMspPhone() {
        return this.mspPhone;
    }
    
    public void setMspPhone(String mspPhone) {
        this.mspPhone = mspPhone;
    }

    
    @Column(name="Msp_country", length=45)
    public String getMspCountry() {
        return this.mspCountry;
    }
    
    public void setMspCountry(String mspCountry) {
        this.mspCountry = mspCountry;
    }

    
    @Column(name="Msp_city", length=45)
    public String getMspCity() {
        return this.mspCity;
    }
    
    public void setMspCity(String mspCity) {
        this.mspCity = mspCity;
    }

    
    @Column(name="Msp_area", length=45)
    public String getMspArea() {
        return this.mspArea;
    }
    
    public void setMspArea(String mspArea) {
        this.mspArea = mspArea;
    }

    
    @Column(name="Msp_street_name", length=45)
    public String getMspStreetName() {
        return this.mspStreetName;
    }
    
    public void setMspStreetName(String mspStreetName) {
        this.mspStreetName = mspStreetName;
    }

    
    @Column(name="Msp_notes", length=45)
    public String getMspNotes() {
        return this.mspNotes;
    }
    
    public void setMspNotes(String mspNotes) {
        this.mspNotes = mspNotes;
    }




}

