/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import active_record.BookActiveRecord;
import active_record.LentBooksActiveRecord;
import active_record.RegisteredUserActiveRecord;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Erik
 */
public class approve_request extends HttpServlet{
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //fetch Book data from DB
        ArrayList<BookActiveRecord> approvedBook = BookActiveRecord.getBookList((String) request.getParameter("isbn"));
        //Setting new values to book table
        approvedBook.get(0).setStatus("borrowed");
        //fetching book from lent table
        ArrayList<LentBooksActiveRecord> borrowedBook = LentBooksActiveRecord.getBookData((String) request.getParameter("isbn"));
        //Setting new Values to lent table
        borrowedBook.get(0).setActive(true);
        borrowedBook.get(0).setStart_date(new Date(new GregorianCalendar().getTimeInMillis()));
        //getting user that is requesting the book for information reasons
        RegisteredUserActiveRecord user = RegisteredUserActiveRecord.getUserList(borrowedBook.get(0).getUser_id()).get(0);
        // Set attribute with lender's data so that adress can be  displayed!
        if (user != null && user.getUser_type() == 2) {
            borrowedBook.get(0).setDuration(28);
        } else if (user != null && user.getUser_type() == 1) {
            borrowedBook.get(0).setDuration(14);
        }
        //applying updates to DB
        boolean bookstable = approvedBook.get(0).update();
        boolean lenttable = borrowedBook.get(0).update();
        boolean appSuccess = bookstable && lenttable;
        request.setAttribute("requestingUser", user);
        request.setAttribute("appSuccess", appSuccess);

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
