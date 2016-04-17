/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Hospital;
import com.tabeebkServer.pojo.Lab;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class LabDao {

    static SessionFactory fact = HibernateUtilFactory.getSessionFactory();
    static Session session = fact.openSession();

    public static Lab updateLab(Lab lab) {
        session.clear();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.update(lab);
        session.getTransaction().commit();
        return lab;
    }
    public static Lab addLab(Lab lab) {
        session.clear();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(lab);
        session.getTransaction().commit();
        return lab;
    }

    public static void deleteLabHospital(int labId, int hospitalId) {
        session.createQuery("Delete From Lab l where l.hospital = :h").setParameter("h", (Hospital) session.get(Hospital.class, hospitalId)).executeUpdate();
    }

    public static void updateLab(int id, String name, String nameAr) {
        session.clear();
        Lab lab = (Lab) session.get(Lab.class, id);
        lab.setLabName(name);
        lab.setLabNameAr(nameAr);
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.update(lab);
        session.getTransaction().commit();

    }

    public static Lab getLabDetails(int id) {
        session.clear();
        return (Lab) session.get(Lab.class, id);
    }
}
