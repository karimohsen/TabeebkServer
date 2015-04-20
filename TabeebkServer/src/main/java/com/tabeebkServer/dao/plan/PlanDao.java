/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao.plan;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tabeebkServer.pojo.Mic;
import com.tabeebkServer.pojo.Plan;
import com.tabeebkServer.session.factory.HibernateUtilFactory;

/**
 *
 * @author Karim
 */
public class PlanDao {

    static SessionFactory factory = HibernateUtilFactory.getSessionFactory();
    static Session session = factory.openSession();

    public void removePlan(int id) {
        Plan plan = (Plan) session.get(Plan.class, id);
        session.beginTransaction();
        plan.setDeleted(1);
        session.saveOrUpdate(plan);
        session.getTransaction().commit();
    }
    
    public Plan getPlanDetails(int id) {
        return (Plan) session.get(Plan.class, id);
    }

    public List<Plan> allMspPlans(int mspId) {
        Mic medicalInsurance = (Mic) session.get(Mic.class, mspId);
        List<Plan> result = session.createQuery("From Plan p where p.mic = ? and p.deleted = 0").setParameter(0, medicalInsurance).list();
        return result;
    }

    public void editPlan(Plan plan) {
        session.beginTransaction();
        session.update(plan);
        session.getTransaction().commit();
    }

    public void addPlan(Plan plan) {
        try {
            session.beginTransaction();
            session.save(plan);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
//        session.beginTransaction();
//        session.save(plan);
//        session.getTransaction().commit();
    }

    public void updatePlan(Plan plan) {
        session.beginTransaction();
        session.saveOrUpdate(plan);
        session.getTransaction().commit();
    }

}
