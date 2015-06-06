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
public class LabBook {

    private Lab lab = null;
    private Labspecialities specialityName = null;
    private Telephone labTelephone = null;
    private MSPDao mspDao = null;
    private Msptype mspType = null;
    private City city = null;
    private BranchDao addressDao = null;
    private Sheet labSheet = null;
    private Msp msp=null;

    public LabBook(Workbook labBook) {

        mspDao = new MSPDao();
        mspType = mspDao.getMspType(Constants.LAB);
        addressDao = new BranchDao();
        if (labBook instanceof XSSFWorkbook) {
            labSheet = (XSSFSheet) labBook.getSheet(Constants.LAB_SHEET);
        } else if (labBook instanceof HSSFWorkbook) {
            labSheet = (HSSFSheet) labBook.getSheet(Constants.LAB_SHEET);
        }
        Iterator<Row> rowIterator = labSheet.iterator();
        Row row = null;
        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            createlab(row, labBook);
        }
        System.out.println("lab Data inserted successfully");
    }

    private void createlab(Row row, Workbook labSheet) {

        if (row.getCell(0) != null) {
            String labNameEn = row.getCell(0).getStringCellValue().toLowerCase();
            String labNameAr = row.getCell(1).getStringCellValue();
            String labHospital = row.getCell(3).getStringCellValue();
            if (labNameEn != null && labNameAr != null  && labHospital != null) {
                lab = new Lab(mspDao.getHospitalByName(labHospital.toLowerCase()), labNameEn, labNameAr,0);
            } else if (labHospital == null) {
                lab = new Lab(labNameEn, labNameAr,0);
            }

            int i = saveLab(lab);
            if (i == 1) {
                msp=new Msp(mspDao.getMspType(Constants.LAB),lab.getLabId(),0);
                mspDao.saveMsp(msp);
                savelabTelephone(createlabTelelphone(row, lab, labSheet));
                savelabBranches(createlabBranch(row, lab, labSheet));
                createLabSpeciality(row, lab, labSheet);
            }
        }
    }

    private void createLabSpeciality(Row labRow, Lab l, Workbook ds) {

        Sheet labSpecialitySheet = null;
        if (ds instanceof XSSFWorkbook) {
            labSpecialitySheet = (XSSFSheet) ds.getSheet(Constants.LAB_SPECIALITY_SHEET);
        } else {
            labSpecialitySheet = (HSSFSheet) ds.getSheet(Constants.LAB_SPECIALITY_SHEET);
        }
        Iterator<Row> rowIterator = labSpecialitySheet.iterator();
        Row labSpecRow = null;
        while (rowIterator.hasNext()) {
            labSpecRow = rowIterator.next();
            if (labSpecRow.getRowNum() == 0) {
                continue;
            }
            String labName=labSpecRow.getCell(0).getStringCellValue();
            //double labRowNum = labSpecRow.getCell(0).getNumericCellValue();
            
            String labSepeciality = labSpecRow.getCell(1).getStringCellValue();
            if(l.getLabName().trim().equals(labName.trim().toLowerCase())){
            //if (labRow.getRowNum() + 1 == labRowNum) {
                specialityName = mspDao.getLabSpeciality(labSepeciality.toLowerCase());
                Labspeciality labSpeciality = new Labspeciality(l, specialityName,0);
                mspDao.saveLabSpeciality(labSpeciality);
            }
        }

    }

    private TreeSet<Telephone> createlabTelelphone(Row labRow, Lab l, Workbook ds) {
        TreeSet<Telephone> telphons = new TreeSet<Telephone>();
        Sheet labTel = null;
        if (ds instanceof XSSFWorkbook) {
            labTel = (XSSFSheet) ds.getSheet(Constants.LAB_TEL);
        } else {
            labTel = (HSSFSheet) ds.getSheet(Constants.LAB_TEL);
        }
        Iterator<Row> rowIterator = labTel.iterator();
        Row labTelRow = null;
        while (rowIterator.hasNext()) {
            labTelRow = rowIterator.next();
            if (labTelRow.getRowNum() == 0) {
                continue;
            }
            String labName=labTelRow.getCell(0).getStringCellValue();
            //double labRowNum = labTelRow.getCell(0).getNumericCellValue();
            String telephs = labTelRow.getCell(1).getStringCellValue();
            if(l.getLabName().trim().equals(labName.trim().toLowerCase())){
            //if (labRow.getRowNum() + 1 == labRowNum) {
                labTelephone = new Telephone(mspType, l.getLabId(), telephs);
                telphons.add(labTelephone);
            }
        }
        return telphons;
    }

    private TreeSet<Branche> createlabBranch(Row labRow, Lab l, Workbook wb) {
        Sheet labAdress = null;
        if (wb instanceof XSSFWorkbook) {
            labAdress = (XSSFSheet) wb.getSheet(Constants.LAB_ADDRESS);
        } else {
            labAdress = (HSSFSheet) wb.getSheet(Constants.LAB_ADDRESS);
        }
        TreeSet<Branche> branchs = new TreeSet<Branche>();
        Iterator<Row> rowIterator = labAdress.iterator();
        Row labAddRow = null;
        Area area = null;
        Branche b = null;
        while (rowIterator.hasNext()) {
            labAddRow = rowIterator.next();
            if (labAddRow.getRowNum() == 0) {
                continue;
            }
            if (labAddRow.getCell(0) != null) {
                String labName=labAddRow.getCell(0).getStringCellValue();
            
                //double labRowNum = labAddRow.getCell(0).getNumericCellValue();
                if(l.getLabName().trim().equals(labName.trim().toLowerCase())){
                //if (labRow.getRowNum() + 1 == labRowNum) {
                    String cityNameEn = labAddRow.getCell(1).getStringCellValue();
                    city = addressDao.getCity(cityNameEn);
                    String areaNamesEn = labAddRow.getCell(2).getStringCellValue();
                    String areaNamesAr = labAddRow.getCell(3).getStringCellValue();
                    String fullAddressEn = labAddRow.getCell(4).getStringCellValue();
                    String fullAddressAr = labAddRow.getCell(5).getStringCellValue();
                    area = new Area(city, areaNamesEn, areaNamesAr);
                   area= addressDao.insertArea(area);
                    b = new Branche(city,area ,mspType, areaNamesEn, areaNamesAr, fullAddressEn, fullAddressAr, l.getLabId());
                    branchs.add(b);
                }
            }
        }
        return branchs;
    }

    private int savelabTelephone(TreeSet<Telephone> docTelephons) {
        TelephoneDao telephoneDao = new TelephoneDao();
        return telephoneDao.insertTelephones(docTelephons);
    }

    private int savelabBranches(TreeSet<Branche> branches) {
        return addressDao.insertBranches(branches);
    }

    private int saveLab(Lab lab) {
        return mspDao.insertLab(lab);
    }
}
