/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.dao.mic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tabeebkServer.pojo.Mic;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import java.util.List;

/**
 *
 * @author Karim
 */
public class MicDao {
    static SessionFactory factory = HibernateUtilFactory.getSessionFactory();
    static Session session = factory.openSession();
    
    //==============Admin Create MIC===============================
    public static int createMIC(Mic mic){
        if(!session.getTransaction().isActive())
        session.beginTransaction();
        session.save(mic);
        session.getTransaction().commit();
        return mic.getMicId();
    }
    //=============================================================
    //=================Admin return all MIC=========================
    public static List<Mic> viewAllNonDeletedMIC(){
        session.clear();
        return session.createQuery("from Mic").list();
    }
    //=============================================================
    //===============Admin Delete MIC==============================
    public static void DeletedMIC(int MIC_id){
        Mic mic = (Mic)session.get(Mic.class, MIC_id);
        mic.setDeleted(1);
        if(!session.getTransaction().isActive())
        session.beginTransaction();
        session.update(mic);
        session.getTransaction().commit();
    }
    //=============================================================
    //===============Admin Recover MIC==============================
    public static void RecoverMIC(int MIC_id){
        Mic mic = (Mic)session.get(Mic.class, MIC_id);
        mic.setDeleted(0);
        if(!session.getTransaction().isActive())
        session.beginTransaction();
        session.update(mic);
        session.getTransaction().commit();
    }
    //=============================================================
    //================Return All not deleted MIC====================
    public static List<Mic> viewAllMIC(){
        session.clear();
        return session.createQuery("from Mic where deleted=0").list();
    }
    public Mic getMicDetails(int id){
        return (Mic)session.get(Mic.class, id);
    }
    
    public static boolean addMIC(Mic newMic){
        try {
            if(!session.getTransaction().isActive())
            session.beginTransaction();
            session.save(newMic);
            session.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
//            throw e;
            return false;
        }
    }

}
