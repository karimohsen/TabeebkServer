/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.parseExcel;

import com.tabeebkServer.dao.BranchDao;
import com.tabeebkServer.dao.MSPDao;
import com.tabeebkServer.dao.TelephoneDao;
import com.tabeebkServer.pojo.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author azza
 */
public class DoctorBook {

    //Azza
    private Doctor mDoctor = null;
    private Doctorspeciality mDoctorSpeciality = null;
    private Telephone doctorTelephone = null;
    private Telephone clinicTelephone = null;
    private int gender = 0;
    private MSPDao mspDao = null;
    private Msptype mspType = null;
    private City city = null;
    private BranchDao addressDao = null;
    private Set<Clinic> docClinics = null;
    private ArrayList<Doctor> doctorList = null;
    private Msp msp = null;

    public DoctorBook(Workbook doctorBook) {
        mspDao = new MSPDao();
        mspType = mspDao.getMspType(Constants.CLINIC);
        addressDao = new BranchDao();
        //docClinics = new TreeSet<Clinic>();
        Sheet docSheet = null;
        if (doctorBook instanceof XSSFWorkbook) {
            docSheet = (XSSFSheet) doctorBook.getSheet(Constants.CLINIC_SHEET);
        } else {
            docSheet = (HSSFSheet) doctorBook.getSheet(Constants.CLINIC_SHEET);
        }
        Iterator<Row> rowIterator = docSheet.iterator();
        Row row = null;
        doctorList = createDoctor(doctorBook);
        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            if (row.getRowNum() == 0) {
                continue;
            }

            createClinics(row, doctorBook);
        }
        System.out.println("Doctor Data inserted successfully");
    }

    private void createClinics(Row clinicRow, Workbook doctorSheet) {
        Clinic c = null;
        if (clinicRow.getCell(0) != null) {
            String clinicNamesEn = clinicRow.getCell(1).getStringCellValue();
            String clinicNamesAr = clinicRow.getCell(2).getStringCellValue();

            if (clinicNamesEn != null && clinicNamesAr != null) {
                c = new Clinic(clinicNamesEn, clinicNamesAr, 0);

                int i = saveDoctorClinic(c);
                if (i == 1) {
                    msp = new Msp(mspDao.getMspType(Constants.CLINIC), c.getClinicId(), 0);
                    mspDao.saveMsp(msp);
                    TreeSet<Telephone> telephones = createClinicTelelphone(c, doctorSheet);
                    saveTelephone(telephones);
                    TreeSet<Branche> brnch = createClinicBranch(c, doctorSheet);
                    saveDoctorClinicBranches(brnch);
                }
            }
            ArrayList<String> doctorRowNums = splitString(clinicRow.getCell(0).getStringCellValue());
            for (int i = 0; i < doctorList.size(); i++) {
                for (String num : doctorRowNums) {
                    if (i == Integer.parseInt(num) - 2) {
                        DoctorClincId dcId = new DoctorClincId(doctorList.get(i).getDoctorId(), c.getClinicId());
                        DoctorClinc dc = new DoctorClinc(dcId, c, doctorList.get(i), 0);
                        mspDao.saveDoctorClinic(dc);
//                        docClinics.add(c);          
//                       doctorList.get(i).setDoctorClincs(docClinics);
//                        mspDao.saveDoctor(doctorList.get(i));
                    } else {
                        //docClinics.remove(c);
                        continue;
                    }
                }
            }
        }
    }

    private TreeSet<Branche> createClinicBranch(Clinic c, Workbook wb) {
        Sheet clinicAdress = null;
        if (wb instanceof XSSFWorkbook) {
            clinicAdress = (XSSFSheet) wb.getSheet(Constants.CLINIC_ADRESS_SHEET);
        } else {
            clinicAdress = (HSSFSheet) wb.getSheet(Constants.CLINIC_ADRESS_SHEET);
        }
        TreeSet<Branche> branchs = new TreeSet<Branche>();
        Iterator<Row> rowIterator = clinicAdress.iterator();
        Row clinicAddRow = null;
        Area area = null;
        Branche b = null;
        while (rowIterator.hasNext()) {
            clinicAddRow = rowIterator.next();
            if (clinicAddRow.getRowNum() == 0) {
                continue;
            }
            if (clinicAddRow.getCell(0) != null) {
                String clinicName = clinicAddRow.getCell(0).getStringCellValue().toLowerCase();
                //double clinicRowNum = clinicAddRow.getCell(0).getNumericCellValue();
                if (c.getClinicName().equalsIgnoreCase(clinicName)) {
                    //if (clincRow.getRowNum() + 1 == clinicRowNum) {
                    String cityNameEn = clinicAddRow.getCell(1).getStringCellValue();
                    city = addressDao.getCity(cityNameEn);
                    String areaNamesEn = clinicAddRow.getCell(2).getStringCellValue();
                    String areaNamesAr = clinicAddRow.getCell(3).getStringCellValue();
                    String fullAddressEn = clinicAddRow.getCell(4).getStringCellValue();
                    String fullAddressAr = clinicAddRow.getCell(5).getStringCellValue();
                    area = new Area(city, areaNamesEn, areaNamesAr);
                    area = addressDao.insertArea(area);
                    b = new Branche(city, area, mspType, areaNamesEn, areaNamesAr, fullAddressEn, fullAddressAr, c.getClinicId(), 0);
                    branchs.add(b);
                }
            }
        }
        return branchs;
    }

    private TreeSet<Telephone> createClinicTelelphone(Clinic c, Workbook ds) {
        TreeSet<Telephone> telphons = new TreeSet<Telephone>();
        Sheet clinicTel = null;
        if (ds instanceof XSSFWorkbook) {
            clinicTel = (XSSFSheet) ds.getSheet(Constants.CLINIC_TEL_SHEET);
        } else {
            clinicTel = (HSSFSheet) ds.getSheet(Constants.CLINIC_TEL_SHEET);
        }
        Iterator<Row> rowIterator = clinicTel.iterator();
        Row clinicTelRow = null;
        while (rowIterator.hasNext()) {
            clinicTelRow = rowIterator.next();
            if (clinicTelRow.getRowNum() == 0) {
                continue;
            }
            if (clinicTelRow.getCell(0) != null && clinicTelRow.getCell(1) != null) {

                String clinicName = clinicTelRow.getCell(0).getStringCellValue().toLowerCase();
                //double clinicRowNum = clinicTelRow.getCell(0).getNumericCellValue();
                String telephs = clinicTelRow.getCell(1).getStringCellValue();
                if (c.getClinicName().equalsIgnoreCase(clinicName)) {
                    //if (clinRow.getRowNum() + 1 == clinicRowNum) {
                    clinicTelephone = new Telephone(mspType, c.getClinicId(), telephs);
                    telphons.add(clinicTelephone);
                }
            }
        }
        return telphons;
    }

    private ArrayList<Doctor> createDoctor(Workbook ds) {
        Sheet docSheet = null;
        if (ds instanceof XSSFWorkbook) {
            docSheet = (XSSFSheet) ds.getSheet(Constants.DOCTOR_SHEET);
        } else {
            docSheet = (HSSFSheet) ds.getSheet(Constants.DOCTOR_SHEET);
        }
        ArrayList<Doctor> docs = new ArrayList<Doctor>();
        Iterator<Row> rowIterator = docSheet.iterator();
        Row row = null;
        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            if (row.getCell(0) != null && row.getCell(1) != null && row.getCell(2) != null && row.getCell(3) != null && row.getCell(4) != null) {
                String doctorNameEn = row.getCell(0).getStringCellValue();
                String doctorNameAr = row.getCell(1).getStringCellValue();
                String doctorDegreeEn = row.getCell(2).getStringCellValue();
                String doctorDegreeAr = row.getCell(3).getStringCellValue();
                String doctorGender = row.getCell(4).getStringCellValue();
                if (doctorGender.trim().equalsIgnoreCase(Constants.MALE)) {
                    gender = Constants.MALE_ID;
                } else if (doctorGender.trim().equalsIgnoreCase(Constants.FEMALE)) {
                    gender = Constants.FEMALE_ID;
                }
                mDoctor = new Doctor(mspDao.getGender(gender), doctorNameEn, doctorNameAr, doctorDegreeEn, doctorDegreeAr, 0);
                mDoctor.setDoctorspeciality(createDoctorSpeciality(row));
                int saved = mspDao.saveDoctor(mDoctor);
                if (saved == 1) {
                    msp = new Msp(mspDao.getMspType(Constants.DOCTOR), mDoctor.getDoctorId(), 0);
                    mspDao.saveMsp(msp);
                }
                docs.add(mDoctor);
            }
        }
        return docs;
    }

    private int saveDoctorClinic(Clinic docC) {
        return mspDao.insertDoctorClinic(docC);
    }

    private int saveDoctorClinicBranches(TreeSet<Branche> branches) {
        return addressDao.insertBranches(branches);
    }

    private int saveTelephone(TreeSet<Telephone> docTelephons) {
        TelephoneDao telephoneDao = new TelephoneDao();
        return telephoneDao.insertTelephones(docTelephons);
    }

    private Doctorspeciality createDoctorSpeciality(Row row) {
        String specialityNameEn = row.getCell(5).getStringCellValue();
        return mDoctorSpeciality = mspDao.getDoctorSpeciality(specialityNameEn);
    }

    private ArrayList<String> splitString(String str) {
        ArrayList<String> result = new ArrayList(Arrays.asList(str.split(",")));
        return result;
    }
}
