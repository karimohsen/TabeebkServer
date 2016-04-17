/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Gender;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class GenderDao {
    static SessionFactory fact = HibernateUtilFactory.getSessionFactory();
    static Session session = fact.openSession();
    
    public static ArrayList<Gender> getAllGenders(){
        session.clear();
        return (ArrayList<Gender>) session.createQuery("From Gender").list();
    }
}
