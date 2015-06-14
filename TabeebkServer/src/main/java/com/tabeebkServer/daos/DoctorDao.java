/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import com.tabeebkServer.pojo.Doctor;
import com.tabeebkServer.pojo.Doctorspeciality;
import com.tabeebkServer.pojo.Hospital;
import com.tabeebkServer.pojo.Hospitalspeciality;
import com.tabeebkServer.pojo.Lab;
import com.tabeebkServer.pojo.Telephone;
import java.util.Set;

/**
 *
 * @author Amal
 */
public class DoctorDao {
 String [] queries={""," select  d.hospitalName , d.hospitalId from Hospital d"," select  d.clinicName , d.clinicId from Clinic d"};
    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();
    List<String[]> clinicResult;

    public List getAllDoctors(List<String> selections) {

        String query = "select ";
        for (int i = 0; i < selections.size(); i++) {
            query += "d." + selections.get(i);
            query += " , ";
        }
        query += "  d.doctorId from Doctor d";
        System.out.println(query);
        Query q = session.createQuery(query);
        List res = q.list();
        return res;

    }

    public List getDoctorPhones(int doctorId) {
        Criteria criteria1 = session.createCriteria(Telephone.class, "t");
        criteria1 = criteria1.add(Restrictions.eq("t.typeId", doctorId)).add(Restrictions.eq("t.msptype.typeId", 3));
        List<Telephone> phones = criteria1.list();
        return phones;
    }

    //////clinic dao 
//    public List getAllClinics(List<String> selections) {
//        List<String[]> finalResult = new ArrayList<>();
//        String[] arr;
//        String query = "select ";
//
//        for (int i = 0; i < selections.size(); i++) {
//
//            if (selections.get(i).equals("clinicName") || selections.get(i).equals("clinicNameAr")) {
//                query += "d." + selections.get(i);
//                query += " , ";
//            }
//
//        }
//        query += "  d.clinicId from Clinic d";
//
//        System.out.println(query);
//        Query q = session.createQuery(query);
//        List res = q.list();
//
//        Iterator itr = res.iterator();
//        int clinicId;
//        while (itr.hasNext()) {
//            Object[] selectedColumns = (Object[]) itr.next();
//            arr = new String[selections.size()];
//            clinicId = (Integer) selectedColumns[selectedColumns.length - 1];
//            for (int i = 0; i < selectedColumns.length - 1; i++) {
//                int arrIdx = 0;
//                arr[arrIdx] = (String) selectedColumns[i];
//                arrIdx++;
//              //finalResult.add((String) selectedColumns[i]);
//                //  System.out.println("hhhhhhhhhhhhh "+finalResult.get(i)[0]+ " ");
//            }
//            finalResult.add(arr);
//            System.out.println("hhhhhhhhhhhhhhhhhhh id " + clinicId);
//        }
//
//        return finalResult;
//
//    }

    ///////////////////// heeeeeeeeeeeeeeeeeeeeeeeeeere
    public List getAllClinics2(List<String> selections , int mspType) {
        clinicResult = new ArrayList<>();
        String query =queries[mspType];
        Query q = session.createQuery(query);
        List res = q.list();
        int arrayLength = selections.size() + 1;
        String resultArray[];
        Iterator itr = res.iterator();
        int clinicId;
        while (itr.hasNext()) {
            Object[] selectedColumns = (Object[]) itr.next();
            resultArray = new String[arrayLength];
            clinicId = (Integer) selectedColumns[selectedColumns.length - 1];
            resultArray[0] = clinicId + "";
            clinicResult.add(resultArray);
        }

        for (int i = 0; i < selections.size(); i++) {
            if (selections.get(i).equals("clinicName") || selections.get(i).equals("clinicNameAr")) {
                getClinicNames(i + 1, selections.get(i));

            } 
           else if (selections.get(i).equals("hospitalName") || selections.get(i).equals("hospitalNameAr")) {
                getHospitalNames(i+1,  selections.get(i));
           }
            
          
        else  if(selections.get(i).equals("phones")){
               
                 getClinicPhones(i+1,mspType);

            }
            else if(selections.get(i).equals("labs")){
                getHospitalComponents(i+1, selections.get(i));
                
            }
            
             else if(selections.get(i).equals("hospitalspecialities")){
                 getHospitalSpecialities(i+1);
             }

               else  {
                getClinicAddress(i+1, selections.get(i),mspType);
                    
            }
        }
//        System.out.println("fewfewf " + clinicResult.get(0)[0]);
//        System.out.println("fewfewf " + clinicResult.get(0)[1]);
//        System.out.println("fewfewf " + clinicResult.get(0)[2]);
//        System.out.println("fewfewf " + clinicResult.get(0)[3]);
//        System.out.println("fewfewf " + clinicResult.get(0)[4]);
//        System.out.println("fewfewf " + clinicResult.get(0)[5]);
//        System.out.println("fewfewf " + clinicResult.get(1)[0]);
//        System.out.println("fewfewf " + clinicResult.get(1)[1]);
//        System.out.println("fewfewf " + clinicResult.get(1)[2]);
//        System.out.println("fewfewf " + clinicResult.get(1)[3]);
//        System.out.println("fewfewf " + clinicResult.get(1)[4]);
//        System.out.println("fewfewf " + clinicResult.get(1)[5]);
        return clinicResult;
    }

