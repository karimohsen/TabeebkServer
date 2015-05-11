/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Hospital;
import com.tabeebkServer.pojo.Lab;
import com.tabeebkServer.pojo.Labspecialities;
import com.tabeebkServer.pojo.Labspeciality;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class LabDao {

    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    public static ArrayList<Labspecialities> getLabSpecialities() {
        ArrayList<Labspecialities> list = (ArrayList<Labspecialities>) session.createQuery("From Labspecialities").list();
        return list;
    }

    public static void addLab(Lab lab, ArrayList<Integer> specialitiesList, int hospital_id){
        if (hospital_id != -2) {
            lab.setHospital((Hospital) session.get(Hospital.class, hospital_id));
        }
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(lab);
        session.getTransaction().commit();
        if (!specialitiesList.isEmpty()) {
            for (int i = 0; i < specialitiesList.size(); i++) {

                Labspeciality labSpeciality = new Labspeciality();
                labSpeciality.setDeleted(0);
                labSpeciality.setLab(lab);
                labSpeciality.setLabspecialities((Labspecialities) session.get(Labspecialities.class, specialitiesList.get(i)));

                if (!session.getTransaction().isActive()) {
                    session.beginTransaction();
                }
                session.save(labSpeciality);
                session.getTransaction().commit();
            }
        }
    }
}
