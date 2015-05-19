/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Doctorspeciality;
import com.tabeebkServer.pojo.Hospital;
import com.tabeebkServer.pojo.Hospitalspeciality;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class HospitalSpecialityDao {

    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    public static ArrayList<Doctorspeciality> getHospitalspecialities() {
        session.clear();
        ArrayList<Doctorspeciality> list = new ArrayList<>();
        list = (ArrayList<Doctorspeciality>) session.createQuery("From Doctorspeciality").list();
        return list;
    }

    public static ArrayList<Hospitalspeciality> getspecialities(int id) {
        session.clear();
        return (ArrayList<Hospitalspeciality>) session.createQuery("From Hospitalspeciality hs where hs.hospital= :h").setParameter("h", (Hospital)session.get(Hospital.class, id)).list();
    }
}
