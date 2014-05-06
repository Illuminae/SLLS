/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class ReturnBookCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ReturnBookCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String execute() throws ServletException, IOException {
        String viewName = "/Controller?command=mybooks";
        RequestDispatcher rd = request.getRequestDispatcher("/mark_returned");
        rd.include(request, response);

        return viewName;
    }
}
