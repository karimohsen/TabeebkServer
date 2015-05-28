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
import com.tabeebkServer.pojo.DoctorClinc;
import com.tabeebkServer.pojo.Doctorspeciality;
import com.tabeebkServer.pojo.Gender;
import com.tabeebkServer.pojo.Hospital;
import com.tabeebkServer.pojo.Hospitalspeciality;
import com.tabeebkServer.pojo.Lab;
import com.tabeebkServer.pojo.Labspecialities;
import com.tabeebkServer.pojo.Labspeciality;
import com.tabeebkServer.pojo.Msp;
import com.tabeebkServer.pojo.Msptype;
import com.tabeebkServer.pojo.Pharamacy;
import com.tabeebkServer.pojo.Ratting;
import com.tabeebkServer.pojo.User;
import com.tabeebkServer.utilty.GenericMSP;
import java.util.TreeSet;

/**
 *
 * @author HMA
 */
public class MSPDao {

    //===== session per dao
    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();
    private int result = 0;

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

    //==================================================
    //-------- excel file methods ---------------------
    //==================================================
    public Gender getGender(int id) {
        Gender gendr = null;
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
            gendr = (Gender) session.createQuery("from Gender g where g.genderId = :genId ").setInteger("genId", id).uniqueResult();

        }
        return gendr;
    }

    public Msptype getMspType(int type) {
        Msptype mspType = null;
        mspType = (Msptype) session.createQuery("from Msptype msT where msT.typeId = :typeID").setInteger("typeID", type).uniqueResult();
        return mspType;
    }

    public Lab getLabByID(int id) {

       
        Lab lab = (Lab) session.createQuery("from Lab l where l.labId = :id ").setInteger("id", id).uniqueResult();
        return lab;
    }

    public Pharamacy getPharamacyByID(int id) {
     
        Pharamacy pharamcy = (Pharamacy) session.createQuery("from Pharamacy ph where ph.pharamacyId = :id ").setInteger("id", id).uniqueResult();
        return pharamcy;
    }

    public Hospital getHospitalByID(int id) {
    
        Hospital hospital = (Hospital) session.createQuery("from Hospital h where h.hospitalId = :id ").setInteger("id", id).uniqueResult();
        return hospital;
    }

    public Hospital getHospitalByName(String hosName) {
       
        Hospital hospital = (Hospital) session.createQuery("from Hospital h where h.hospitalName = :name ").setString("name", hosName.toLowerCase()).uniqueResult();
        return hospital;
    }

    public Clinic getClinicByName(String cName) {
      
        Clinic clinc = (Clinic) session.createQuery("from Clinic c where c.clinicName=:clinName").setString("clinName", cName.toLowerCase()).uniqueResult();
        session.evict(clinc);
        session.flush();
        return clinc;
    }

    public Doctor getDoctorByID(int id) {
        
        Doctor doctor = (Doctor) session.createQuery("from Doctor d where d.doctorId = :id ").setInteger("id", id).uniqueResult();
        return doctor;

    }

    public Doctorspeciality getDoctorSpeciality(String name) {
        Doctorspeciality docSpe = null;
    
            docSpe = (Doctorspeciality) session.createQuery("from Doctorspeciality ds where ds.doctorSpecialityName=:docSpec").setString("docSpec", name.toLowerCase()).uniqueResult();
            session.evict(docSpe);
        
        return docSpe;
    }

    public Labspecialities getLabSpeciality(String name) {
       
        Labspecialities labSpec = (Labspecialities) session.createQuery("from Labspecialities l where l.specialityName = :lSpec").setString("lSpec", name.toLowerCase()).uniqueResult();
        session.evict(labSpec);
        return labSpec;
    }

    public boolean isDoctorExist(String name) {
      
        Doctor doctor = (Doctor) session.createQuery("from Doctor d where d.doctorName = :docName").setString("docName", name.toLowerCase()).uniqueResult();
        if (doctor != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isHospitalExist(String name) {
       
        Hospital hospital = (Hospital) session.createQuery(" from Hospital h where h.hospitalName = :hosName").setString("hosName", name.toLowerCase()).uniqueResult();
        if (hospital != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isClinicExist(String name) {
       
        Clinic c = (Clinic) session.createQuery(" from Clinic c where c.clinicName = :clinName").setString("clinName", name.toLowerCase()).uniqueResult();
        if (c != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPharamacyExist(String name) {
        
        Pharamacy pharamcy = (Pharamacy) session.createQuery("from Pharamacy ph where ph.pharamacyName = :pharmcName").setString("pharmcName", name.toLowerCase()).uniqueResult();
        if (pharamcy != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLabExist(String name) {
        
        Lab lab = (Lab) session.createQuery("from Lab l where l.labName = :labname ").setString("labname", name.toLowerCase()).uniqueResult();
        if (lab != null) {
            return true;
        } else {
            return false;
        }
    }

    public int saveMsp(Msp msp) {
        if (msp != null) {
            if (!session.getTransaction().isActive()) {
                session.getTransaction().begin();
            }
            session.persist(msp);
            session.getTransaction().commit();
            session.evict(msp);
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }

    public int insertDoctorClinics(TreeSet<Clinic> clinics) {
        if (clinics != null) {
            for (Clinic clinic : clinics) {
                if (!session.getTransaction().isActive()) {
                    session.getTransaction().begin();
                }
                session.saveOrUpdate(clinic);
                session.getTransaction().commit();
                session.evict(clinic);
            }

            result = 1;
        } else {
            result = 0;
        }

        return result;
    }

    public int insertDoctorClinic(Clinic c) {

        if (c != null) {
            if (!session.getTransaction().isActive()) {
                session.getTransaction().begin();
            }
            session.saveOrUpdate(c);
            session.getTransaction().commit();
            session.evict(c);
            result = 1;
        } else {
            result = 0;
        }

        return result;
    }

    public int saveDoctors(ArrayList<Doctor> doctors) {

        if (doctors != null) {
            for (Doctor d : doctors) {
                if (!session.getTransaction().isActive()) {
                    session.getTransaction().begin();
                }
                session.saveOrUpdate(d);
                session.getTransaction().commit();
                session.evict(d);
                session.flush();
            }
            result = 1;
        } else {
            result = 0;
        }

        return result;
    }

    public int saveDoctor(Doctor doctor) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        if (doctor != null) {
            session.clear();
            session.saveOrUpdate(doctor);
            session.getTransaction().commit();
            session.evict(doctor);
            result = 1;
        } else {
            result = 0;
        }

        return result;
    }

    public int saveDoctorClinic(DoctorClinc dc) {
        if (!session.getTransaction().isActive()) {
            session.getTransaction().begin();
        }
        if (dc != null) {
            session.persist(dc);
            session.getTransaction().commit();
            session.evict(dc);
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }

    public int saveDoctorSpeciality(Doctorspeciality ds) {
        if (!session.getTransaction().isActive()) {
            session.getTransaction().begin();
        }
        if (ds != null) {
            session.persist(ds);
            session.getTransaction().commit();
            session.evict(ds);
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }

    public int saveHospitalSpeciality(Hospitalspeciality specs) {

        if (specs != null) {
            if (!session.getTransaction().isActive()) {
                session.getTransaction().begin();
            }
            session.saveOrUpdate(specs);
            session.getTransaction().commit();
            session.evict(specs);
            result = 1;

        } else {
            result = 0;
        }
        return result;
    }

    public int saveLabSpeciality(Labspeciality specs) {

        if (specs != null) {
            if (!session.getTransaction().isActive()) {
                session.getTransaction().begin();
            }
            session.persist(specs);
            session.getTransaction().commit();
            session.evict(specs);
            result = 1;

        } else {
            result = 0;
        }
        return result;
    }

    public Hospital insertHospital(Hospital hospital) {
       Hospital h=null;
        if (!session.getTransaction().isActive()) {
            session.getTransaction().begin();
        }
        if (hospital != null) {
            if(!isHospitalExist(hospital.getHospitalName().toLowerCase())){
            session.saveOrUpdate(hospital);
            session.getTransaction().commit();
            session.evict(hospital);
            h=hospital;
            }else{
              h = (Hospital) session.createQuery(" from Hospital h where h.hospitalName = :hosName").setString("hosName", hospital.getHospitalName().toLowerCase());
           
            }
                
        } else {
            h=null;
        }

       return h;
    }

    public int insertPharmacy(Pharamacy ph) {
        if (!session.getTransaction().isActive()) {
            session.getTransaction().begin();
        }
        if (ph != null) {
            session.saveOrUpdate(ph);
            session.getTransaction().commit();
            session.evict(ph);
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }

    public int insertLab(Lab lab) {
        if (!session.getTransaction().isActive()) {
            session.getTransaction().begin();
        }
        if (lab != null) {
            session.saveOrUpdate(lab);
            session.getTransaction().commit();
            session.evict(lab);
            result = 1;
        } else {
            result = 0;
        }
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
