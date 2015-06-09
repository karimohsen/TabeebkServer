/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Doctor;
import com.tabeebkServer.pojo.Doctorspeciality;
import com.tabeebkServer.pojo.Gender;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class DoctorDao {

    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    public static int addDoctor(String name, String nameAr, String degree, String degreeAr, int drSpeciality, byte[] imgPath,int gender) {
        Doctor dr = new Doctor();
        dr.setDeleted(0);
        dr.setDoctorName(name);
        dr.setDoctorNameAr(nameAr);
        dr.setDoctorDegree(degree);
        dr.setDoctorDegreeAr(degreeAr);
        dr.setDoctorImagepath(imgPath);
        dr.setDoctorspeciality((Doctorspeciality) session.get(Doctorspeciality.class, drSpeciality));
        dr.setGender((Gender)session.get(Gender.class, gender));
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(dr);
        session.getTransaction().commit();
        return dr.getDoctorId();
    }
    
    public static void updateDoctor(int drId,String name, String nameAr, String degree, String degreeAr, int drSpeciality,int gender){
        Doctor dr = (Doctor) session.get(Doctor.class, drId);
        dr.setDoctorName(name);
        dr.setDoctorNameAr(nameAr);
        dr.setGender((Gender)session.get(Gender.class, gender));
        dr.setDoctorspeciality((Doctorspeciality)session.get(Doctorspeciality.class, drSpeciality));
        dr.setDoctorDegree(degree);
        dr.setDoctorDegreeAr(degreeAr);
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.update(dr);
        session.getTransaction().commit();
    }
    
    
    public static Doctor getDoctor(int id){
        session.clear();
        return (Doctor)session.get(Doctor.class, id);
    }
}
