/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.Doctor;

import com.tabeebkServer.dao.ScheduleDao;
import com.tabeebkServer.pojo.Schedule;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Karim
 */
public class DoctorAddSchedule extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        if (session.getAttribute("Accountid") != null) {
            try {
                Date date = new SimpleDateFormat("mm-dd-yyyy").parse(request.getParameter("date"));
                String strt = request.getParameter("start");
                int hours = Integer.parseInt(strt.substring(0, 2));
                int mint = Integer.parseInt(strt.substring(3, 5));
                Date start = new Date();
                start.setHours(hours);
                start.setMinutes(mint);
                String ed = request.getParameter("end");
                hours = Integer.parseInt(ed.substring(0, 2));
                mint = Integer.parseInt(ed.substring(3, 5));
                Date end = new Date();
                start.setHours(hours);
                start.setMinutes(mint);
                int serveTime = Integer.parseInt(request.getParameter("servetime"));
                double fee = Double.parseDouble(request.getParameter("fees"));
                Schedule schedule= new Schedule();
                schedule.setDate(date);
                schedule.setFees(fee);
                schedule.setEndTime(end);
                schedule.setServeTime(serveTime);
                schedule.setStartTime(start);
                ScheduleDao.createSchedule(schedule);
                response.sendRedirect("Doctor/DoctorSchedule.jsp");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

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