    public void getClinicNames(int index, String selction) {

        String query;
        Query q;
        List res;
        for (int i = 0; i < clinicResult.size(); i++) {
            query = " select d." + selction + " from Clinic d where d.clinicId = " + clinicResult.get(i)[0];
            q = session.createQuery(query);
            res = q.list();
            clinicResult.get(i)[index] = (String) res.get(0);
        }

    }
    
    public void getClinicPhones(int index , int mspType){
            String query;
        Query q;
        List res;
        for (int i = 0; i < clinicResult.size(); i++) {
            query = "select t.telephoneNumber from Telephone t where t.typeId = "+clinicResult.get(i)[0]+" and t.msptype = "+mspType;
            q = session.createQuery(query);
            res = q.list();
            String allPhones="";
                for (Object re : res) {
                    allPhones+=(String)re;
                    allPhones+=" \n ";
                }
                 clinicResult.get(i)[index] = allPhones;
        }
    }
    
    public void getClinicAddress(int index , String selection , int mspType){
                 String query;
        Query q;
        List res;
        String addressType="";
        if(selection.startsWith("city"))addressType="city.";
        if(selection.startsWith("area"))addressType="area.";
        
        for (int i = 0; i < clinicResult.size(); i++) {
            query = "select t."+addressType+selection+" from Branche t where t.typeId = "+clinicResult.get(i)[0]+" and t.msptype = "+mspType;
            q = session.createQuery(query);
            res = q.list();
            String allAddresses="";
                for (Object re : res) {
                    allAddresses+=(String)re+"\n";
                }
                 clinicResult.get(i)[index] = allAddresses;
        }
    }
    
    /////////hospital daos
      public void getHospitalNames(int index, String selction) {

        String query;
        Query q;
        List res;
        for (int i = 0; i < clinicResult.size(); i++) {
            query = " select d." + selction + " from Hospital d where d.hospitalId = " + clinicResult.get(i)[0];
            q = session.createQuery(query);
            res = q.list();
            clinicResult.get(i)[index] = (String) res.get(0);
        }

    }
      
      public void getHospitalComponents(int index , String selection){
          System.out.println("lkrgnrelhbknrelnhbelfnbklkkkkkkkkkkkkkkkkkkkkkkkk");
           String query;
        Query q;
        List res;
        String labNames="";
        for (int i = 0; i < clinicResult.size(); i++) {
            labNames="";
            query = " from Lab d where d.hospital.hospitalId = " + clinicResult.get(i)[0];
            q = session.createQuery(query);
            res = q.list();
          //  Hospital hospital=(Hospital) res.get(0);
               for (int j = 0; j < res.size(); j++) {
                   Lab lab=(Lab) res.get(j);
                   System.out.println("lab names "+lab.getLabName());
                   labNames+=lab.getLabName()+" \n ";
               }

            clinicResult.get(i)[index] = labNames;
            System.out.println("fsgrbrgb "+labNames);
        }
      }
      
       public void getHospitalSpecialities(int index ){
          System.out.println("lkrgnrelhbknrelnhbelfnbklkkkkkkkkkkkkkkkkkkkkkkkk");
           String query;
        Query q;
        List res;
        String labNames="";
        for (int i = 0; i < clinicResult.size(); i++) {
            labNames="";
            query = " from Hospital d where d.hospitalId = " + clinicResult.get(i)[0];
            q = session.createQuery(query);
            res = q.list();
            Hospital hospital=(Hospital) res.get(0);
          for(Hospitalspeciality h  : hospital.getHospitalspecialities())
          {
                  
                   System.out.println("lab names "+h.getDoctorspeciality().getDoctorSpecialityDescription());
                   labNames+=h.getDoctorspeciality().getDoctorSpecialityName()+" \n ";
               }

            clinicResult.get(i)[index] = labNames;
            System.out.println("fsgrbrgb "+labNames);
        }
      }
    ///////////////////////////////////////////////////

    public static void main(String[] args) {
        DoctorDao d = new DoctorDao();
        System.out.println("djcndcndk \n fvrbverb");
//        List<String> selctions = new ArrayList<>();
//        selctions.add("clinicNameAr");
//        selctions.add("phone");
//        selctions.add("cityName");
//        selctions.add("areaNameEn");
//        selctions.add("brancheAddress");
//        d.getAllClinics2(selctions);
//        DoctorDao d = new DoctorDao();
//        List<String> selctions = new ArrayList<String>();
//        selctions.add("doctorName");
//        selctions.add("genderId");
//        selctions.add("doctorspeciality");
//
//        List l = d.getAllDoctors(selctions);
//        Iterator itr = l.iterator();
//        while (itr.hasNext()) {
//            Object[] res = (Object[]) itr.next();
//            String docname = (String) res[0];
//            System.out.println("name is " + docname);
//            int docgender = (Integer) res[1];
//            System.out.println("gender is " + docgender);
//            Doctorspeciality specialtity = (Doctorspeciality) res[2];
//            System.out.println("soec is " + specialtity.getDoctorSpecialityDescription());
//        }
        /////////////////////////
//        Doctor doc;
//        for (int i = 0; i < l.size(); i++) {
//            doc=(Doctor) l.get(i);
//            Telephone t=(Telephone) d.getDoctorPhones( doc.getDoctorId()).get(0);
//            System.out.println("doctor name is "+doc.getDoctorName());
//            System.out.println("phnoe "+t.getTelephoneNumber());
//        }

    }
}
