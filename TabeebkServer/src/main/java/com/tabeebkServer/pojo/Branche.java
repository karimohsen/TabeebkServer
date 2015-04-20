package com.tabeebkServer.pojo;
// Generated Apr 19, 2015 2:31:24 PM by Hibernate Tools 3.6.0


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
 * Branche generated by hbm2java
 */
@Entity
@Table(name="branche"
    ,catalog="graduationprojecttabeebakdb"
)
public class Branche  implements java.io.Serializable {


     private Integer brancheId;
     private Msptype msptype;
     private City city;
     private Mspimage mspimage;
     private Country country;
     private String brancheName;
     private String brancheNameAr;
     private String brancheAddress;
     private String brancheAddressAr;
     private String brancheLongtitude;
     private String brancheLatatitude;
     private Integer typeId;
     private String brancheStreet;
     private String brancheStreetAr;

    public Branche() {
    }

	
    public Branche(String brancheName) {
        this.brancheName = brancheName;
    }
    public Branche(Msptype msptype, City city, Mspimage mspimage, Country country, String brancheName, String brancheNameAr, String brancheAddress, String brancheAddressAr, String brancheLongtitude, String brancheLatatitude, Integer typeId, String brancheStreet, String brancheStreetAr) {
       this.msptype = msptype;
       this.city = city;
       this.mspimage = mspimage;
       this.country = country;
       this.brancheName = brancheName;
       this.brancheNameAr = brancheNameAr;
       this.brancheAddress = brancheAddress;
       this.brancheAddressAr = brancheAddressAr;
       this.brancheLongtitude = brancheLongtitude;
       this.brancheLatatitude = brancheLatatitude;
       this.typeId = typeId;
       this.brancheStreet = brancheStreet;
       this.brancheStreetAr = brancheStreetAr;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="branche_id", unique=true, nullable=false)
    public Integer getBrancheId() {
        return this.brancheId;
    }
    
    public void setBrancheId(Integer brancheId) {
        this.brancheId = brancheId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="msptype_type_id")
    public Msptype getMsptype() {
        return this.msptype;
    }
    
    public void setMsptype(Msptype msptype) {
        this.msptype = msptype;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="city_id")
    public City getCity() {
        return this.city;
    }
    
    public void setCity(City city) {
        this.city = city;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="image_id")
    public Mspimage getMspimage() {
        return this.mspimage;
    }
    
    public void setMspimage(Mspimage mspimage) {
        this.mspimage = mspimage;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="country_id")
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }

    
    @Column(name="branche_name", nullable=false, length=45)
    public String getBrancheName() {
        return this.brancheName;
    }
    
    public void setBrancheName(String brancheName) {
        this.brancheName = brancheName;
    }

    
    @Column(name="branche_name_ar", length=45)
    public String getBrancheNameAr() {
        return this.brancheNameAr;
    }
    
    public void setBrancheNameAr(String brancheNameAr) {
        this.brancheNameAr = brancheNameAr;
    }

    
    @Column(name="branche_address", length=100)
    public String getBrancheAddress() {
        return this.brancheAddress;
    }
    
    public void setBrancheAddress(String brancheAddress) {
        this.brancheAddress = brancheAddress;
    }

    
    @Column(name="branche_address_ar", length=100)
    public String getBrancheAddressAr() {
        return this.brancheAddressAr;
    }
    
    public void setBrancheAddressAr(String brancheAddressAr) {
        this.brancheAddressAr = brancheAddressAr;
    }

    
    @Column(name="branche_longtitude", length=45)
    public String getBrancheLongtitude() {
        return this.brancheLongtitude;
    }
    
    public void setBrancheLongtitude(String brancheLongtitude) {
        this.brancheLongtitude = brancheLongtitude;
    }

    
    @Column(name="branche_latatitude", length=45)
    public String getBrancheLatatitude() {
        return this.brancheLatatitude;
    }
    
    public void setBrancheLatatitude(String brancheLatatitude) {
        this.brancheLatatitude = brancheLatatitude;
    }

    
    @Column(name="type_id")
    public Integer getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    
    @Column(name="branche_street", length=100)
    public String getBrancheStreet() {
        return this.brancheStreet;
    }
    
    public void setBrancheStreet(String brancheStreet) {
        this.brancheStreet = brancheStreet;
    }

    
    @Column(name="branche_street_ar", length=45)
    public String getBrancheStreetAr() {
        return this.brancheStreetAr;
    }
    
    public void setBrancheStreetAr(String brancheStreetAr) {
        this.brancheStreetAr = brancheStreetAr;
    }




}


