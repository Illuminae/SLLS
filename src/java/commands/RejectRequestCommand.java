/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import active_record.BookActiveRecord;
import active_record.LentBooksActiveRecord;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class RejectRequestCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public RejectRequestCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String execute() throws ServletException, IOException {
        String viewName = "/Controller?command=borrow_requests";
        ArrayList<BookActiveRecord> rejectedBook = BookActiveRecord.getBookList((String) request.getParameter("isbn"));
        rejectedBook.get(0).setStatus("available");
        LentBooksActiveRecord.deleteLentBooksActiveRecord(rejectedBook.get(0).getIsbn(), rejectedBook.get(0).getOwner());
        boolean rejectSuccess = rejectedBook.get(0).update();
        request.setAttribute("rejSuccess", rejectSuccess);
        return viewName;
    }

}
