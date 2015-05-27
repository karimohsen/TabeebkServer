/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.admin.controller;

import com.tabeebkServer.parseExcel.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author azza
 */
public class uploadExcelFile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //process only if its multipart content
        int res = 0;
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        String ext = FilenameUtils.getExtension(name);
                        if ((ext.equals(Constants.XLSX) || ext.equals(Constants.XLS)) && (name.equals(Constants.FILE_NAME) || name.equals(Constants.FILE_NAMEX))) {
                            item.write(new File(Constants.UPLOAD_DIRECTORY + File.separator + name));
                           int x= validateExcelFile(Constants.FILE_PATH);
                           if(x==1) 
                           res = 1;
                           
                        } else {
                            res = 0;
                        }
                    }
                }

                //File uploaded successfully
                if (res == 1) {
                    request.setAttribute("message", "File Uploaded Successfully");
                } else {
                    request.setAttribute("message", "Please choose a correct file, it should be an excel file named MspData");
                }
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }

        } else {
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }

        request.getRequestDispatcher("/Admin/pulkUpload.jsp").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private int validateExcelFile(String filePath) {
        int res=0;
        try {
            FileInputStream excelFile = new FileInputStream(new File(Constants.FILE_PATH));
            String ext = FilenameUtils.getExtension(Constants.FILE_PATH);

            if (ext.equals(Constants.XLSX)) {
                Workbook workbook = new XSSFWorkbook(excelFile);
                Sheet sheet1 = (XSSFSheet) workbook.getSheet(Constants.DOCTOR_SHEET);
                Sheet sheet2 = (XSSFSheet) workbook.getSheet(Constants.DOCTOR_TEL_SHEET);
                Sheet sheet3 = (XSSFSheet) workbook.getSheet(Constants.CLINIC_SHEET);
                Sheet sheet4 = (XSSFSheet) workbook.getSheet(Constants.CLINIC_TEL_SHEET);
                Sheet sheet5 = (XSSFSheet) workbook.getSheet(Constants.CLINIC_ADRESS_SHEET);
                Sheet sheet6 = (XSSFSheet) workbook.getSheet(Constants.HOSPITAL_SHEET);
                Sheet sheet7 = (XSSFSheet) workbook.getSheet(Constants.HOSPITAL_SPECIALITY_SHEET);
                Sheet sheet8 = (XSSFSheet) workbook.getSheet(Constants.HOSPITAL_ADDRESS_SHEET);
                Sheet sheet9 = (XSSFSheet) workbook.getSheet(Constants.HOSPITAL_TEL_SHEET);
                Sheet sheet10 = (XSSFSheet) workbook.getSheet(Constants.PHARMACY_SHEET);
                Sheet sheet11 = (XSSFSheet) workbook.getSheet(Constants.PHARMACY_TEL_SHEET);
                if (sheet1 != null && sheet2 != null && sheet3 != null && sheet4 != null && sheet5 != null && sheet6 != null && sheet7 != null && sheet8 != null && sheet9 != null && sheet10 != null && sheet11 != null) {
                res=1;
                }
                else
                {res=0;}
            } else if (ext.equals(Constants.XLS)) {
                Workbook workbook = new HSSFWorkbook(excelFile);
                Sheet sheet1 = (HSSFSheet) workbook.getSheet(Constants.DOCTOR_SHEET);
                 Sheet sheet2 = (HSSFSheet) workbook.getSheet(Constants.DOCTOR_TEL_SHEET);
                Sheet sheet3 = (HSSFSheet) workbook.getSheet(Constants.CLINIC_SHEET);
                Sheet sheet4 = (HSSFSheet) workbook.getSheet(Constants.CLINIC_TEL_SHEET);
                Sheet sheet5 = (HSSFSheet) workbook.getSheet(Constants.CLINIC_ADRESS_SHEET);
                Sheet sheet6 = (HSSFSheet) workbook.getSheet(Constants.HOSPITAL_SHEET);
                Sheet sheet7 = (HSSFSheet) workbook.getSheet(Constants.HOSPITAL_SPECIALITY_SHEET);
                Sheet sheet8 = (HSSFSheet) workbook.getSheet(Constants.HOSPITAL_ADDRESS_SHEET);
                Sheet sheet9 = (HSSFSheet) workbook.getSheet(Constants.HOSPITAL_TEL_SHEET);
                Sheet sheet10 =(HSSFSheet) workbook.getSheet(Constants.PHARMACY_SHEET);
                Sheet sheet11 = (HSSFSheet) workbook.getSheet(Constants.PHARMACY_TEL_SHEET);
                if (sheet1 != null && sheet2 != null && sheet3 != null && sheet4 != null && sheet5 != null && sheet6 != null && sheet7 != null && sheet8 != null && sheet9 != null && sheet10 != null && sheet11 != null) {
                res=1;
                }
                else
                {res=0;}
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(uploadExcelFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(uploadExcelFile.class.getName()).log(Level.SEVERE, null, ex);
        }
return res;
    }
}
