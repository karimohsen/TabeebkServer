/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.planmsp;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.Clinic;
import pojo.Doctor;
import pojo.Hospital;
import pojo.Lab;
import pojo.Msp;
import pojo.Msptype;
import pojo.Plan;
import pojo.Planmsp;
import pojo.PlanmspId;
import session.factory.HibernateUtilFactory;
import utilty.GenericMSP;

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
        return finalResult;
    }

    public List<Planmsp> allMspsNotInMyPlan(int planId) {
        Plan p = (Plan) session.get(Plan.class, planId);
        List<Planmsp> result = session.createQuery("From Planmsp pm where pm.plan = ? and pm.deleted = 0").setParameter(0, p).list();
//        List<Msp> msps=session.createQuery("from Msp m where planmsp.msptype != ? and planmsp.typeId != ?")
//                .setParameter(null, p).setParameter(null, p).list();
        List<Msp> msps = session.createQuery("from Msp").list();
//        for (Msp msp : msps) {
//            
//        }

        for (int j = 0; j < result.size(); j++) {
            for (int i = 0; i < msps.size(); i++) {
                if ((result.get(j).getMsptype().getTypeId() == msps.get(i).getTypeId()) && (result.get(j).getId().getMsptypeTypeId() == msps.get(i).getMspId())) {
                    msps.remove(msps.get(i));
                    //Systeplanmsp.out.println(msps.get(i).getMsptype().getTypeName());
                }
            }

        }
        //Systeplanmsp.out.println("size : " + msps.size());
//        msps.remove(result);
        return result;
    }

    public void deleteMspFromPlan(List<Planmsp> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setDeleted(1);
            session.beginTransaction();
            session.saveOrUpdate(list.get(i));
            session.getTransaction().commit();
        }
    }

    public static void deleteMspFromPlan(Planmsp obj) {
        session.beginTransaction();
        session.saveOrUpdate(obj);
        session.getTransaction().commit();
    }

    public static void main(String[] args) {
        //new PlanMspDao().allMspsNotInMyPlan(1);
        //================ allMspsInMyPlan =========================
        List<GenericMSP> list = new PlanMspDao().allMspsInMyPlan(1);
        for (GenericMSP list1 : list) {
            System.out.println(list1.getMspname() + " " + list1.getMspId());
        }
    }
}
