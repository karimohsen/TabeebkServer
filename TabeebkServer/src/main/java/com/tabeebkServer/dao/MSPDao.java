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
import com.tabeebkServer.pojo.Pharamacy;
import com.tabeebkServer.pojo.Ratting;
import com.tabeebkServer.pojo.User;
import com.tabeebkServer.utilty.GenericMSP;

/**
 *
 * @author HMA
 */
public class MSPDao {

    //===== session per dao
    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    //=====================Admin Add msp====================================
    public static void addMsp(int msptype, int typeid) {
        Msp msp = new Msp();
        msp.setDeleted(0);
        msp.setMsptype((Msptype) session.get(Msptype.class, msptype));
        msp.setTypeId(typeid);
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(msp);
        session.getTransaction().commit();

    }

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
            Pharamacy p = new Pharamacy();
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
                case 5:
                    p = (Pharamacy) session.get(Pharamacy.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(p.getPharamacyName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    gmsp.setDeleted(p.getDeleted());
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
            case 5:
                Pharamacy p = (Pharamacy) session.get(Pharamacy.class, typeid);
                p.setDeleted(1);
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
            case 5:
                Pharamacy p = (Pharamacy) session.get(Pharamacy.class, typeid);
                p.setDeleted(0);
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
        //refresh session
        session.clear();
        Query q = session.createQuery("from Ratting where msptype= :mspt and typeId= :tid")
                .setParameter("mspt", msptype)
                .setParameter("tid", msp.getMspId());
        List<Ratting> result = q.list();
        return result;
    }

    //=====================================================
    public static List<Ratting> viewUserMspRatting(int userId) {
        User user = (User) session.get(User.class, userId);
        //refresh session
        session.clear();
        Query q = session.createQuery("from Ratting where user=? ")
                .setParameter(0, user);
        List<Ratting> result = q.list();
        return result;
    }

    //================= get MSP's Details ======================================
    public static GenericMSP getMSPDetails(int msptypeid, int mspid) {
        Msptype msptype = (Msptype) session.get(Msptype.class, msptypeid);
        //refresh session
        session.clear();
        //Generic result
        GenericMSP gmsp = new GenericMSP();
        Query q = session.createQuery("from Msp m where msptype=? and typeId=?")
                .setParameter(0, msptype)
                .setParameter(1, mspid);
        List<Msp> result = q.list();
        for (Msp m : result) {
            Hospital h = new Hospital();
            Clinic c = new Clinic();
            Doctor d = new Doctor();
            Lab l = new Lab();
            Pharamacy p = new Pharamacy();
            switch (m.getMsptype().getTypeId()) {
                case 1:
                    h = (Hospital) session.get(Hospital.class, m.getTypeId());
                    if (h.getDeleted() == 0) {
                        gmsp.setMspId(m.getTypeId());
                        gmsp.setMspname(h.getHospitalName());
                        gmsp.setMspnamear(h.getHospitalNameAr());
                        //Email
                        gmsp.setMspEmail(h.getHospitalName());
                        gmsp.setMsptypeId(m.getMsptype().getTypeId());
                        gmsp.setMsptypename(m.getMsptype().getTypeName());
                    }
                    break;
                case 2:
                    c = (Clinic) session.get(Clinic.class, m.getTypeId());
                    if (c.getDeleted() == 0) {
                        gmsp.setMspId(m.getTypeId());
                        gmsp.setMspname(c.getClinicName());
                        gmsp.setMspnamear(h.getHospitalNameAr());
                        //Email
                        gmsp.setMspEmail(h.getHospitalName());
                        gmsp.setMsptypeId(m.getMsptype().getTypeId());
                        gmsp.setMsptypename(m.getMsptype().getTypeName());
                    }
                    break;
                case 3:
                    d = (Doctor) session.get(Doctor.class, m.getTypeId());
                    if (d.getDeleted() == 0) {
                        gmsp.setMspId(m.getTypeId());
                        gmsp.setMspname(d.getDoctorName());
                        gmsp.setMspnamear(d.getDoctorNameAr());
                        //Email
                        gmsp.setMspEmail(d.getDoctorName());
                        gmsp.setMsptypeId(m.getMsptype().getTypeId());
                        gmsp.setMsptypename(m.getMsptype().getTypeName());
                    }
                    break;
                case 4:
                    l = (Lab) session.get(Lab.class, m.getTypeId());
                    if (l.getDeleted() == 0) {
                        gmsp.setMspId(m.getTypeId());
                        gmsp.setMspname(l.getLabName());
                        gmsp.setMspnamear(l.getLabNameAr());
                        //Email
                        gmsp.setMspEmail(l.getLabName());
                        gmsp.setMsptypeId(m.getMsptype().getTypeId());
                        gmsp.setMsptypename(m.getMsptype().getTypeName());
                    }
                    break;
                case 5:
                    p = (Pharamacy) session.get(Pharamacy.class, m.getTypeId());
                    if (p.getDeleted() == 0) {
                        gmsp.setMspId(m.getTypeId());
                        gmsp.setMspname(p.getPharamacyName());
                        gmsp.setMspnamear(p.getPharamacyNameAr());
                        //Email
                        gmsp.setMspEmail(p.getPharamacyName());
                        gmsp.setMsptypeId(m.getMsptype().getTypeId());
                        gmsp.setMsptypename(m.getMsptype().getTypeName());
                    }
                    break;
            }
        }
        return gmsp;
    }

    public static void main(String[] args) {
        //================== viewMSPs ===============================
//        System.out.println(viewMSPs().size());

        //================== viewMspRatting ===============================
//        System.out.println(viewMspRatting(1).size());
        //      RecoverMSP(3,1);
    }
}
