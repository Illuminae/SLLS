/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import active_record.BookActiveRecord;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class BookSearchCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public BookSearchCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

@Override 
    public String execute() throws ServletException, IOException {
        String viewName;
        ArrayList<BookActiveRecord> bookList = BookActiveRecord.getBookList();
            viewName = "/book_search.jsp";
        request.setAttribute("bookList", bookList);
        return viewName;
    }
}
