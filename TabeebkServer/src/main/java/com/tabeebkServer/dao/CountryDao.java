/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Country;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class CountryDao {

    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    public static ArrayList<Country> getAllCountries() {
        ArrayList<Country> list = (ArrayList<Country>) session.createQuery("from Country").list();
        return list;
    }
}
