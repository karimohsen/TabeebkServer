/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

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
        Query q = session.createQuery("from User");
        List<User> result = q.list();
        return result;
    }

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
//        System.out.println("viewUsers: "+viewUsers().get(0).getUserEmail());
//        //================== viewMicRatting ===============================                
//        System.out.println("Rating value: "+UserDao.viewMicRatting(1, 1).get(0).getRatingValue());
//        System.out.println("Rating comments: "+UserDao.viewMicRatting(1, 1).get(0).getRatingComment());
    }
}
