/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.admin.controller;

import com.tabeebkServer.parseExcel.Constants;
import com.tabeebkServer.parseExcel.ParseExcelFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
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
    private static final String DESTINATION_DIR_PATH ="/bulkUpload/uploadedexcelFile";
    private File destinationDir;
    public void init(ServletConfig config) throws ServletException {
 
        super.init(config);
       String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
        destinationDir = new File(realPath);
        if(!destinationDir.isDirectory()) {
            destinationDir.mkdirs();
           // throw new ServletException(DESTINATION_DIR_PATH+" is not a directory");
        }
 
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      PrintWriter out = response.getWriter();
        DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
      fileItemFactory.setRepository(destinationDir);
 
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);

 
        String fileName = null;
        String fullName = null;
        File file = null;
              try {
 
            //Parse the request
            List items = uploadHandler.parseRequest(request);
            Iterator iterator = items.iterator();
            while(iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
 
                //Handle Form Fields
                if(item.isFormField()) {
                    System.out.println("Field Name = " + item.getFieldName() + ", Value = " + item.getString());
                    if(item.getFieldName().trim().equalsIgnoreCase("filename")){
                        fileName = item.getString().trim();
                    }
                }
                //Handle Uploaded files.
                else {
                    System.out.println("Field Name = " + item.getFieldName()+
                            ", File Name = "+ item.getName()+
                            ", Content type = "+item.getContentType()+
                            ", File Size = "+item.getSize());
                    fullName = item.getName().trim();
 
                    //Write file to the ultimate location.
                    file = new File(destinationDir,item.getName());
                    String extension = FilenameUtils.getExtension(file.getName());
                    String name=FilenameUtils.getName(file.getName());
                    if((extension.trim().equalsIgnoreCase(Constants.XLSX)||extension.trim().equalsIgnoreCase(Constants.XLS))&& (name.equalsIgnoreCase(Constants.FILE_NAMEX)||name.equalsIgnoreCase(Constants.FILE_NAMEX))){
                    item.write(file);
                    request.setAttribute("message", "File Uploaded Successfully");}
                    else{
                    request.setAttribute("message", "Sorry server unable to upload your file, please select an excel file named MspData");
                    }
                }
 }
            String extension = FilenameUtils.getExtension(fullName);
            String name=FilenameUtils.getName(fullName);
                  System.out.println(name+"||||||||");
            if(extension.trim().equalsIgnoreCase(Constants.XLSX)&& name.equalsIgnoreCase(Constants.FILE_NAMEX)){
                validateExcelXFile(file);
                request.setAttribute("message1", "Data saved Successfully");
                
            }else if(extension.trim().equalsIgnoreCase(Constants.XLS) && name.equalsIgnoreCase(Constants.FILE_NAME)){
              validateExcelFile(file);
              request.setAttribute("message1", "Data saved Successfully");
              }
            request.getRequestDispatcher("/Admin/bulkUpload.jsp").forward(request, response);
              }
        catch(FileUploadException ex) {
            log("Error encountered while parsing the request",ex);
           
        } catch(Exception ex) {
            log("Error encountered while uploading file",ex);
            
        }
 
        out.close();
 
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
        processRequest(request, response);
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

    private int validateExcelXFile(File filePath) {
        int res = 0;
        System.out.println("in validation method ");
        try {
            //InputStream excelFile = getServletContext().getResourceAsStream(filePath);
            FileInputStream excelFile = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(excelFile);
                Sheet sheet1 = (XSSFSheet) workbook.getSheet(Constants.DOCTOR_SHEET);
                System.out.println(sheet1.getSheetName()+"-----------");
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
                    new ParseExcelFile(filePath);
                } else {
                    res = 0;
                }
                
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(uploadExcelFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(uploadExcelFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
     private int validateExcelFile(File filePath) {
        int res = 0;
        try {
            //InputStream excelFile = getServletContext().getResourceAsStream(filePath);
            FileInputStream excelFile = new FileInputStream(filePath);
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
                Sheet sheet10 = (HSSFSheet) workbook.getSheet(Constants.PHARMACY_SHEET);
                Sheet sheet11 = (HSSFSheet) workbook.getSheet(Constants.PHARMACY_TEL_SHEET);
     
         if (sheet1 != null && sheet2 != null && sheet3 != null && sheet4 != null && sheet5 != null && sheet6 != null && sheet7 != null && sheet8 != null && sheet9 != null && sheet10 != null && sheet11 != null) {
                    res = 1;
                    new ParseExcelFile(filePath);
                } else {
                    res = 0;
                }
                
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(uploadExcelFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(uploadExcelFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
     }
}
