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
import com.tabeebkServer.pojo.Planupdates;
import com.tabeebkServer.pojo.UpdateStatus;
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
        //save Transaction -> get last version of plan
        Integer version=(Integer)session.createQuery("SELECT MAX(version) FROM Planupdates WHERE plan=?")
                                     .setParameter(0, plan).uniqueResult();
        if (version==null)
            version=0;
        
        Planupdates pUpdate=new Planupdates();
        pUpdate.setPlan(plan);
        //save Transaction -> get Status
        UpdateStatus uStatus=(UpdateStatus) session.createQuery("FROM UpdateStatus WHERE status='changeINFO'").uniqueResult();
        pUpdate.setUpdateStatus(uStatus);
        pUpdate.setVersion(++version);
        //save Transaction -> Insert new Record in PlanUpdate
        session.beginTransaction();
        session.saveOrUpdate(pUpdate);
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

    public static List<Plan> allMICPlansMsp(int micId, int mspTypeId, int mspId) {
        //Refresh Session
        session.clear();
        Mic medicalInsurance = (Mic) session.get(Mic.class, micId);
        List<Plan> result = session.createQuery("FROM Plan p WHERE p.deleted=0 AND p.mic=? AND p.planId not in(SELECT pm.plan.planId FROM Planmsp pm WHERE pm.id.msptypeTypeId=? AND pm.id.typeId=?)")
                .setParameter(0, medicalInsurance).setParameter(1, mspTypeId).setParameter(2, mspId)
                .list();
        return result;
    }

    public void addPlan(Plan plan) {
        try {
            session.beginTransaction();
            session.save(plan);
            session.getTransaction().commit();
            //save Transaction -> get last version of plan
            Integer version=(Integer)session.createQuery("SELECT MAX(version) FROM Planupdates WHERE plan=?")
                                         .setParameter(0, plan).uniqueResult();
            if (version==null)
                version=0;

            Planupdates pUpdate=new Planupdates();
            pUpdate.setPlan(plan);
            //save Transaction -> get Status
            UpdateStatus uStatus=(UpdateStatus) session.createQuery("FROM UpdateStatus WHERE status='changeINFO'").uniqueResult();
            pUpdate.setUpdateStatus(uStatus);
            pUpdate.setVersion(++version);
            //save Transaction -> Insert new Record in PlanUpdate
            session.beginTransaction();
            session.saveOrUpdate(pUpdate);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void updatePlan(Plan plan) {
        session.beginTransaction();
        session.saveOrUpdate(plan);
        session.getTransaction().commit();
        //save Transaction -> get last version of plan
        Integer version=(Integer)session.createQuery("SELECT MAX(version) FROM Planupdates WHERE plan=?")
                                     .setParameter(0, plan).uniqueResult();
        if (version==null)
            version=0;
        
        Planupdates pUpdate=new Planupdates();
        pUpdate.setPlan(plan);
        //save Transaction -> get Status
        UpdateStatus uStatus=(UpdateStatus) session.createQuery("FROM UpdateStatus WHERE status='changeINFO'").uniqueResult();
        pUpdate.setUpdateStatus(uStatus);
        pUpdate.setVersion(++version);
        //save Transaction -> Insert new Record in PlanUpdate
        session.beginTransaction();
        session.saveOrUpdate(pUpdate);
        session.getTransaction().commit();
    }

    public static void main(String[] args) {
        //============= test ========================
//        List<Plan> result = allMICPlansMsp(1, 1, 1);
//        System.out.println("Size: " + result.size());
//        for (Plan result1 : result) {
//            System.out.println(result1.getPlanName() + "\t" + result1.getPlanNameAr());
//        }
    }
}
