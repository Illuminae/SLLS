/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import active_record.BookActiveRecord;
import active_record.RegisteredUserActiveRecord;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class add_book extends HttpServlet {

    /**
     * Does not validate whether ISBN is 15 or less characters.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean bookSuccess;
        String isbn = (String) request.getParameter("isbn").trim();
        String author = (String) request.getParameter("author").trim();
        String title = (String) request.getParameter("title").trim();
        String year = (String) request.getParameter("year").trim();
        String publisher = (String) request.getParameter("publisher").trim();
        if (isbn.isEmpty() || author.isEmpty() || title.isEmpty() || year.isEmpty() || publisher.isEmpty()) {
            bookSuccess = false;
            request.setAttribute("bookSuccess", bookSuccess);
        } else {
            RegisteredUserActiveRecord user = (RegisteredUserActiveRecord)request.getSession().getAttribute("currentUser");
            
            BookActiveRecord newBook = new BookActiveRecord("available", isbn, author, title, Integer.parseInt(year), publisher, user.getUser_id());
            bookSuccess = newBook.insert();
        }
        request.setAttribute("bookSuccess", bookSuccess);
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
