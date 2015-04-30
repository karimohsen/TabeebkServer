package com.tabeebkServer.pojo;
// Generated Apr 28, 2015 12:25:04 PM by Hibernate Tools 3.6.0


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
 * Gender generated by hbm2java
 */
@Entity
@Table(name="gender"
    ,catalog="graduationprojecttabeebakdb"
)
public class Gender  implements java.io.Serializable {


     private Integer genderId;
     private String genderName;
     private String genderNameAr;
     private Set<User> users = new HashSet<User>(0);

    public Gender() {
    }

    public Gender(String genderName, String genderNameAr, Set<User> users) {
       this.genderName = genderName;
       this.genderNameAr = genderNameAr;
       this.users = users;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="gender_id", unique=true, nullable=false)
    public Integer getGenderId() {
        return this.genderId;
    }
    
    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    
    @Column(name="gender_name", length=10)
    public String getGenderName() {
        return this.genderName;
    }
    
    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    
    @Column(name="gender_name_ar", length=10)
    public String getGenderNameAr() {
        return this.genderNameAr;
    }
    
    public void setGenderNameAr(String genderNameAr) {
        this.genderNameAr = genderNameAr;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="gender")
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }




}


