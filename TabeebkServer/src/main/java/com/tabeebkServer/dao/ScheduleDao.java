/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Msptype;
import com.tabeebkServer.pojo.Schedule;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Karim
 */
public class ScheduleDao {
    static SessionFactory fact = HibernateUtilFactory.getSessionFactory();
    static Session session = fact.openSession();
    
    public static void createSchedule(Schedule schedule){
        if(!session.getTransaction().isActive())
            session.beginTransaction();
        session.save(schedule);
        session.getTransaction().commit();
    }
    
    public static ArrayList<Schedule> getSchedule(int drId){
        session.clear();
        return (ArrayList<Schedule>)session.createQuery("From Schedule s where s.msptype =:type and s.msptypeId =:id and s.date >= :dt").setParameter("type", session.get(Msptype.class, 3)).setParameter("id", drId).setParameter("dt", new Date()).list();
    }
    
    public static double getNumberofSlots(Schedule schedule){
        return ((schedule.getEndTime().getHours() - schedule.getStartTime().getHours()) / (schedule.getServeTime() / 60.0));
    }
}
