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
        String first_name = (String) request.getParameter("firstName").trim();
        String last_name = (String) request.getParameter("lastName").trim();
        String password = (String) request.getParameter("pWord").trim();
        String user_name = (String) request.getParameter("userName").trim();
        String zip_code = (String) request.getParameter("zipCode").trim();
        String city = (String) request.getParameter("city").trim();
        String street = (String) request.getParameter("street").trim();
        String house_no = (String) request.getParameter("houseNo").trim();
        String iban = (String) request.getParameter("iban").trim();
        
        if ( first_name.isEmpty() || last_name.isEmpty() || password.isEmpty() || 
             user_name.isEmpty() || zip_code.isEmpty() || city.isEmpty() ||
             street.isEmpty() || house_no.isEmpty() || iban.isEmpty()
                ){
            boolean insertSuccess = false;
            request.setAttribute("insertSuccess", insertSuccess);
            
        }else {
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
