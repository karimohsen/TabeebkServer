/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.utilty;

import com.tabeebkServer.pojo.Clinic;
import com.tabeebkServer.pojo.DoctorClinc;
import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class MyDoctorClinics {
    Clinic clinic;
    boolean checked;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    public static ArrayList<MyDoctorClinics>getCheckedClinics(ArrayList<Clinic> cList, ArrayList<DoctorClinc> speciality){
        ArrayList<MyDoctorClinics> list = new ArrayList<MyDoctorClinics>();
        for (int i = cList.size()-1 ; i >= 0 ; i--) {
            for (int j = 0; j < speciality.size(); j++) {
                if (speciality.get(j).getClinic().getClinicId().intValue() == cList.get(i).getClinicId().intValue()) {
                    cList.remove(i);
                    break;
                }
            }
        }
        for (int i = 0; i < speciality.size(); i++) {
            MyDoctorClinics hs = new MyDoctorClinics();
            hs.clinic = speciality.get(i).getClinic();
            hs.checked = true;
            list.add(hs);
        }
        for (int i = 0; i < cList.size(); i++) {
            MyDoctorClinics hs = new MyDoctorClinics();
            hs.clinic = cList.get(i);
            hs.checked = false;
            list.add(hs);
        }
        return list;
    }
}
