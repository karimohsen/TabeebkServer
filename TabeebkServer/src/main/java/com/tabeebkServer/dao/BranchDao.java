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
import com.tabeebkServer.session.factory.HibernateUtilFactory;
import java.util.ArrayList;
import java.util.TreeSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Karim
 */
public class BranchDao {

    static SessionFactory fact = HibernateUtilFactory.getSessionFactory();
    static Session session = fact.openSession();
    private int result=0;
    public static void addBranch(int areaId, String address, String adressAr, String latitude, String longitude, String branchName, String branchNameAr, int mspType, int TypeId, int country, int city) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        Branche b = new Branche();
        b.setArea((Area) session.get(Area.class, areaId));
        b.setBrancheAddress(address);
        b.setBrancheAddressAr(adressAr);
        b.setBrancheLatatitude(latitude);
        b.setBrancheLongtitude(longitude);
        b.setBrancheName(branchName);
        b.setBrancheNameAr(branchNameAr);
        b.setMsptype((Msptype) session.get(Msptype.class, mspType));
        b.setTypeId(TypeId);
        b.setCountry((Country) session.get(Country.class, country));
        b.setCity((City) session.get(City.class, city));
        session.save(b);
        session.getTransaction().commit();

    }

    public static void updateBranch(int branchId, int areaId, String address, String adressAr, String latitude, String longitude, String branchName, String branchNameAr, int country, int city) {
        session.clear();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        Branche b = (Branche) session.get(Branche.class, branchId);
        b.setArea((Area) session.get(Area.class, areaId));
        b.setBrancheAddress(address);
        b.setBrancheAddressAr(adressAr);
        b.setBrancheLatatitude(latitude);
        b.setBrancheLongtitude(longitude);
        b.setBrancheName(branchName);
        b.setBrancheNameAr(branchNameAr);
        b.setCountry((Country) session.get(Country.class, country));
        b.setCity((City) session.get(City.class, city));
        session.update(b);
        session.getTransaction().commit();

    }

    public static Branche getBranch(int type, int typeId) {
        ArrayList<Branche> list = (ArrayList<Branche>) session.createQuery("From Branche b where b.msptype = :mtype and b.typeId = :id").setParameter("mtype", (Msptype)session.get(Msptype.class, type)).setParameter("id", typeId).list();
        if(list.size() > 0)
            return list.get(0);
        else
            return null;
    }
    
    //==================================================
    //-------- excel file methods ---------------------
    //==================================================
    
      public Country getCountry(String countryName) {
        if(!session.getTransaction().isActive())
          session.beginTransaction();
        Country country = (Country) session.createQuery("from Country c where c.countryName = :contryName").setString("contryName", countryName.toLowerCase()).uniqueResult();
        return country;
    }

    public City getCity(String cityName) {
        if(!session.getTransaction().isActive())
        session.beginTransaction();
        City city = (City) session.createQuery("from City c where c.cityName = :cityName").setString("cityName", cityName.toLowerCase()).uniqueResult();
        return city;
    }

    public int insertArea(TreeSet<Area> areas) {
        
        if (areas != null) {
            for (Area area : areas) {
                if (!isAreaExist(area.getAreaNameEn().toLowerCase())) {
                    if(!session.getTransaction().isActive())
                    session.getTransaction().begin();
                    session.persist(area);
                    session.getTransaction().commit();
                    session.evict(area);
                }
            }
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }
 public boolean isAreaExist(String name) {
     if(!session.getTransaction().isActive())   
     session.beginTransaction();
        Area area = (Area) session.createQuery("from Area a where a.areaNameEn = :areaName").setString("areaName", name.toLowerCase()).uniqueResult();
        if (area != null) {
            return true;
        } else {
            return false;
        }
    }
 
    public Area insertArea(Area area) {
        
        Area a=null;
        if (area != null) {
           
            if (!isAreaExist(area.getAreaNameEn().toLowerCase())) {
               if(!session.getTransaction().isActive())
                session.getTransaction().begin();
                session.persist(area);
                session.getTransaction().commit();
                session.evict(area);
                a=area;
              
            }
            else{
             a=(Area)session.createQuery("from Area a where a.areaNameEn = :areaName").setString("areaName", area.getAreaNameEn().toLowerCase()).uniqueResult();
               
            }
        } else {
            a=null;
        }
        return a;
    }

    public int insertBranches(TreeSet<Branche> branches) {
        
        if (branches != null) {
            for (Branche b : branches) {
                if(!session.getTransaction().isActive())
                session.getTransaction().begin();
                session.persist(b);
                session.getTransaction().commit();
                session.evict(b);
            }
            result = 1;
        } else {
            result = 0;
        }
        
        return result;
    }
    public int insertClinicBranches(Branche branches) {
        
        if (branches != null) {
            if(!session.getTransaction().isActive())
                session.getTransaction().begin();
                session.persist(branches);
                session.getTransaction().commit();
                session.evict(branches);
        result = 1;    
        }
            
         else {
            result = 0;
        }
       
        return result;
    }
}
