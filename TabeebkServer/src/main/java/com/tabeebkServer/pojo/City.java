package com.tabeebkServer.pojo;
// Generated Jun 15, 2015 12:46:00 PM by Hibernate Tools 3.6.0


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
 * City generated by hbm2java
 */
@Entity
@Table(name="city"
    ,catalog="graduationprojecttabeebakdb"
)
public class City  implements java.io.Serializable {


     private Integer cityId;
     private String cityName;
     private String cityNameAr;
     private Set<Area> areas = new HashSet<Area>(0);
     private Set<Branche> branches = new HashSet<Branche>(0);

    public City() {
    }

	
    public City(String cityName, String cityNameAr) {
        this.cityName = cityName;
        this.cityNameAr = cityNameAr;
    }
    public City(String cityName, String cityNameAr, Set<Area> areas, Set<Branche> branches) {
       this.cityName = cityName;
       this.cityNameAr = cityNameAr;
       this.areas = areas;
       this.branches = branches;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="city_id", unique=true, nullable=false)
    public Integer getCityId() {
        return this.cityId;
    }
    
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    
    @Column(name="city_name", nullable=false, length=45)
    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    
    @Column(name="city_name_ar", nullable=false, length=45)
    public String getCityNameAr() {
        return this.cityNameAr;
    }
    
    public void setCityNameAr(String cityNameAr) {
        this.cityNameAr = cityNameAr;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="city")
    public Set<Area> getAreas() {
        return this.areas;
    }
    
    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="city")
    public Set<Branche> getBranches() {
        return this.branches;
    }
    
    public void setBranches(Set<Branche> branches) {
        this.branches = branches;
    }




}


