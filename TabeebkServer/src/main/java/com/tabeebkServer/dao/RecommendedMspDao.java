/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Msprecommendations;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import com.tabeebkServer.utilty.RecommendedMSP;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class RecommendedMspDao {

    static SessionFactory fact = HibernateUtilFactory.getSessionFactory();
    static Session session = fact.openSession();

    public static ArrayList<RecommendedMSP> viewRecommendations() {
        session.clear();
        ArrayList<RecommendedMSP> list = new ArrayList<RecommendedMSP>();
        ArrayList<Msprecommendations> recommendataionsList = (ArrayList<Msprecommendations>) session.createQuery("From Msprecommendations recommend where recommend.approved = 0").list();
        for (int i = 0; i < recommendataionsList.size(); i++) {
            RecommendedMSP recommendedMsp = new RecommendedMSP();
            recommendedMsp.setId(recommendataionsList.get(i).getMspRecommendationsId());
            recommendedMsp.setMspArea(recommendataionsList.get(i).getMspArea());
            recommendedMsp.setMspCity(recommendataionsList.get(i).getMspCity());
            recommendedMsp.setMspCountry(recommendataionsList.get(i).getMspCountry());
            recommendedMsp.setMspName(recommendataionsList.get(i).getMspName());
            recommendedMsp.setNotes(recommendataionsList.get(i).getMspNotes());
            recommendedMsp.setMspPhone(recommendataionsList.get(i).getMspPhone());
            recommendedMsp.setMspStreet(recommendataionsList.get(i).getMspStreetName());
            recommendedMsp.setMspType(recommendataionsList.get(i).getMsptype().getTypeName());
            recommendedMsp.setUserName(recommendataionsList.get(i).getUser().getUserName());
            list.add(recommendedMsp);
        }
        return list;
    }
    public static void ApproveMsp(int id){
        System.out.println("====( Here )====");
        System.out.println("----------------");
        Msprecommendations msp = (Msprecommendations)session.get(Msprecommendations.class, id);
        msp.setApproved(1);
        if(!session.getTransaction().isActive())
            session.beginTransaction();
        session.update(msp);
        session.getTransaction().commit();
    }
}
