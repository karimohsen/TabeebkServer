/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.controller.plan;

import com.tabeebkServer.dao.plan.PlanDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tabeebkServer.pojo.Plan;

/**
 *
 * @author Karim
 */
public class UpdatePlan extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * Plan currentPlan=dao.getPlanDetails(id);
        currentPlan.setPlanDescription(null);
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("planid"));
        String planname=request.getParameter("planname");
        String plannamear=request.getParameter("plannamear");
        String plandesc=request.getParameter("plandesc");
        String plandescar=request.getParameter("plandescar");
        int version=Integer.parseInt(request.getParameter("version"));
        
//        Plan currentPlan=new Plan(id,planname, plannamear, plandesc, plandescar, version);
        PlanDao planDao=new PlanDao();
        Plan currentPlan = planDao.getPlanDetails(id);
        currentPlan.setPlanName(planname);
        currentPlan.setPlanNameAr(plannamear);
        currentPlan.setPlanDescription(plandesc);
        currentPlan.setPlanDescriptionAr(plandescar);
        currentPlan.setVersion(version);
        planDao.updatePlan(currentPlan);
        response.sendRedirect("AllPlans");
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
