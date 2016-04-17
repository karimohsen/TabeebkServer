/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.admin.controller;

import com.tabeebkServer.dao.DoctorClinicDao;
import com.tabeebkServer.dao.DoctorDao;
import com.tabeebkServer.dao.TelephoneDao;
import com.tabeebkServer.pojo.Doctor;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Karim
 */
public class UpdateDoctorDetails extends HttpServlet {

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
        Integer teleId1=null;
        Integer teleId2=null;
        if( request.getParameter("teleid1")!= null && !request.getParameter("teleid1").equals(""))
        teleId1 =Integer.parseInt(request.getParameter("teleid1"));
        if( request.getParameter("teleid2")!= null && !request.getParameter("teleid2").equals(""))
         teleId2 =Integer.parseInt(request.getParameter("teleid2"));
        int drId=Integer.parseInt(request.getParameter("doctorid"));
        String name=request.getParameter("name");
        String nameAr=request.getParameter("namear");
        String doctorDegree=request.getParameter("doctordegree");
        String doctorDegreeAr=request.getParameter("doctordegreear");
        String tele1 = request.getParameter("tele1");
        String tele2 = request.getParameter("tele2");
        int drSpeciality=Integer.parseInt(request.getParameter("specialities"));
        int genderId = Integer.parseInt(request.getParameter("gender"));
        String [] arr = request.getParameterValues("clinics");
        ArrayList<Integer> clinicArr = new ArrayList<>();
        for(int i = 0 ; i < arr.length; i++){
            clinicArr.add(Integer.parseInt(arr[i]));
        }
        DoctorDao.updateDoctor(drId, name, nameAr, doctorDegree, doctorDegreeAr, drSpeciality, genderId);
        if(teleId1!=null){
            TelephoneDao.updateTelephone(teleId1, tele1);
        }
        if(teleId2!=null){
            TelephoneDao.updateTelephone(teleId2, tele2);
        }
        DoctorClinicDao.deleteDoctorClinics(drId);
        for(int i = 0 ; i < clinicArr.size() ; i++){
            DoctorClinicDao.updateDoctorClinic(drId, clinicArr.get(i));
        }
        response.sendRedirect("Admin/Home.jsp");
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
