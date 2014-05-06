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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class MyBooksCommand implements Command{
    private HttpServletRequest request;
    private HttpServletResponse response;

    public MyBooksCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    public String execute() throws ServletException, IOException{
        String viewName = "/myBooks.jsp";
        RegisteredUserActiveRecord user = (RegisteredUserActiveRecord)request.getSession().getAttribute("currentUser");
        ArrayList<BookActiveRecord> myBookList = BookActiveRecord.getBookList(user);
        ArrayList<LentBooksActiveRecord> myLentBooksList = LentBooksActiveRecord.getBookList(user.getUser_id());
        ArrayList<BookActiveRecord> myLentBooksList2 = new ArrayList();
        ArrayList<BookActiveRecord> tmpBookList;
        for(int i = 0; i<myLentBooksList.size(); i++){
            tmpBookList = BookActiveRecord.getBookList(myLentBooksList.get(i).getIsbn());
            myLentBooksList2.add(tmpBookList.get(0));
        }
        request.setAttribute("myLentBooksList", myLentBooksList2);
        request.setAttribute("myBookList", myBookList);
        return viewName;
    }
    
}
