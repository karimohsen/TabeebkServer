/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Clinic;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class ClinicDao {
    static SessionFactory fact = HibernateUtilFactory.getSessionFactory();
    static Session session = fact.openSession();
    public static int addClinic(Clinic clinic){
        session.clear();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(clinic);
        session.getTransaction().commit();
        return clinic.getClinicId();
    }
    
    public static ArrayList<Clinic> getAllClinics(){
        session.clear();
        return (ArrayList<Clinic>) session.createQuery("From Clinic c where c.deleted = 0").list();
    }
    
    public static void updateClinic(int clinicId,String name,String nameAr){
        Clinic c = (Clinic) session.get(Clinic.class, clinicId);
        c.setClinicName(name);
        c.setClinicNameAr(nameAr);
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.update(c);
        session.getTransaction().commit();
    }
    
    public static Clinic getClinicData(int id){
        session.clear();
        return (Clinic) session.get(Clinic.class, id);
    }
}
