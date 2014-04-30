/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import active_record.RegisteredUserActiveRecord;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class create_user extends HttpServlet {

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
        String first_name = (String) request.getParameter("firstName");
        String last_name = (String) request.getParameter("lastName");
        String password = (String) request.getParameter("pWord");
        String user_name = (String) request.getParameter("userName");
        String zip_code = (String) request.getParameter("zipCode");
        String city = (String) request.getParameter("city");
        String street = (String) request.getParameter("street");
        String house_no = (String) request.getParameter("houseNo");
        String iban = (String) request.getParameter("iban");
        
        RegisteredUserActiveRecord user = new RegisteredUserActiveRecord();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setPassword(password);
        user.setUser_name(user_name);
        user.setZip_code(zip_code);
        user.setTown(city);
        user.setStreet(street);
        user.setHouse_no(house_no);
        user.setIban(iban);
        
        boolean insertSuccess = user.insert();
        request.setAttribute("insertSuccess", insertSuccess);
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
