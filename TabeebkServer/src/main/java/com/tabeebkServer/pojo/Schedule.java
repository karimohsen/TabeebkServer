package com.tabeebkServer.pojo;
// Generated Apr 25, 2015 2:02:55 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Schedule generated by hbm2java
 */
@Entity
@Table(name="schedule"
    ,catalog="graduationprojecttabeebakdb"
)
public class Schedule  implements java.io.Serializable {


     private Integer scheduleId;
     private Msptype msptype;
     private String scheduleDay;
     private String scheduleDayAr;
     private Date scheduleFrom;
     private Date scheduleTo;
     private Integer typeId;

    public Schedule() {
    }

	
    public Schedule(String scheduleDay, Date scheduleFrom, Date scheduleTo) {
        this.scheduleDay = scheduleDay;
        this.scheduleFrom = scheduleFrom;
        this.scheduleTo = scheduleTo;
    }
    public Schedule(Msptype msptype, String scheduleDay, String scheduleDayAr, Date scheduleFrom, Date scheduleTo, Integer typeId) {
       this.msptype = msptype;
       this.scheduleDay = scheduleDay;
       this.scheduleDayAr = scheduleDayAr;
       this.scheduleFrom = scheduleFrom;
       this.scheduleTo = scheduleTo;
       this.typeId = typeId;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="schedule_id", unique=true, nullable=false)
    public Integer getScheduleId() {
        return this.scheduleId;
    }
    
    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="msptype_type_id")
    public Msptype getMsptype() {
        return this.msptype;
    }
    
    public void setMsptype(Msptype msptype) {
        this.msptype = msptype;
    }

    
    @Column(name="schedule_day", nullable=false, length=45)
    public String getScheduleDay() {
        return this.scheduleDay;
    }
    
    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    
    @Column(name="schedule_day_ar", length=45)
    public String getScheduleDayAr() {
        return this.scheduleDayAr;
    }
    
    public void setScheduleDayAr(String scheduleDayAr) {
        this.scheduleDayAr = scheduleDayAr;
    }

    @Temporal(TemporalType.TIME)
    @Column(name="schedule_from", nullable=false, length=8)
    public Date getScheduleFrom() {
        return this.scheduleFrom;
    }
    
    public void setScheduleFrom(Date scheduleFrom) {
        this.scheduleFrom = scheduleFrom;
    }

    @Temporal(TemporalType.TIME)
    @Column(name="schedule_to", nullable=false, length=8)
    public Date getScheduleTo() {
        return this.scheduleTo;
    }
    
    public void setScheduleTo(Date scheduleTo) {
        this.scheduleTo = scheduleTo;
    }

    
    @Column(name="type_id")
    public Integer getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }




}


