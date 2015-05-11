/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.dao;

import com.tabeebkServer.pojo.Area;
import com.tabeebkServer.pojo.Branche;
import com.tabeebkServer.pojo.City;
import com.tabeebkServer.pojo.Country;
import com.tabeebkServer.pojo.Msptype;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class BranchDao {
    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();
    public static void addBranch(int areaId,String address,String adressAr,String latitude,String longitude,String branchName,String branchNameAr,int mspType,int TypeId,int country,int city){
    if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
    Branche b = new Branche();
    b.setArea((Area)session.get(Area.class,areaId));
    b.setBrancheAddress(address);
    b.setBrancheAddressAr(adressAr);
    b.setBrancheLatatitude(latitude);
    b.setBrancheLongtitude(longitude);
    b.setBrancheName(branchName);
    b.setBrancheNameAr(branchNameAr);
    b.setMsptype((Msptype)session.get(Msptype.class, mspType));
    b.setTypeId(TypeId);
    b.setCountry((Country)session.get(Country.class, country));
    b.setCity((City)session.get(City.class, city));
    session.save(b);
    session.getTransaction().commit();
    
    }
}
