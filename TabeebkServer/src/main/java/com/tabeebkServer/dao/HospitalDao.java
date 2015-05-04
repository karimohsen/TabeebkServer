/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Hospital;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class HospitalDao {
    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();
    
    public static void addHospital(Hospital h){
        if(!session.getTransaction().isActive())
            session.beginTransaction();
        session.save(h);
        session.getTransaction().commit();
    }
    
}
