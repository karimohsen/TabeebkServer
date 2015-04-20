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
 * Socialnetwork generated by hbm2java
 */
@Entity
@Table(name="socialnetwork"
    ,catalog="graduationprojecttabeebakdb"
)
public class Socialnetwork  implements java.io.Serializable {


     private Integer socialnetworkId;
     private String socialnetworkName;
     private String socialnetworkNameAr;
     private Set<User> users = new HashSet<User>(0);

    public Socialnetwork() {
    }

    public Socialnetwork(String socialnetworkName, String socialnetworkNameAr, Set<User> users) {
       this.socialnetworkName = socialnetworkName;
       this.socialnetworkNameAr = socialnetworkNameAr;
       this.users = users;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="socialnetwork_id", unique=true, nullable=false)
    public Integer getSocialnetworkId() {
        return this.socialnetworkId;
    }
    
    public void setSocialnetworkId(Integer socialnetworkId) {
        this.socialnetworkId = socialnetworkId;
    }

    
    @Column(name="socialnetwork_name", length=45)
    public String getSocialnetworkName() {
        return this.socialnetworkName;
    }
    
    public void setSocialnetworkName(String socialnetworkName) {
        this.socialnetworkName = socialnetworkName;
    }

    
    @Column(name="socialnetwork_name_ar", length=45)
    public String getSocialnetworkNameAr() {
        return this.socialnetworkNameAr;
    }
    
    public void setSocialnetworkNameAr(String socialnetworkNameAr) {
        this.socialnetworkNameAr = socialnetworkNameAr;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="socialnetwork")
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }




}


