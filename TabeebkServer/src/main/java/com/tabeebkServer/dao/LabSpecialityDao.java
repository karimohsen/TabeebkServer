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
public class LabSpecialityDao {

    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    public static ArrayList<Labspecialities> getLabSpecialities() {
        ArrayList<Labspecialities> list = (ArrayList<Labspecialities>) session.createQuery("From Labspecialities").list();
        return list;
    }

    public static void deleteLabSpecialities(int labId) {
        session.createQuery("Delete from Labspeciality ls where ls.lab = :l").setParameter("l", (Lab)session.get(Lab.class, labId)).executeUpdate();
    }

    public static void addLabToSpeciality(Lab lab, ArrayList<Integer> specialitiesList, int hospital_id) {
        session.clear();
        if (hospital_id != -2) {
            lab.setHospital((Hospital)session.get(Hospital.class, hospital_id));
        }
        lab = LabDao.updateLab(lab);
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

    public static ArrayList<Labspeciality> getLabSpecialities(int id) {
        session.clear();
        return (ArrayList<Labspeciality>) session.createQuery("From Labspeciality ls where ls.lab = :l").setParameter("l", (Lab) session.get(Lab.class, id)).list();
    }
}
