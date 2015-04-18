/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import static com.tabeebkServer.dao.MICDao.session;
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
                    finalResult.add(gmsp);
                    break;
                case 2:
                    c = (Clinic) session.get(Clinic.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(c.getClinicName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    finalResult.add(gmsp);
                    break;
                case 3:
                    d = (Doctor) session.get(Doctor.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(d.getDoctorName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    finalResult.add(gmsp);
                    break;
                case 4:
                    l = (Lab) session.get(Lab.class, m.getTypeId());
                    gmsp.setMspId(m.getTypeId());
                    gmsp.setMspname(l.getLabName());
                    gmsp.setMsptypeId(m.getMsptype().getTypeId());
                    gmsp.setMsptypename(m.getMsptype().getTypeName());
                    finalResult.add(gmsp);
                    break;
            }
        }
        return finalResult;
    }

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
    }
}
