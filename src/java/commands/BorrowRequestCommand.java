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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class BorrowRequestCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public BorrowRequestCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String execute() throws ServletException, IOException {
        String viewName = "/borrow_requests.jsp";
        ArrayList<BookActiveRecord> myRequestsBookList = BookActiveRecord.getRequestedBookList((RegisteredUserActiveRecord) request.getSession().getAttribute("currentUser"));
        request.setAttribute("myRequestsBookList", myRequestsBookList);
        return viewName;
    }

}
