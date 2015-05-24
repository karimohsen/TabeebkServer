/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Clinic;
import com.tabeebkServer.pojo.Doctor;
import com.tabeebkServer.pojo.DoctorClinc;
import com.tabeebkServer.pojo.DoctorClincId;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class DoctorClinicDao {

    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    public static void addDoctorClinic(int drId, ArrayList<Integer> clinicList) {
        System.out.println("clinc number : " + clinicList.size());
        for (int i = 0; i < clinicList.size(); i++) {
            session.clear();
            DoctorClinc drClinic = new DoctorClinc();
            drClinic.setDeleted(0);
            drClinic.setDoctor((Doctor) session.get(Doctor.class, drId));
            System.out.println("clinic id " + clinicList.get(i));
            drClinic.setClinic((Clinic) session.get(Clinic.class, clinicList.get(i)));
            drClinic.setId(new DoctorClincId(drId, clinicList.get(i)));
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            session.save(drClinic);
            session.getTransaction().commit();
        }
    }

    public static ArrayList<DoctorClinc> getAllDoctorClinics(int id) {
        session.clear();
        return (ArrayList<DoctorClinc>) session.createQuery("from DoctorClinc dc where dc.doctor = :dr").setParameter("dr", (Doctor) session.get(Doctor.class, id)).list();
    }

    public static void deleteDoctorClinics(int drId) {
        session.createQuery("Delete From DoctorClinc dc where dc.doctor =:dr").setParameter("dr", (Doctor) session.get(Doctor.class, drId)).executeUpdate();
    }

    public static void updateDoctorClinic(int drId, int clinicId) {
        session.clear();
        DoctorClinc drClinic = new DoctorClinc();
        drClinic.setDeleted(0);
        drClinic.setDoctor((Doctor) session.get(Doctor.class, drId));
        drClinic.setClinic((Clinic) session.get(Clinic.class, clinicId));
        drClinic.setId(new DoctorClincId(drId, clinicId));
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(drClinic);
        session.getTransaction().commit();
    }
}
