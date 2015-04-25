package com.tabeebkServer.pojo;
// Generated Apr 25, 2015 12:56:11 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user"
    ,catalog="graduationprojecttabeebakdb"
    , uniqueConstraints = {@UniqueConstraint(columnNames="user_email"), @UniqueConstraint(columnNames="user_token")} 
)
public class User  implements java.io.Serializable {


     private Integer userId;
     private Gender gender;
     private Socialnetwork socialnetwork;
     private String userName;
     private String userNameAr;
     private String userEmail;
     private String userImage;
     private String userTelephone;
     private String userToken;
     private Date userDateofbirth;
     private int blocked;
     private Set<Micratting> micrattings = new HashSet<Micratting>(0);
     private Set<Ratting> rattings = new HashSet<Ratting>(0);
     private Set<Micuser> micusers = new HashSet<Micuser>(0);

    public User() {
    }

	
    public User(Socialnetwork socialnetwork, String userEmail, int blocked) {
        this.socialnetwork = socialnetwork;
        this.userEmail = userEmail;
        this.blocked = blocked;
    }
    public User(Gender gender, Socialnetwork socialnetwork, String userName, String userNameAr, String userEmail, String userImage, String userTelephone, String userToken, Date userDateofbirth, int blocked, Set<Micratting> micrattings, Set<Ratting> rattings, Set<Micuser> micusers) {
       this.gender = gender;
       this.socialnetwork = socialnetwork;
       this.userName = userName;
       this.userNameAr = userNameAr;
       this.userEmail = userEmail;
       this.userImage = userImage;
       this.userTelephone = userTelephone;
       this.userToken = userToken;
       this.userDateofbirth = userDateofbirth;
       this.blocked = blocked;
       this.micrattings = micrattings;
       this.rattings = rattings;
       this.micusers = micusers;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="user_id", unique=true, nullable=false)
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gender_id")
    public Gender getGender() {
        return this.gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Social_id", nullable=false)
    public Socialnetwork getSocialnetwork() {
        return this.socialnetwork;
    }
    
    public void setSocialnetwork(Socialnetwork socialnetwork) {
        this.socialnetwork = socialnetwork;
    }

    
    @Column(name="user_name", length=45)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    @Column(name="user_name_ar", length=45)
    public String getUserNameAr() {
        return this.userNameAr;
    }
    
    public void setUserNameAr(String userNameAr) {
        this.userNameAr = userNameAr;
    }

    
    @Column(name="user_email", unique=true, nullable=false, length=45)
    public String getUserEmail() {
        return this.userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    
    @Column(name="user_image", length=45)
    public String getUserImage() {
        return this.userImage;
    }
    
    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    
    @Column(name="user_telephone", length=15)
    public String getUserTelephone() {
        return this.userTelephone;
    }
    
    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    
    @Column(name="user_token", unique=true, length=100)
    public String getUserToken() {
        return this.userToken;
    }
    
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="user_dateofbirth", length=10)
    public Date getUserDateofbirth() {
        return this.userDateofbirth;
    }
    
    public void setUserDateofbirth(Date userDateofbirth) {
        this.userDateofbirth = userDateofbirth;
    }

    
    @Column(name="blocked", nullable=false)
    public int getBlocked() {
        return this.blocked;
    }
    
    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Micratting> getMicrattings() {
        return this.micrattings;
    }
    
    public void setMicrattings(Set<Micratting> micrattings) {
        this.micrattings = micrattings;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Ratting> getRattings() {
        return this.rattings;
    }
    
    public void setRattings(Set<Ratting> rattings) {
        this.rattings = rattings;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Micuser> getMicusers() {
        return this.micusers;
    }
    
    public void setMicusers(Set<Micuser> micusers) {
        this.micusers = micusers;
    }




}


