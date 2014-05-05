/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import active_record.BookActiveRecord;
import active_record.RegisteredUserActiveRecord;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class LendBookCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public LendBookCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String execute() throws ServletException, IOException {
        String viewName;
        /**
         * User is fetched from Session and ISBN of book to be lend from form
         */
        RegisteredUserActiveRecord user = (RegisteredUserActiveRecord)request.getSession().getAttribute("currentUser");
        String isbn = request.getParameter("isbn");
        /**
         * returns whether book CAN be lend or user is owner of the book
         */
        if(user != null){
        boolean isOwner = BookActiveRecord.isOwner(isbn, user.getUser_id());
        ArrayList<BookActiveRecord> requestedBook = BookActiveRecord.getBookList(isbn);
        /**
         * Value whether lending was successful is written in request
         */
        boolean lendSuccess;
        if (isOwner || requestedBook.get(0).getStatus().equalsIgnoreCase("pending") || requestedBook.get(0).getStatus().equalsIgnoreCase("borrowed")){
        lendSuccess = false;
        request.setAttribute("lendSuccess", lendSuccess);
        } else {
        lendSuccess = true;
        BookActiveRecord.setPending(isbn);
        request.setAttribute("lendSuccess", lendSuccess);
        }
        viewName = "/Controller?command=booksearch";
        } else {
            viewName = "/home.jsp";
        }
        return viewName;
        
    }
}
