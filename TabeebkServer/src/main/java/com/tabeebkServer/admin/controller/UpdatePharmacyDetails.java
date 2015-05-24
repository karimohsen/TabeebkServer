/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.admin.controller;

import com.tabeebkServer.dao.BranchDao;
import com.tabeebkServer.dao.PharmacyDao;
import com.tabeebkServer.dao.TelephoneDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Karim
 */
public class UpdatePharmacyDetails extends HttpServlet {

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
        Integer teleId1 =Integer.parseInt(request.getParameter("teleid1"));
        Integer teleId2 =Integer.parseInt(request.getParameter("teleid2"));
        int pharmacyId = Integer.parseInt(request.getParameter("pharmacyid"));
        int branchId = Integer.parseInt(request.getParameter("branchid"));
        String name=request.getParameter("name");
        String nameAr=request.getParameter("namear");
        String branchName=request.getParameter("pharmacybranch");
        String branchNameAr=request.getParameter("pharmacybranchar");
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");
        String address=request.getParameter("pharmacyadd");
        String addressAr=request.getParameter("pharmacyaddar");
        String tele1 = request.getParameter("tele1");
        String tele2 = request.getParameter("tele2");
        int country = Integer.parseInt(request.getParameter("Country"));
        int area = Integer.parseInt(request.getParameter("Area"));
        int city = Integer.parseInt(request.getParameter("Cities"));
        Integer hospitalId = Integer.parseInt(request.getParameter("hospitals"));
        if(teleId1!=null){
            TelephoneDao.updateTelephone(teleId1, tele1);
        }
        if(teleId2!=null){
            TelephoneDao.updateTelephone(teleId2, tele2);
        }
        if(hospitalId == null){
            hospitalId = -2;
        }
        PharmacyDao.updatePharmacy(pharmacyId, hospitalId, name, nameAr);
        BranchDao.updateBranch(branchId, area, address, addressAr, latitude, longitude, branchName, branchNameAr, country, city);
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
