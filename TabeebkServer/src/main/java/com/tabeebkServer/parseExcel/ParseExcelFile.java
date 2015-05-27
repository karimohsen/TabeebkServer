/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.parseExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author azza
 */
public class ParseExcelFile {

    private String ext = FilenameUtils.getExtension(Constants.FILE_PATH);
    private String fileName = FilenameUtils.getName(Constants.FILE_PATH);
    private FileInputStream excelFile = null;
    private Workbook workbook = null;
    private DoctorBook doctorSheetObj = null;
    private HospitalBook hospitalSheetObj = null;
    private PharmacyBook pharmacySheetObj = null;
    private LabBook labSheetObj = null;

    public ParseExcelFile() {
        try {
            this.excelFile = new FileInputStream(new File(Constants.FILE_PATH));
            // for XLSX Files
            if (ext.equalsIgnoreCase(Constants.XLSX)) { 
                workbook = new XSSFWorkbook(excelFile);
                doctorSheetObj = new DoctorBook(workbook);
                hospitalSheetObj = new HospitalBook(workbook);
                pharmacySheetObj = new PharmacyBook(workbook);
                labSheetObj = new LabBook(workbook);
            } // for XLS Files
            else if (ext.equalsIgnoreCase(Constants.XLS)) {
                workbook = new HSSFWorkbook(excelFile);
                doctorSheetObj = new DoctorBook(workbook);
                hospitalSheetObj = new HospitalBook(workbook);
                pharmacySheetObj = new PharmacyBook(workbook);
                labSheetObj = new LabBook(workbook);
            }
            excelFile.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParseExcelFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParseExcelFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new ParseExcelFile();
    }
}
