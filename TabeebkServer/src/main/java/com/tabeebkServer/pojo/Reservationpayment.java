package com.tabeebkServer.pojo;
<<<<<<< HEAD
// Generated May 24, 2015 9:40:13 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
=======
// Generated May 23, 2015 9:23:29 AM by Hibernate Tools 3.6.0


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
>>>>>>> 6689aef6dda3c5207775e14ccd6ef12c7e2f185a

/**
 * Reservationpayment generated by hbm2java
 */
@Entity
@Table(name="reservationpayment"
    ,catalog="graduationprojecttabeebakdb"
)
public class Reservationpayment  implements java.io.Serializable {


     private Integer id;
     private String paymentEn;
     private String paymentAr;
<<<<<<< HEAD
=======
     private Set<Reservation> reservations = new HashSet<Reservation>(0);
>>>>>>> 6689aef6dda3c5207775e14ccd6ef12c7e2f185a

    public Reservationpayment() {
    }

<<<<<<< HEAD
    public Reservationpayment(String paymentEn, String paymentAr) {
       this.paymentEn = paymentEn;
       this.paymentAr = paymentAr;
=======
    public Reservationpayment(String paymentEn, String paymentAr, Set<Reservation> reservations) {
       this.paymentEn = paymentEn;
       this.paymentAr = paymentAr;
       this.reservations = reservations;
>>>>>>> 6689aef6dda3c5207775e14ccd6ef12c7e2f185a
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="payment_en", length=45)
    public String getPaymentEn() {
        return this.paymentEn;
    }
    
    public void setPaymentEn(String paymentEn) {
        this.paymentEn = paymentEn;
    }

    
    @Column(name="payment_ar", length=45)
    public String getPaymentAr() {
        return this.paymentAr;
    }
    
    public void setPaymentAr(String paymentAr) {
        this.paymentAr = paymentAr;
    }

<<<<<<< HEAD
=======
@OneToMany(fetch=FetchType.LAZY, mappedBy="reservationpayment")
    public Set<Reservation> getReservations() {
        return this.reservations;
    }
    
    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

>>>>>>> 6689aef6dda3c5207775e14ccd6ef12c7e2f185a



}


