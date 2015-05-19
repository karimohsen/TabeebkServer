/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.utilty;

import com.tabeebkServer.pojo.Doctorspeciality;
import com.tabeebkServer.pojo.Hospitalspeciality;
import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class MyHospitalSpeciality {

    Doctorspeciality doctorSpeciality;
    boolean checked;

    public static ArrayList<MyHospitalSpeciality> getCheckedHospitals(ArrayList<Doctorspeciality> hList, ArrayList<Hospitalspeciality> speciality) {
        ArrayList<MyHospitalSpeciality> list = new ArrayList<MyHospitalSpeciality>();
        for (int i = hList.size()-1 ; i >= 0 ; i--) {
            for (int j = 0; j < speciality.size(); j++) {
                if (speciality.get(j).getDoctorspeciality().getDoctorSpecialityId().intValue() == hList.get(i).getDoctorSpecialityId().intValue()) {
                    hList.remove(i);
                    break;
                }
            }
        }
        for (int i = 0; i < speciality.size(); i++) {
            MyHospitalSpeciality hs = new MyHospitalSpeciality();
            hs.doctorSpeciality = speciality.get(i).getDoctorspeciality();
            hs.checked = true;
            list.add(hs);
        }
        for (int i = 0; i < hList.size(); i++) {
            MyHospitalSpeciality hs = new MyHospitalSpeciality();
            hs.doctorSpeciality = hList.get(i);
            hs.checked = false;
            list.add(hs);
        }
        return list;
    }

    public Doctorspeciality getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(Doctorspeciality doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
