/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import static com.tabeebkServer.dao.AccountDao.session;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.tabeebkServer.pojo.Mic;
import com.tabeebkServer.pojo.Micratting;
import com.tabeebkServer.pojo.User;

/**
 *
 * @author HMA
 */
public class UserDao {

    //===== session per dao
    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    public static List<User> viewUsers() {
        session.clear();
        Query q = session.createQuery("from User where blocked=0");
        List<User> result = q.list();
        return result;
    }
    //=======================admin view all users=====================
    public static List<User> viewAllUsers() {
        session.clear();
        Query q = session.createQuery("from User");
        List<User> result = q.list();
        return result;
    }
    //================================================================
    
    //============admin un block users=====================
    public static void unBlockUser(int userId) {
        User u = (User) session.get(User.class, userId);
        u.setBlocked(0);
        if(!session.getTransaction().isActive())
        session.beginTransaction();
        session.saveOrUpdate(u);
        session.getTransaction().commit();
    }
    //====================================================
    //============admin block users=====================
    public static void blockUser(int userId) {
        User u = (User) session.get(User.class, userId);
        u.setBlocked(1);
        if(!session.getTransaction().isActive())
        session.beginTransaction();
        session.saveOrUpdate(u);
        session.getTransaction().commit();
    }
    //====================================================
    public static List<Micratting> viewMicRatting(int userId, int micId) {

        User u = (User) session.get(User.class, userId);
        Mic mic = (Mic) session.get(Mic.class, micId);
        Query q = session.createQuery("from Micratting where user= :user and mic= :mic")
                .setParameter("user", u)
                .setParameter("mic", mic);
        List<Micratting> result = q.list();
        return result;
    }

    public static void main(String[] args) {
        //================== viewUsers ================================
//        System.out.println("viewUsers: "+viewUsers().size());
//        //================== viewMicRatting ===============================                
//        System.out.println("Rating value: "+UserDao.viewMicRatting(1, 1).get(0).getRatingValue());
//        System.out.println("Rating comments: "+UserDao.viewMicRatting(1, 1).get(0).getRatingComment());
    }
}
