/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Doctorspeciality;
import com.tabeebkServer.pojo.Hospital;
import com.tabeebkServer.pojo.Hospitalspeciality;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class HospitalDao {

    static SessionFactory fact = HibernateUtilFactory.getSessionFactory();
    static Session session = fact.openSession();

    public static ArrayList<Hospital> getAllHospitals(){
        ArrayList<Hospital> list = (ArrayList<Hospital>)session.createQuery("From Hospital h where h.deleted = 0").list();
        return list;
    }
    
    public static Hospital getHospitalDetails(int id){
        session.clear();
        return (Hospital)session.get(Hospital.class, id);
    }
    
    public static void updateHospital(int id , String name , String nameAr){
        Hospital hospital = (Hospital)session.get(Hospital.class, id);
        hospital.setHospitalName(name);
        hospital.setHospitalNameAr(nameAr);
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(hospital);
        session.getTransaction().commit();
    }
    
    public static int addHospital(Hospital h){
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(h);
        session.getTransaction().commit();
        return h.getHospitalId();
    }
    
    public static void addHospitalSpeciality(int id, ArrayList<Integer> specialityList) {
        Hospital h = (Hospital)session.get(Hospital.class, id);
        if (!specialityList.isEmpty()) {
            for (int i = 0; i < specialityList.size(); i++) {
                Hospitalspeciality hospitalSpeciality = new Hospitalspeciality();
                hospitalSpeciality.setHospital(h);
                hospitalSpeciality.setDoctorspeciality((Doctorspeciality) session.get(Doctorspeciality.class, specialityList.get(i)));
                hospitalSpeciality.setDeleted(0);
                if (!session.getTransaction().isActive()) {
                    session.beginTransaction();
                }
                session.save(hospitalSpeciality);
                session.getTransaction().commit();
            }
        }
    }
    public static void deleteHospitalSpeciality(int id){
        session.clear();
        session.createQuery("Delete From Hospitalspeciality hs where hs.hospital = :id").setParameter("id", (Hospital)session.get(Hospital.class, id)).executeUpdate();
    }

}
