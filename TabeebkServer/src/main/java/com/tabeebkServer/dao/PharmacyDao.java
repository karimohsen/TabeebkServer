/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Hospital;
import com.tabeebkServer.pojo.Pharamacy;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class PharmacyDao {

    static SessionFactory fact = HibernateUtilFactory.getSessionFactory();
    static Session session = fact.openSession();

    public static int addPharmacy(Pharamacy p, int hospitalId) {
        if (hospitalId != -2) {
            p.setHospital((Hospital) session.get(Hospital.class, hospitalId));
        }
        session.clear();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(p);
        session.getTransaction().commit();
        return p.getPharamacyId();
    }
    
    public static void updatePharmacy(int id,int hospitalId,String name,String nameAr){
        Pharamacy p = (Pharamacy)session.get(Pharamacy.class, id);
        if (hospitalId != -2) {
            p.setHospital((Hospital) session.get(Hospital.class, hospitalId));
        }
        p.setPharamacyName(name);
        p.setPharamacyNameAr(nameAr);
        session.clear();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.update(p);
        session.getTransaction().commit();
    }
    
    public static Pharamacy getPharmacyDetails(int id){
        session.clear();
        return (Pharamacy)session.get(Pharamacy.class, id);
    }
}
