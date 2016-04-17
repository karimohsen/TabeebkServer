/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Reservation;
import com.tabeebkServer.pojo.Schedule;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Karim
 */
public class ReservationDao {
    static SessionFactory fact = HibernateUtilFactory.getSessionFactory();
    static Session session = fact.openSession();
    
    public static ArrayList<Reservation> getReservations(ArrayList<Schedule> schedule){
        session.clear();
        ArrayList<Reservation> list = new ArrayList<>();
        for(int i = 0 ; i < schedule.size() ; i++){
            list.addAll((ArrayList<Reservation>) session.createQuery("From Reservation r where r.schedule = :sc ").setParameter("sc", schedule.get(i)).list());
        }
        return list;
    }
    
}
