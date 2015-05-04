/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.controller.account;

import com.tabeebkServer.dao.AccountDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tabeebkServer.pojo.Account;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author HMA
 */
public class LoginControler extends HttpServlet {

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
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setDateHeader("Expires", -1); // Proxies.
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Account currentUser = new Account();
        currentUser.setUsername(username);
        currentUser.setPassword(password);


        //================= Check Account Data =====================       
        currentUser = new AccountDao().checkLogin(currentUser);
        //================= Start =====================       

        //Scucessful login
        if (currentUser != null) {
             HttpSession session = request.getSession(true);
            //if user is admin user
            session.setAttribute("Accountid", currentUser.getAccountId());
            session.setAttribute("account", currentUser);
            session.setAttribute("type", currentUser.getAccounttype().getAccountTypeId());
            session.setAttribute("authenticated", "true");

            //if Account is MSP Account
            if (currentUser.getAccounttype().getAccountTypeId() == 1) {
                response.sendRedirect("MSP/Home.jsp");
            }
            //if Account is MIC Account
            if (currentUser.getAccounttype().getAccountTypeId() == 2) {
                response.sendRedirect("MSP/Home.jsp");
            }
             //if Account is MIC Account
            if (currentUser.getAccounttype().getAccountTypeId() == 7) {
                response.sendRedirect("Admin/Home.jsp");
            }
        } //Failed login
        else {
            RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
            request.setAttribute("ErrorMessage", "You Try To Login with Wrong Username or Password ... Try Again");
            rd.forward(request, response);
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
