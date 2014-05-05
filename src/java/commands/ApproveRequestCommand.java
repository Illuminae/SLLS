/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import active_record.BookActiveRecord;
import active_record.LentBooksActiveRecord;
import active_record.RegisteredUserActiveRecord;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class ApproveRequestCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ApproveRequestCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String execute() throws ServletException, IOException {
            String viewName = "/Controller?command=borrow_requests";
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
        } else if (user != null && user.getUser_type() == 1){
            borrowedBook.get(0).setDuration(14);
        }
        //applying updates to DB
            boolean bookstable = approvedBook.get(0).update();
            boolean lenttable = borrowedBook.get(0).update();
            boolean appSuccess = bookstable && lenttable;
            request.setAttribute("requestingUser", user);
            request.setAttribute("appSuccess", appSuccess);

            return viewName;
    }
}
