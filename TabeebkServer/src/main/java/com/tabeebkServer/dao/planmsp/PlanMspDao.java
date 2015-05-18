/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao.planmsp;

import com.tabeebkServer.dao.MICDao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tabeebkServer.pojo.Clinic;
import com.tabeebkServer.pojo.Doctor;
import com.tabeebkServer.pojo.Hospital;
import com.tabeebkServer.pojo.Lab;
import com.tabeebkServer.pojo.Msp;
import com.tabeebkServer.pojo.Msptype;
import com.tabeebkServer.pojo.Plan;
import com.tabeebkServer.pojo.Planmsp;
import com.tabeebkServer.pojo.PlanmspId;
import com.tabeebkServer.pojo.Planupdates;
import com.tabeebkServer.pojo.UpdateStatus;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import com.tabeebkServer.utilty.GenericMSP;

/**
 *
 * @author Karim
 */
public class PlanMspDao {

    static SessionFactory factory = HibernateUtilFactory.getSessionFactory();
    static Session session = factory.openSession();

    public static List<GenericMSP> allMspsInMyPlan(int planId) {
        //refresh session
        session.clear();
        Plan p = (Plan) session.get(Plan.class, planId);
        List<Planmsp> list = session.createQuery("From Planmsp pm where pm.plan = ? and pm.deleted = 0").setParameter(0, p).list();
        List<GenericMSP> finalResult = new ArrayList<GenericMSP>();
        for (Planmsp planmsp : list) {
            Hospital h = new Hospital();
            Clinic c = new Clinic();
            Doctor d = new Doctor();
            Lab l = new Lab();
            GenericMSP gmsp = new GenericMSP();
            if (planmsp.getMsptype() != null) {
                switch (planmsp.getMsptype().getTypeId()) {
                    case 1:
                        h = (Hospital) session.get(Hospital.class, planmsp.getId().getTypeId());
                        gmsp.setMspId(planmsp.getId().getTypeId());
                        gmsp.setMspname(h.getHospitalName());
                        gmsp.setMsptypeId(planmsp.getMsptype().getTypeId());
                        gmsp.setMsptypename(planmsp.getMsptype().getTypeName());
                        if (h.getDeleted() == 0) {
                            finalResult.add(gmsp);
                        }
                        break;
                    case 2:
                        c = (Clinic) session.get(Clinic.class, planmsp.getId().getTypeId());
                        gmsp.setMspId(planmsp.getId().getTypeId());
                        gmsp.setMspname(c.getClinicName());
                        gmsp.setMsptypeId(planmsp.getMsptype().getTypeId());
                        gmsp.setMsptypename(planmsp.getMsptype().getTypeName());
                        if (c.getDeleted() == 0) {
                            finalResult.add(gmsp);
                        }
                        break;
                    case 3:
                        d = (Doctor) session.get(Doctor.class, planmsp.getId().getTypeId());
                        gmsp.setMspId(planmsp.getId().getTypeId());
                        gmsp.setMspname(d.getDoctorName());
                        gmsp.setMsptypeId(planmsp.getMsptype().getTypeId());
                        gmsp.setMsptypename(planmsp.getMsptype().getTypeName());
                        if (d.getDeleted() == 0) {
                            finalResult.add(gmsp);
                        }
                        break;
                    case 4:
                        l = (Lab) session.get(Lab.class, planmsp.getId().getTypeId());
                        gmsp.setMspId(planmsp.getId().getTypeId());
                        gmsp.setMspname(l.getLabName());
                        gmsp.setMsptypeId(planmsp.getMsptype().getTypeId());
                        gmsp.setMsptypename(planmsp.getMsptype().getTypeName());
                        if (l.getDeleted() == 0) {
                            finalResult.add(gmsp);
                        }
                        break;
                }
            }
        }

        return finalResult;
    }

    public static List<GenericMSP> allMspsNotInMyPlan(int micId, int planId) {
        //my all msp
        List<GenericMSP> finalResult1 = MICDao.viewMyMSPs(micId);
        //msp at certain paln
        List<GenericMSP> finalResult2 = PlanMspDao.allMspsInMyPlan(planId);
        for (int j = 0; j < finalResult1.size(); j++) {
            for (int i = 0; i < finalResult2.size(); i++) {
                if (finalResult2.get(i).toString().compareToIgnoreCase(finalResult1.get(j).toString()) == 0) {
                    finalResult1.remove(j);
                }
            }
        }
        return finalResult1;
    }
// not useddddddddd
    public void deleteMspFromPlan(List<Planmsp> list) {
        for (int i = 0; i < list.size(); i++) {
            session.beginTransaction();
            list.get(i).setDeleted(1);
            session.saveOrUpdate(list.get(i));
            session.getTransaction().commit();
        }
    }

