/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.admin.controller;

import com.tabeebkServer.dao.DoctorClinicDao;
import com.tabeebkServer.dao.DoctorDao;
import com.tabeebkServer.dao.MSPDao;
import com.tabeebkServer.dao.TelephoneDao;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Karim
 */
public class AdminAddDoctor extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String tele1 = null;
        String tele2 = null;
        String name = "";
        String nameAr = "";
        String degree = "";
        String degreeAr = "";
        int drSpeciality = -2;
        int gender = -2;
        int doctorId;
        String path="";
        ArrayList<Integer> listClinics = new ArrayList<>();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {

// Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

// Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            factory.setSizeThreshold(1024 * 1024 * 3);
            // sets temporary location to store files
            ServletFileUpload upload = new ServletFileUpload(factory);
            // sets maximum size of upload file
            upload.setFileSizeMax(1024 * 1024 * 40);
            // sets maximum size of request (include file + form data)
            upload.setSizeMax(1024 * 1024 * 50);
            // Parse the request
            List<FileItem> items;
            try {
                items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (item.isFormField()) {
                        if (item.getFieldName().equals("name")) {
                            name = item.getString();
                        } else if (item.getFieldName().equals("namear")) {
                            nameAr = item.getString("UTF-8").trim();
                        } else if (item.getFieldName().equals("doctordegree")) {
                            degree = item.getString();
                        } else if (item.getFieldName().equals("doctordegreear")) {
                            degreeAr = item.getString("UTF-8").trim();
                        } else if (item.getFieldName().equals("specialities")) {
                            drSpeciality = Integer.parseInt(item.getString());
                        } else if (item.getFieldName().equals("gender")) {
                            gender = Integer.parseInt(item.getString());
                        } else if (item.getFieldName().equals("clinics")) {
                            listClinics.add(Integer.parseInt(item.getString()));
                        } else if (item.getFieldName().equals("tele1")) {
                            tele1 = item.getString();
                        } else if (item.getFieldName().equals("tele2")) {
                            tele2 = item.getString();;
                        }
                    } else {
                        String Name = item.getName();
                        if (!Name.equals("") && Name != null) {
                            String uploadFolder = System.getProperty("user.home");
                            String fName = new File(item.getName()).getName();
                            File file = new File(uploadFolder + "\\doctor");
                            if (!file.exists()) {
                                file.mkdir();
                            }
                            uploadFolder += "\\doctor";
                            String filePath = uploadFolder + File.separator + fName;
                            //mic.setMicImageurl(filePath);
                            path=filePath;
                            File f = new File(filePath);
                            item.write(f);
                        }
                    }
                }
                doctorId = DoctorDao.addDoctor(name, nameAr, degree, degreeAr, drSpeciality, path,gender);
                if (tele1 != null) {
                    TelephoneDao.createTelephoneNumber(3, doctorId, tele1);
                }
                if (tele2 != null) {
                    TelephoneDao.createTelephoneNumber(3, doctorId, tele2);
                }
                MSPDao.addMsp(3, doctorId);
                DoctorClinicDao.addDoctorClinic(doctorId, listClinics);
                response.sendRedirect("Admin/Home.jsp");
            } catch (FileUploadException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

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

}
