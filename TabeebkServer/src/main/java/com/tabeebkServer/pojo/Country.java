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

/**
 * Country generated by hbm2java
 */
@Entity
@Table(name="country"
    ,catalog="graduationprojecttabeebakdb"
)
public class Country  implements java.io.Serializable {


     private Integer countryId;
     private String countryName;
     private String counteryNameAr;
     private Set<Branche> branches = new HashSet<Branche>(0);

    public Country() {
    }

    public Country(String countryName, String counteryNameAr, Set<Branche> branches) {
       this.countryName = countryName;
       this.counteryNameAr = counteryNameAr;
       this.branches = branches;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="country_id", unique=true, nullable=false)
    public Integer getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    
    @Column(name="country_name", length=45)
    public String getCountryName() {
        return this.countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    
    @Column(name="countery_name_ar", length=45)
    public String getCounteryNameAr() {
        return this.counteryNameAr;
    }
    
    public void setCounteryNameAr(String counteryNameAr) {
        this.counteryNameAr = counteryNameAr;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="country")
    public Set<Branche> getBranches() {
        return this.branches;
    }
    
    public void setBranches(Set<Branche> branches) {
        this.branches = branches;
    }




}


