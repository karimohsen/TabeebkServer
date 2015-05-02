/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.tabeebkServer.pojo.Clinic;
import com.tabeebkServer.pojo.Doctor;
import com.tabeebkServer.pojo.Hospital;
import com.tabeebkServer.pojo.Lab;
import com.tabeebkServer.pojo.Msp;
import com.tabeebkServer.pojo.Msptype;
import com.tabeebkServer.pojo.Ratting;
import com.tabeebkServer.utilty.GenericMSP;

/**
 *
 * @author HMA
 */
public class MSPDao {

    //===== session per dao
    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    //=====================Admin get all MSPS==============================
    public static List<GenericMSP> viewAllMSPs() {
        session.clear();
        List<GenericMSP> finalResult = new ArrayList<GenericMSP>();
        Query q = session.createQuery("from Msp");
        List<Msp> result = q.list();
        for (Msp m : result) {
            Hospital h = new Hospital();
            Clinic c = new Clinic();
            Doctor d = new Doctor();
            Lab l = new Lab();
            GenericMSP gmsp = new GenericMSP();
            switch (m.getMsptype().getTypeId()) {
                case 1:
                    h = (Hospital) session.get(Hospital.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(h.getHospitalName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    gmsp.setDeleted(h.getDeleted());
                    finalResult.add(gmsp);
                    break;
                case 2:
                    c = (Clinic) session.get(Clinic.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(c.getClinicName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    gmsp.setDeleted(c.getDeleted());
                    finalResult.add(gmsp);
                    break;
                case 3:
                    d = (Doctor) session.get(Doctor.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(d.getDoctorName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    gmsp.setDeleted(d.getDeleted());
                    finalResult.add(gmsp);
                    break;
                case 4:
                    l = (Lab) session.get(Lab.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(l.getLabName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    gmsp.setDeleted(l.getDeleted());
                    finalResult.add(gmsp);
                    break;
            }
        }
        return finalResult;
    }

    //=====================================================================
    //=================View only not deleted MSPS==========================
    public static List<GenericMSP> viewMSPs() {
        List<GenericMSP> finalResult = new ArrayList<GenericMSP>();
        Query q = session.createQuery("from Msp");
        List<Msp> result = q.list();
        for (Msp m : result) {
            Hospital h = new Hospital();
            Clinic c = new Clinic();
            Doctor d = new Doctor();
            Lab l = new Lab();
            GenericMSP gmsp = new GenericMSP();
            switch (m.getMsptype().getTypeId()) {
                case 1:
                    h = (Hospital) session.get(Hospital.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(h.getHospitalName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    if (h.getDeleted() == 0) {
                        finalResult.add(gmsp);
                    }
                    break;
                case 2:
                    c = (Clinic) session.get(Clinic.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(c.getClinicName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    if (c.getDeleted() == 0) {
                        finalResult.add(gmsp);
                    }
                    break;
                case 3:
                    d = (Doctor) session.get(Doctor.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(d.getDoctorName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    if (d.getDeleted() == 0) {
                        finalResult.add(gmsp);
                    }
                    break;
                case 4:
                    l = (Lab) session.get(Lab.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(l.getLabName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    if (l.getDeleted() == 0) {
                        finalResult.add(gmsp);
                    }
                    break;
            }
        }
        return finalResult;
    }

    //===================Admin Delete MSP===================
    public static void DeleteMSP(int id, int typeid) {
        Msptype type = (Msptype) session.get(Msptype.class, id);

        switch (type.getTypeId()) {
            case 1:
                Hospital h = (Hospital) session.get(Hospital.class, typeid);
                h.setDeleted(1);
                break;
            case 2:
                Clinic c = (Clinic) session.get(Clinic.class, typeid);
                c.setDeleted(1);
                break;
            case 3:
                Doctor d = (Doctor) session.get(Doctor.class, typeid);
                d.setDeleted(1);
                break;
            case 4:
                Lab l = (Lab) session.get(Lab.class, typeid);
                l.setDeleted(1);
                break;
        }
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.createQuery("update Msp msp set msp.deleted = 1 where msp.msptype=:id and msp.typeId=:typeid ").setParameter("id", type).setParameter("typeid", typeid).executeUpdate();
        session.getTransaction().commit();
    }

    //======================================================
    //=============Admin Recover MSP========================
    public static void RecoverMSP(int id, int typeid) {
        Msptype type = (Msptype) session.get(Msptype.class, id);
        switch (type.getTypeId()) {
            case 1:
                Hospital h = (Hospital) session.get(Hospital.class, typeid);
                h.setDeleted(0);
                break;
            case 2:
                Clinic c = (Clinic) session.get(Clinic.class, typeid);
                c.setDeleted(0);
                break;
            case 3:
                Doctor d = (Doctor) session.get(Doctor.class, typeid);
                d.setDeleted(0);
                break;
            case 4:
                Lab l = (Lab) session.get(Lab.class, typeid);
                l.setDeleted(0);
                break;
        }
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.createQuery("update Msp msp set msp.deleted = 0 where msp.msptype=:id and msp.typeId=:typeid ").setParameter("id", type).setParameter("typeid", typeid).executeUpdate();
        session.getTransaction().commit();
    }

    //=====================================================
    public static List<Ratting> viewMspRatting(int mspId) {
        Msp msp = (Msp) session.get(Msp.class, mspId);
        Msptype msptype = msp.getMsptype();
        Query q = session.createQuery("from Ratting where msptype= :mspt and typeId= :tid")
                .setParameter("mspt", msptype)
                .setParameter("tid", msp.getMspId());
        List<Ratting> result = q.list();
        return result;
    }

    public static void main(String[] args) {
        //================== viewMSPs ===============================
//        System.out.println(viewMSPs().size());

        //================== viewMspRatting ===============================
//        System.out.println(viewMspRatting(1).size());
        //      RecoverMSP(3,1);
    }
}
