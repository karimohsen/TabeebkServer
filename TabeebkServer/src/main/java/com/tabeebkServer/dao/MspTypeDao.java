/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Msptype;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Karim
 */
public class MspTypeDao {
    static SessionFactory fact = HibernateUtilFactory.getSessionFactory();
    static Session session = fact.openSession();
    
    
    public static Msptype getMspType(int id){
        return (Msptype) session.get(Msptype.class, id);
    }
}
