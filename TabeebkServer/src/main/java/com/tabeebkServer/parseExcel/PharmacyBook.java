/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.parseExcel;
//Azza
import com.tabeebkServer.dao.BranchDao;
import com.tabeebkServer.dao.MSPDao;
import com.tabeebkServer.dao.TelephoneDao;
import com.tabeebkServer.pojo.*;
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
public class PharmacyBook {

    private Pharamacy pharmacy = null;
    private Telephone pharmacyTelephone = null;
    private MSPDao mspDao = null;
    private Msptype mspType = null;
    private City city = null;
    private BranchDao addressDao = null;
    private Sheet pharmcySheet = null;
    private Msp msp=null;

    public PharmacyBook(Workbook pharmacyBook) {

        mspDao = new MSPDao();
        mspType = mspDao.getMspType(Constants.PHARMACY);
        addressDao = new BranchDao();
        if (pharmacyBook instanceof XSSFWorkbook) {
            pharmcySheet = (XSSFSheet) pharmacyBook.getSheet(Constants.PHARMACY_SHEET);
        } else if (pharmacyBook instanceof HSSFWorkbook) {
            pharmcySheet = (HSSFSheet) pharmacyBook.getSheet(Constants.PHARMACY_SHEET);
        }

        Iterator<Row> rowIterator = pharmcySheet.iterator();
        Row row = null;
        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            createPharmacy(row, pharmacyBook);
        }
        System.out.println("pharmacies Data inserted successfully");
    }

    private void createPharmacy(Row row, Workbook pharmacySheet) {
        if (row.getCell(0) != null) {
            String pharmacyNameEn = row.getCell(0).getStringCellValue().toLowerCase();
            String pharmacyNameAr = row.getCell(1).getStringCellValue();
            String pharmacyHospital = row.getCell(3).getStringCellValue();
            if (pharmacyNameEn != null && pharmacyNameAr != null &&  pharmacyHospital != null) {
                pharmacy = new Pharamacy(mspDao.getHospitalByName(pharmacyHospital.toLowerCase()), pharmacyNameEn, pharmacyNameAr,0);
            } else if (pharmacyHospital == null) {
                pharmacy = new Pharamacy(pharmacyNameEn, pharmacyNameAr,0);
            }
            int i = savePharmacy(pharmacy);
            if (i == 1) {
                msp=new Msp(mspDao.getMspType(Constants.PHARMACY),pharmacy.getPharamacyId(),0);
                mspDao.saveMsp(msp);
                savePharmacyTelephone(createPharmacyTelelphone(row, pharmacy, pharmacySheet));
                savePharmacyBranches(createPharmacyBranch(row, pharmacy, pharmacySheet));
            }
        }
    }

    private TreeSet<Telephone> createPharmacyTelelphone(Row pharmacyRow, Pharamacy ph, Workbook ds) {
        TreeSet<Telephone> telphons = new TreeSet<Telephone>();
        Sheet pharmacyTel = null;
        if (ds instanceof XSSFWorkbook) {
            pharmacyTel = (XSSFSheet) ds.getSheet(Constants.PHARMACY_TEL_SHEET);
        } else {
            pharmacyTel = (HSSFSheet) ds.getSheet(Constants.PHARMACY_TEL_SHEET);
        }
        Iterator<Row> rowIterator = pharmacyTel.iterator();
        Row pharmacyTelRow = null;
        while (rowIterator.hasNext()) {
            pharmacyTelRow = rowIterator.next();
            if (pharmacyTelRow.getRowNum() == 0) {
                continue;
            }
            String pharmName=pharmacyTelRow.getCell(0).getStringCellValue();
            //double pharmacyRowNum = pharmacyTelRow.getCell(0).getNumericCellValue();
            String telephs = pharmacyTelRow.getCell(1).getStringCellValue();
            if(ph.getPharamacyName().trim().equals(pharmName.trim().toLowerCase())){
            //if (pharmacyRow.getRowNum() + 1 == pharmacyRowNum) {
                pharmacyTelephone = new Telephone(mspType, ph.getPharamacyId(), telephs);
                telphons.add(pharmacyTelephone);
            }
        }
        return telphons;
    }

    private TreeSet<Branche> createPharmacyBranch(Row pharmcyRow, Pharamacy ph, Workbook wb) {
        Sheet phAdress = null;
        if (wb instanceof XSSFWorkbook) {
            phAdress = (XSSFSheet) wb.getSheet(Constants.PHARMACY_ADDRESS_SHEET);
        } else {
            phAdress = (HSSFSheet) wb.getSheet(Constants.PHARMACY_ADDRESS_SHEET);
        }
        TreeSet<Branche> branchs = new TreeSet<Branche>();
        Iterator<Row> rowIterator = phAdress.iterator();
        Row phAddRow = null;
        Area area = null;
        Branche b = null;
        while (rowIterator.hasNext()) {
            phAddRow = rowIterator.next();
            if (phAddRow.getRowNum() == 0) {
                continue;
            }
            if (phAddRow.getCell(0) != null) {
                String phamrName=phAddRow.getCell(0).getStringCellValue();
                //double phRowNum = phAddRow.getCell(0).getNumericCellValue();
                if(ph.getPharamacyName().trim().equals(phamrName.trim().toLowerCase())){
                //if (pharmcyRow.getRowNum() + 1 == phRowNum) {
                    String cityNameEn = phAddRow.getCell(1).getStringCellValue();
                    city = addressDao.getCity(cityNameEn);
                   
                    String areaNamesEn = phAddRow.getCell(2).getStringCellValue();
                    String areaNamesAr = phAddRow.getCell(3).getStringCellValue();
                    String fullAddressEn = phAddRow.getCell(4).getStringCellValue();
                    String fullAddressAr = phAddRow.getCell(5).getStringCellValue();
                    area = new Area(city, areaNamesEn, areaNamesAr);
                    area=addressDao.insertArea(area);
                    b = new Branche(city,area ,mspType, areaNamesEn, areaNamesAr, fullAddressEn, fullAddressAr, ph.getPharamacyId());
                    branchs.add(b);
                }
            }
        }
        return branchs;
    }

    private int savePharmacy(Pharamacy ph) {
        return mspDao.insertPharmacy(ph);
    }

    private int savePharmacyTelephone(TreeSet<Telephone> docTelephons) {
        TelephoneDao telephoneDao = new TelephoneDao();
        return telephoneDao.insertTelephones(docTelephons);
    }

    private int savePharmacyBranches(TreeSet<Branche> branches) {
        return addressDao.insertBranches(branches);
    }
}
