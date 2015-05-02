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

    public List<Plan> allMICPlans(int micId) {
        Mic medicalInsurance = (Mic) session.get(Mic.class, micId);
        List<Plan> result = session.createQuery("From Plan p where p.mic = ? and p.deleted = 0").setParameter(0, medicalInsurance).list();
        return result;
    }
    public static List<Plan> allMICPlansMsp(int micId, int mspTypeId,int mspId) {
        //session.clear();
        Mic medicalInsurance = (Mic) session.get(Mic.class, micId);
//        session.createQuery("SELECT p FROM Planmsp pm WHERE pm.id.msptypeTypeId=? AND pm.id.typeId=?");
        //=========== get distancate resulte ======
        List<Plan> result=session.createQuery("FROM Plan p WHERE p.deleted=0 AND p.mic=? AND p.planId not in(SELECT pm.plan.planId FROM Planmsp pm WHERE pm.id.msptypeTypeId=? AND pm.id.typeId=?)")
                .setParameter(0, medicalInsurance).setParameter(1, mspTypeId).setParameter(2, mspId)
                .list();
//        List<Plan> result = session.createQuery("From Plan p where p.deleted = 0 and p.mic = ?").setParameter(0, medicalInsurance).list();
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
    }

    public void updatePlan(Plan plan) {
        session.beginTransaction();
        session.saveOrUpdate(plan);
        session.getTransaction().commit();
    }
    public static void main(String[] args) {
        //============= test ========================
        List<Plan> result=allMICPlansMsp(1, 1, 1);
        System.out.println("Size: "+result.size());
        for (Plan result1 : result) {
            System.out.println(result1.getPlanName()+"\t"+result1.getPlanNameAr());
        }
    }
}
