/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import active_record.BookActiveRecord;
import active_record.LentBooksActiveRecord;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class mark_returned extends HttpServlet {

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
        
        LentBooksActiveRecord bookToReturn = LentBooksActiveRecord.getBookData((String) request.getParameter("isbn")).get(0);
        
        GregorianCalendar startDate = new GregorianCalendar();
        startDate.setTimeInMillis(bookToReturn.getStart_date().getTime());
        int duration = bookToReturn.getDuration();
        startDate.add(GregorianCalendar.DAY_OF_MONTH, duration);
        GregorianCalendar currentDate = new GregorianCalendar();
        
        //Logger.getLogger(mark_returned.class.getName()).log(Level.INFO, startDate.toString());
        //Logger.getLogger(mark_returned.class.getName()).log(Level.INFO, currentDate.toString());
        
        if (startDate.after(currentDate)){
            request.setAttribute("payFine", false);
            
        } else {
            request.setAttribute("payFine", true);
        }
        LentBooksActiveRecord.deleteLentBooksActiveRecord(bookToReturn.getIsbn(), bookToReturn.getOwner());
        BookActiveRecord returnBook = BookActiveRecord.getBookList(bookToReturn.getIsbn()).get(0);
        returnBook.setStatus("available");
        boolean success = returnBook.update();
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
