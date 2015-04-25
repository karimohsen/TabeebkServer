/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.controller.plan;

import com.tabeebkServer.dao.mic.MicDao;
import com.tabeebkServer.dao.plan.PlanDao;
import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tabeebkServer.pojo.Mic;
import com.tabeebkServer.pojo.Plan;

/**
 *
 * @author Karim
 */
public class AddPlan extends HttpServlet {

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
        String planeName = request.getParameter("planname");
        String planeNameAr = request.getParameter("plannamear");
        String planDesc = request.getParameter("plandesc");
        String planDescAr = request.getParameter("plandescar");
        System.out.println("=>Plane name  = " + planeNameAr + " Description = " + planDescAr);
        //GET from session
        HttpSession session = request.getSession(false);
        if (session.getAttribute("Accountid") != null) {
            int micId = (Integer) session.getAttribute("Accountid");
            Plan plan = new Plan();
            plan.setPlanDescription(planDesc);
            plan.setPlanDescriptionAr(planDescAr);
            plan.setPlanInsertDate(new Date());
            plan.setPlanName(planeName);
            plan.setPlanNameAr(planeNameAr);
            plan.setPlanUpdatedDate(new Date());
            plan.setVersion(1);
            plan.setDeleted(0);
            PlanDao dao = new PlanDao();
            MicDao micDo = new MicDao();
            Mic mic = micDo.getMicDetails(micId);
            plan.setMic(mic);
            dao.addPlan(plan);
            RequestDispatcher rd = request.getRequestDispatcher("/MSP/AddPlan.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("Login.jsp");
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