    public static void deleteMspFromPlan(int planId, int mspTypeId, int typeId) {
        PlanmspId planMsp = new PlanmspId(planId, mspTypeId, typeId);

        session.beginTransaction();
        session.createQuery("UPDATE Planmsp pm SET pm.deleted = 1 where pm.id = :myid")
                .setParameter("myid", planMsp)
                .executeUpdate();
        session.getTransaction().commit();
        //save Transaction -> get last version of plan
        Plan plan=(Plan)session.get(Plan.class, planId);
        Msp msp=(Msp)session.get(Msp.class, typeId);
        Msptype msptype=(Msptype)session.get(Msptype.class, mspTypeId);
        Integer version=(Integer)session.createQuery("SELECT MAX(version) FROM Planupdates WHERE plan=?")
                                     .setParameter(0, plan).uniqueResult();
        if (version==null)
            version=0;
        
        Planupdates pUpdate=new Planupdates();
        pUpdate.setPlan(plan);
        //save Transaction -> get Status
        UpdateStatus uStatus=(UpdateStatus) session.createQuery("FROM UpdateStatus WHERE status='DeleteMSP'").uniqueResult();
        pUpdate.setUpdateStatus(uStatus);
        pUpdate.setVersion(++version);
        pUpdate.setMsptype(msptype);
        pUpdate.setMsp(msp);
        //save Transaction -> Insert new Record in PlanUpdate
        session.beginTransaction();
        session.saveOrUpdate(pUpdate);
        session.getTransaction().commit();
    }

    public static void addMspToPlan(Planmsp obj) {
        session.beginTransaction();
        session.saveOrUpdate(obj);
        session.getTransaction().commit();
        //save Transaction -> get last version of plan
        Plan plan=(Plan)session.get(Plan.class, obj.getId().getPlanId());
        Msp msp=(Msp)session.get(Msp.class, obj.getId().getTypeId());
        Msptype msptype=(Msptype)session.get(Msptype.class, obj.getId().getMsptypeTypeId());
        Integer version=(Integer)session.createQuery("SELECT MAX(version) FROM Planupdates WHERE plan=?")
                                     .setParameter(0, plan).uniqueResult();
        if (version==null)
            version=0;
        
        Planupdates pUpdate=new Planupdates();
        pUpdate.setPlan(plan);
        //save Transaction -> get Status
        UpdateStatus uStatus=(UpdateStatus) session.createQuery("FROM UpdateStatus WHERE status='DeleteMSP'").uniqueResult();
        pUpdate.setUpdateStatus(uStatus);
        pUpdate.setVersion(++version);
        pUpdate.setMsptype(msptype);
        pUpdate.setMsp(msp);
        //save Transaction -> Insert new Record in PlanUpdate
        session.beginTransaction();
        session.saveOrUpdate(pUpdate);
        session.getTransaction().commit();
    }

    public static void addMspToPlan(List<Planmsp> objs) {
        for (Planmsp obj : objs) {
            session.beginTransaction();
            session.saveOrUpdate(obj);
            session.getTransaction().commit();
            //save Transaction -> get last version of plan
            Plan plan=(Plan)session.get(Plan.class, obj.getId().getPlanId());
            Msp msp=(Msp)session.get(Msp.class, obj.getId().getTypeId());
            Msptype msptype=(Msptype)session.get(Msptype.class, obj.getId().getMsptypeTypeId());
            Integer version=(Integer)session.createQuery("SELECT MAX(version) FROM Planupdates WHERE plan=?")
                                         .setParameter(0, plan).uniqueResult();
            if (version==null)
                version=0;

            Planupdates pUpdate=new Planupdates();
            pUpdate.setPlan(plan);
            //save Transaction -> get Status
            UpdateStatus uStatus=(UpdateStatus) session.createQuery("FROM UpdateStatus WHERE status='DeleteMSP'").uniqueResult();
            pUpdate.setUpdateStatus(uStatus);
            pUpdate.setVersion(++version);
            pUpdate.setMsptype(msptype);
            pUpdate.setMsp(msp);
            //save Transaction -> Insert new Record in PlanUpdate
            session.beginTransaction();
            session.saveOrUpdate(pUpdate);
            session.getTransaction().commit();
        }
        session.flush();
        session.clear();
    }

    //not used

    public void addMspToPlann(int mspId, int mspTypeId, int planid) {
        PlanmspId planMspId = new PlanmspId(planid, mspTypeId, mspId);
        Plan p = (Plan) session.get(Plan.class, planid);
        Msptype mspType = (Msptype) session.get(Msptype.class, mspTypeId);
        Planmsp planMsp = new Planmsp(planMspId, mspType, p, 0);
        session.beginTransaction();
        session.save(planMsp);
        session.getTransaction().commit();
    }

    public static void main(String[] args) {
        //=================== Test msp not in plan =================
//        PlanMspDao.allMspsNotInMyPlan(1, 1).size();
        //================ allMspsInMyPlan =========================
//        List<GenericMSP> list = new PlanMspDao().allMspsInMyPlan(1);
//        for (GenericMSP list1 : list) { 
//            System.out.println(list1.getMspname() + " " + list1.getMspId());
//        }
        //================ Test Add And delete MspFromPlan ======================
//        int planid = 1;
//        int mspid = 1;
//        Planmsp planmsp = new Planmsp();
//        PlanmspId planmspId = new PlanmspId();
//        planmspId.setPlanId(planid);
//        planmspId.setTypeId(mspid);
//        planmspId.setMsptypeTypeId(1);
//        planmsp.setId(planmspId);
//        planmsp.setDeleted(1);
        //================ deleteMspFromPlan ======================
//        PlanMspDao.deleteMspFromPlan(1, 3, 1);
//        planmsp.setDeleted(0);
        //================ addMspToPlan =========================
//        PlanMspDao.addMspToPlan(planmsp);
    }
}
