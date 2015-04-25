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
import com.tabeebkServer.pojo.Msptype;
import com.tabeebkServer.pojo.Plan;
import com.tabeebkServer.pojo.Planmsp;
import com.tabeebkServer.pojo.PlanmspId;
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import com.tabeebkServer.utilty.GenericMSP;

/**
 *
 * @author Karim
 */
public class PlanMspDao {

    static SessionFactory factory = HibernateUtilFactory.getSessionFactory();
    static Session session = factory.openSession();

    public void addMspToPlan(int mspId, int mspTypeId, int planid) {
        PlanmspId planMspId = new PlanmspId(planid, mspTypeId, mspId);
        Plan p = (Plan) session.get(Plan.class, planid);
        Msptype mspType = (Msptype) session.get(Msptype.class, mspTypeId);
        Planmsp planMsp = new Planmsp(planMspId, mspType, p, 0);
        session.beginTransaction();
        session.save(planMsp);
        session.getTransaction().commit();
    }

    public static List<GenericMSP> allMspsInMyPlan(int planId) {
        Plan p = (Plan) session.get(Plan.class, planId);
        List<Planmsp> list = session.createQuery("From Planmsp pm where pm.plan = ? and pm.deleted = 0").setParameter(0, p).list();
        List<GenericMSP> finalResult = new ArrayList<GenericMSP>();
        for (Planmsp planmsp : list) {
            Hospital h = new Hospital();
            Clinic c = new Clinic();
            Doctor d = new Doctor();
            Lab l = new Lab();
            GenericMSP gmsp = new GenericMSP();
            if (planmsp.getMsptype().getTypeId() != null) {
                switch (planmsp.getMsptype().getTypeId()) {
                    case 1:
                        h = (Hospital) session.get(Hospital.class, planmsp.getId().getTypeId());
                        gmsp.setMspId(planmsp.getId().getTypeId());
                        gmsp.setMspname(h.getHospitalName());
                        gmsp.setMsptypeId(planmsp.getMsptype().getTypeId());
                        gmsp.setMsptypename(planmsp.getMsptype().getTypeName());
                        finalResult.add(gmsp);
                        break;
                    case 2:
                        c = (Clinic) session.get(Clinic.class, planmsp.getId().getTypeId());
                        gmsp.setMspId(planmsp.getId().getTypeId());
                        gmsp.setMspname(c.getClinicName());
                        gmsp.setMsptypeId(planmsp.getMsptype().getTypeId());
                        gmsp.setMsptypename(planmsp.getMsptype().getTypeName());
                        finalResult.add(gmsp);
                        break;
                    case 3:
                        d = (Doctor) session.get(Doctor.class, planmsp.getId().getTypeId());
                        gmsp.setMspId(planmsp.getId().getTypeId());
                        gmsp.setMspname(d.getDoctorName());
                        gmsp.setMsptypeId(planmsp.getMsptype().getTypeId());
                        gmsp.setMsptypename(planmsp.getMsptype().getTypeName());
                        finalResult.add(gmsp);
                        break;
                    case 4:
                        l = (Lab) session.get(Lab.class, planmsp.getId().getTypeId());
                        gmsp.setMspId(planmsp.getId().getTypeId());
                        gmsp.setMspname(l.getLabName());
                        gmsp.setMsptypeId(planmsp.getMsptype().getTypeId());
                        gmsp.setMsptypename(planmsp.getMsptype().getTypeName());
                        finalResult.add(gmsp);
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
        List<GenericMSP> finalResult2 = PlanMspDao.allMspsInMyPlan(1);
        finalResult1.removeAll(finalResult2);
        for (int j = 0; j < finalResult1.size(); j++) {
            for (int i = 0; i < finalResult2.size(); i++) {
                if (finalResult2.get(i).equals(finalResult1.get(j))) {
//                    System.out.println("Remove: "+finalResult2.get(i).getMspname() + "\t" + finalResult1.get(j).getMspname());
                    finalResult1.remove(j);
                }
            }
        }
        return finalResult1;
    }

    public void deleteMspFromPlan(List<Planmsp> list) {
        for (int i = 0; i < list.size(); i++) {
            session.beginTransaction();
            list.get(i).setDeleted(1);
            session.saveOrUpdate(list.get(i));
            session.getTransaction().commit();
        }
    }

    public static void deleteMspFromPlan(Planmsp obj) {
//        obj=(Planmsp) session.get(Planmsp.class, obj);
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
    }

    public static void addMspToPlan(Planmsp obj) {
        session.beginTransaction();
        session.saveOrUpdate(obj);
        session.getTransaction().commit();
    }

    public static void main(String[] args) {
        //=================== Test msp not in plan =================
//        System.out.println(PlanMspDao.allMspsNotInMyPlan(1).size());
        //================ allMspsInMyPlan =========================
//        List<GenericMSP> list = new PlanMspDao().allMspsInMyPlan(1);
//        for (GenericMSP list1 : list) {
//            System.out.println(list1.getMspname() + " " + list1.getMspId());
//        }
        //================ Test Add And delete MspFromPlan ======================
        int planid = 1;
        int mspid = 1;
        Planmsp planmsp = new Planmsp();
        PlanmspId planmspId = new PlanmspId();
        planmspId.setPlanId(planid);
        planmspId.setTypeId(mspid);
        planmspId.setMsptypeTypeId(1);
        planmsp.setId(planmspId);
        planmsp.setDeleted(1);
        //================ deleteMspFromPlan ======================
        PlanMspDao.deleteMspFromPlan(planmsp);
        planmsp.setDeleted(0);
        //================ addMspToPlan =========================
        PlanMspDao.addMspToPlan(planmsp);
    }
}
