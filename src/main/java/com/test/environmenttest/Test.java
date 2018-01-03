/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.environmenttest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author martin
 */
@WebServlet(name = "Test", urlPatterns = {"/test"})
public class Test extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Environment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>System Properties" + "</h1>");

            Properties p = System.getProperties();
            for (String key : p.stringPropertyNames()) {
                String value = p.getProperty(key);
                out.println(key + " = " + value + "<br/>");
            }

            out.println("<h1>System Environment" + "</h1>");

            Map<String, String> env = System.getenv();
            for (String envName : env.keySet()) {
                out.format("%s = %s%n", envName, env.get(envName));
                out.println("<br/>");
            }

            String propertyFile = "/tmp/app-data/test.properties";
            File tmpDir = new File(propertyFile);
            boolean fileExists = tmpDir.exists();

            if (fileExists) {
                out.println("<h1>File Properties (" + propertyFile + ")</h1>");

                Properties fileProperties = new Properties();
                fileProperties.load(new FileInputStream(propertyFile));
                for (String key : fileProperties.stringPropertyNames()) {
                    String value = fileProperties.getProperty(key);
                    out.println(key + " = " + value + "<br/>");
                }
            } else {
                out.println("<h1>File Properties (DOES NOT EXIST! - " + propertyFile + ")</h1>");
            }

            out.println("</body>");
            out.println("</html>");
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
