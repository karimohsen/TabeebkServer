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
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author azza
 */
public class HospitalBook {

    private Hospital hospital = null;
    private Doctorspeciality specialityName = null;
    private Telephone hospitalTelephone = null;
    private MSPDao mspDao = null;
    private Msptype mspType = null;
    private City city = null;
    private BranchDao addressDao = null;
    private Sheet hosSheet=null;
    
    public HospitalBook(Workbook hospitalSheet) {

        mspDao = new MSPDao();
        mspType = mspDao.getMspType(Constants.HOSPITAL);
        addressDao = new BranchDao();
        if (hospitalSheet instanceof XSSFWorkbook) {
            hosSheet = (XSSFSheet) hospitalSheet.getSheet(Constants.HOSPITAL_SHEET);
        } else if (hospitalSheet instanceof HSSFWorkbook) {
             hosSheet = (HSSFSheet) hospitalSheet.getSheet(Constants.HOSPITAL_SHEET);
        }
        Iterator<Row> rowIterator = hosSheet.iterator();
        Row row = null;
        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            createHospital(row, hospitalSheet);
        }
        System.out.println("Hospital Data inserted successfully");
    }

    private void createHospital(Row row, Workbook wb) {
        if (row.getCell(0) != null) {
            String hospitalNameEn = row.getCell(0).getStringCellValue().toLowerCase();
            String hospitalNameAr = row.getCell(1).getStringCellValue();
            String hospitalImagePath = Constants.HOSPITAL_IMAGE_DIRECTORY+new Date().toString()+"-/-"+row.getCell(2).getStringCellValue();
            hospital = new Hospital(hospitalNameEn.toLowerCase(), hospitalNameAr, hospitalImagePath,0);
           
             int i = mspDao.insertHospital(hospital);
                if (i == 1) {
                TreeSet<Telephone> telephones = createHospitalTelelphone(row, hospital, wb);
                saveHospitalTelephone(telephones);
                TreeSet<Branche> brnch = createHospitalBranch(row, hospital, wb);
                saveHospitalBranches(brnch);
               createHospitalSpeciality(row, hospital, wb);
            }
        }
    }

    private void createHospitalSpeciality(Row hosRow, Hospital h, Workbook ds) {
        Sheet hospitalspecialitySheet=null;
        if(ds instanceof XSSFWorkbook){
        hospitalspecialitySheet = (XSSFSheet) ds.getSheet(Constants.HOSPITAL_SPECIALITY_SHEET);
        }else 
        {
        hospitalspecialitySheet=(HSSFSheet)ds.getSheet(Constants.HOSPITAL_SPECIALITY_SHEET);
        }
        Iterator<Row> rowIterator = hospitalspecialitySheet.iterator();
        Row hosSpecRow = null;
        while (rowIterator.hasNext()) {
            hosSpecRow = rowIterator.next();
            if (hosSpecRow.getRowNum() == 0) {
                continue;
            }
            String hospitalName=hosSpecRow.getCell(0).getStringCellValue();
//double hospitalRowNum = hosSpecRow.getCell(0).getNumericCellValue();
            String hospitalSepeciality = hosSpecRow.getCell(1).getStringCellValue();
if(hospital.getHospitalName().trim().equals(hospitalName.trim().toLowerCase())){            
//if (hosRow.getRowNum() + 1 == hospitalRowNum) {
                specialityName = mspDao.getDoctorSpeciality(hospitalSepeciality.toLowerCase());
                Hospitalspeciality hospitalSpeciality = new Hospitalspeciality(specialityName, hospital,0);
                mspDao.saveHospitalSpeciality(hospitalSpeciality);
            }
        }
    }

    private TreeSet<Telephone> createHospitalTelelphone(Row hosRow, Hospital h, Workbook ds) {
        TreeSet<Telephone> telphons = new TreeSet<Telephone>();
        Sheet hospitalTel=null;
        if(ds instanceof XSSFWorkbook){
        hospitalTel = (XSSFSheet) ds.getSheet(Constants.HOSPITAL_TEL_SHEET);
        }else {
        hospitalTel=(HSSFSheet) ds.getSheet(Constants.HOSPITAL_TEL_SHEET);
        }
        Iterator<Row> rowIterator = hospitalTel.iterator();
        Row hospitalTelRow = null;
        while (rowIterator.hasNext()) {
            hospitalTelRow = rowIterator.next();
            if (hospitalTelRow.getRowNum() == 0) {
                continue;
            }
            String hospitalName=hospitalTelRow.getCell(0).getStringCellValue();
            
            //double hospitalRowNum = hospitalTelRow.getCell(0).getNumericCellValue();
            String telephs = hospitalTelRow.getCell(1).getStringCellValue();
            
            if(h.getHospitalName().trim().equals(hospitalName.trim().toLowerCase())){
            //if (hosRow.getRowNum() + 1 == hospitalRowNum) {
                hospitalTelephone = new Telephone(mspType, h.getHospitalId(), telephs);
                telphons.add(hospitalTelephone);
            }
        }
        return telphons;
    }

    private TreeSet<Branche> createHospitalBranch(Row hospitalRow, Hospital h, Workbook wb) {
        Sheet hosAdress=null;
        if(wb instanceof XSSFWorkbook){
        hosAdress = (XSSFSheet) wb.getSheet(Constants.HOSPITAL_ADDRESS_SHEET);
        }else {
        hosAdress=(HSSFSheet)wb.getSheet(Constants.HOSPITAL_ADDRESS_SHEET);
        }
        TreeSet<Branche> branchs = new TreeSet<Branche>();
        Iterator<Row> rowIterator = hosAdress.iterator();
        Row hosAddRow = null;
        Area area = null;
        Branche b = null;
        while (rowIterator.hasNext()) {
            hosAddRow = rowIterator.next();
            if (hosAddRow.getRowNum() == 0) {
                continue;
            }
             if (hosAddRow.getCell(0) != null) {
            String hospitalName=hosAddRow.getCell(0).getStringCellValue();
            //double hospitalRowNum = hosAddRow.getCell(0).getNumericCellValue();            
            if(h.getHospitalName().trim().equals(hospitalName.trim().toLowerCase())){
            //if (hospitalRow.getRowNum() + 1 == hospitalRowNum) {
                String cityNameEn = hosAddRow.getCell(1).getStringCellValue();
                city = addressDao.getCity(cityNameEn);
                String areaNamesEn = hosAddRow.getCell(2).getStringCellValue();
                String areaNamesAr = hosAddRow.getCell(3).getStringCellValue();
                String fullAddressEn = hosAddRow.getCell(4).getStringCellValue();
                String fullAddressAr = hosAddRow.getCell(5).getStringCellValue();
                area = new Area(city, areaNamesEn, areaNamesAr);
               area= addressDao.insertArea(area);
                b = new Branche(city,area ,mspType, areaNamesEn, areaNamesAr, fullAddressEn, fullAddressAr, h.getHospitalId());
                branchs.add(b);
            }
        }
        }
        return branchs;
    }

    private int saveHospitalTelephone(TreeSet<Telephone> docTelephons) {
        TelephoneDao telephoneDao = new TelephoneDao();
        return telephoneDao.insertTelephones(docTelephons);
    }

    private int saveHospitalBranches(TreeSet<Branche> branches) {
        return addressDao.insertBranches(branches);
    }
}
