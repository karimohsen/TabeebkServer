/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.admin.controller;

import com.tabeebkServer.dao.BranchDao;
import com.tabeebkServer.dao.LabDao;
import com.tabeebkServer.pojo.Lab;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Karim
 */
public class AddLabBranch extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String branchName = request.getParameter("name");
        String branchNameAr = request.getParameter("namear");
        String address = request.getParameter("address");
        String addressAr = request.getParameter("addressar");
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");
        int country = Integer.parseInt(request.getParameter("Country"));
        int area = Integer.parseInt(request.getParameter("Area"));
        int city = Integer.parseInt(request.getParameter("Cities"));
        int hospitalId = -2;
        if (request.getParameter("hospitals") != null) {
            hospitalId = Integer.parseInt(request.getParameter("hospitals"));
        }
        String[] specialities = request.getParameterValues("specialities");
        ArrayList<Integer> allSpecialities = new ArrayList<>();
        Lab l = (Lab) this.getServletConfig().getServletContext().getAttribute("newlab");
        int labId = LabDao.addLab(l);
        System.out.println("hospital => " + hospitalId);
        for (int i = 0; i < specialities.length; i++) {
            allSpecialities.add(Integer.parseInt(specialities[i]));
        }
        LabDao.addLabToSpeciality(l, allSpecialities, hospitalId);
        BranchDao.addBranch(area, address, addressAr, latitude, longitude, branchName, branchNameAr, 4, labId, country, city);
        RequestDispatcher rd = request.getRequestDispatcher("AllLabHospitalsAndSpecialities");
        rd.forward(request, response);
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
