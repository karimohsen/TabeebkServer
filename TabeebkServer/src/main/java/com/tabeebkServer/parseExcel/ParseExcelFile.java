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

    
    private String fileName = FilenameUtils.getName(Constants.FILE_PATH);
    private FileInputStream excelFile = null;
    private Workbook workbook = null;
    private DoctorBook doctorSheetObj = null;
    private HospitalBook hospitalSheetObj = null;
    private PharmacyBook pharmacySheetObj = null;
    private LabBook labSheetObj = null;

    public ParseExcelFile(File file) {
        try {
            System.out.println("in parsing classes");
            this.excelFile = new FileInputStream(file);
            String ext = FilenameUtils.getExtension(file.getName());
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
        } catch(Exception e){
        e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//     File f=new File("C:\\Users\\azza\\Documents\\NetBeansProjects\\GP\\Server\\TabeebkServer\\src\\main\\java\\com\\tabeebkServer\\parseExcel\\MspData.xlsx");
//        new ParseExcelFile(f);
    }
}
