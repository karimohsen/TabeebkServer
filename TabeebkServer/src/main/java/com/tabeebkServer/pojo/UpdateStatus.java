package com.tabeebkServer.pojo;
// Generated May 24, 2015 9:40:13 AM by Hibernate Tools 4.3.1


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
 * UpdateStatus generated by hbm2java
 */
@Entity
@Table(name="update_status"
    ,catalog="graduationprojecttabeebakdb"
)
public class UpdateStatus  implements java.io.Serializable {


     private Integer id;
     private String status;
     private Set<Planupdates> planupdateses = new HashSet<Planupdates>(0);

    public UpdateStatus() {
    }

    public UpdateStatus(String status, Set<Planupdates> planupdateses) {
       this.status = status;
       this.planupdateses = planupdateses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="status", length=45)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="updateStatus")
    public Set<Planupdates> getPlanupdateses() {
        return this.planupdateses;
    }
    
    public void setPlanupdateses(Set<Planupdates> planupdateses) {
        this.planupdateses = planupdateses;
    }




}


