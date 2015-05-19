/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Msptype;
import com.tabeebkServer.pojo.Telephone;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class TelephoneDao {

    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    public static void createTelephoneNumber(int mspType, int mspTypeId, String teleNumber) {
        session.clear();
        try {
            Telephone tele = new Telephone();
            tele.setMsptype((Msptype) session.get(Msptype.class, mspType));
            tele.setTypeId(mspTypeId);
            tele.setTelephoneNumber(teleNumber);
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            session.save(tele);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static ArrayList<Telephone> getTelephones(int type,int id) {
        session.clear();
        return (ArrayList<Telephone>) session.createQuery("from Telephone t where t.typeId = :id and t.msptype = :mtype").setParameter("id", id).setParameter("mtype", (Msptype)session.get(Msptype.class, type)).list();
    }

    public static void updateTelephone(int teleid, String number) {
        session.clear();
        try {
            Telephone tele = (Telephone) session.get(Telephone.class, teleid);
            tele.setTelephoneNumber(number);
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            session.update(tele);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
