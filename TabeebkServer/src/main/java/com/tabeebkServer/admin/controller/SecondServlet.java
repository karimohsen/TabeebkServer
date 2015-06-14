/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.admin.controller;

import com.TKSL.tabebk.writeToExcel.WriteExcelDemo;
import com.tabeebkServer.parseExcel.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amal
 */
@WebServlet(name = "SecondServlet", urlPatterns = {"/SecondServlet"})
public class SecondServlet extends HttpServlet {

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
      //  response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ArrayList<String> docSelectors=new ArrayList(7);
        ArrayList<String> clinicSelectors=new ArrayList(10);
        ArrayList<String> hosSelectors=new ArrayList(13);
        String temp;
        for (int i = 1; i <= 7; i++) {
          temp =(String) request.getParameter("docselector"+i).trim();
          if(!temp.equals(""))
           docSelectors.add(temp);
         /* temp =(String) request.getParameter("clinicselector"+i).trim();
          if(!temp.equals(""))
           labSelectors.add(temp);
          temp =(String) request.getParameter("hosselector"+i).trim();
          if(!temp.equals(""))
           
          hosSelectors.add(temp);
          */
          
//            System.out.println("docselector "+i+" is "+docSelectors.get(i-1));
//            System.out.println("labselector "+i+" is "+labSelectors.get(i-1));
//            System.out.println("hosselector "+i+" is "+hosSelectors.get(i-1));
        }
        
               for (int i = 1; i <= 9; i++) {
          temp =(String) request.getParameter("clinicselector"+i).trim();
          if(!temp.equals(""))
           clinicSelectors.add(temp);
        
        }
                       for (int i = 1; i <= 12; i++) {
          temp =(String) request.getParameter("hospitalSelector"+i).trim();
          if(!temp.equals(""))
           hosSelectors.add(temp);
        
        }
        System.out.println("doctorlist size is "+docSelectors.size());
        System.out.println("cliniclist size is "+clinicSelectors.size());
        WriteExcelDemo wr=new WriteExcelDemo();
        if(!docSelectors.isEmpty())
        wr.writeToExcel(docSelectors);
        wr.writeToExcelClinic(clinicSelectors,2);
        wr.writeToExcelClinic(hosSelectors,1);
        
        /////download excel file 
        response.setContentType("APPLICATION/OCTET-STREAM");
        String fileName="mspWorkBook.xls" ;
          String path = this.getClass().getClassLoader().getResource("").getPath();

            String fullPath = URLDecoder.decode(path, "UTF-8");
String pathArr[] = fullPath.split("/WEB-INF/classes/");
        String filePath =pathArr[0]+"\\bulkDownload\\";

        response.setHeader("Content-Disposition","attachment; filename=\"" + fileName+ "\"");
        try (PrintWriter out2 = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            FileInputStream str=new FileInputStream(filePath+fileName);
            int i;
            while((i=str.read())!=-1){
                out2.write(i);
            }
            str.close();
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
