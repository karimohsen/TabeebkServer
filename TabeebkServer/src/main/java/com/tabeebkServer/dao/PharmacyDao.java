/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import static com.tabeebkServer.dao.LabDao.session;
import com.tabeebkServer.pojo.Hospital;
import com.tabeebkServer.pojo.Pharamacy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class PharmacyDao {

    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
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
}
