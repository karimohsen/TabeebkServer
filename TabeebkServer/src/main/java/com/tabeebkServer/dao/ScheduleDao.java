/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.dao;

import static com.tabeebkServer.dao.AccountDao.session;
import com.tabeebkServer.pojo.Schedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class ScheduleDao {
    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();
    
    public static void createSchedule(Schedule schedule){
        if(!session.getTransaction().isActive())
            session.beginTransaction();
        session.save(schedule);
        session.getTransaction().commit();
    }
}
