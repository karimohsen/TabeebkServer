/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Doctorspeciality;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class HospitalSpecialityDao {
    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();
    
    public static ArrayList<Doctorspeciality> getHospitalspecialities(){
        ArrayList<Doctorspeciality> list = new ArrayList<>();
        list = (ArrayList<Doctorspeciality>) session.createQuery("From Doctorspeciality").list();
        return list;
    }
    
    
   
}
