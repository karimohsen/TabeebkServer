/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.utilty;

import com.tabeebkServer.pojo.Labspecialities;
import com.tabeebkServer.pojo.Labspeciality;
import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class MyLabSpecialities {
    Labspecialities labspeciality;
    boolean checked;

    public Labspecialities getLabspeciality() {
        return labspeciality;
    }

    public void setLabspeciality(Labspecialities labspeciality) {
        this.labspeciality = labspeciality;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public static ArrayList<MyLabSpecialities> getCheckedHospitals(ArrayList<Labspecialities> hList, ArrayList<Labspeciality> speciality) {
        ArrayList<MyLabSpecialities> list = new ArrayList<MyLabSpecialities>();
        for (int i = hList.size()-1 ; i >= 0 ; i--) {
            for (int j = 0; j < speciality.size(); j++) {
                if (speciality.get(j).getLabspecialities().getId().intValue() == hList.get(i).getId().intValue()) {
                    hList.remove(i);
                    break;
                }
            }
        }
        for (int i = 0; i < speciality.size(); i++) {
            MyLabSpecialities hs = new MyLabSpecialities();
            hs.labspeciality = speciality.get(i).getLabspecialities();
            hs.checked = true;
            list.add(hs);
        }
        for (int i = 0; i < hList.size(); i++) {
            MyLabSpecialities hs = new MyLabSpecialities();
            hs.labspeciality = hList.get(i);
            hs.checked = false;
            list.add(hs);
        }
        return list;
    }
}
