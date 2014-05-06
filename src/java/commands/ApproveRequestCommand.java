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
public class ApproveRequestCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ApproveRequestCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String execute() throws ServletException, IOException {
        String viewName = "/Controller?command=borrow_requests";
        RequestDispatcher rd = request.getRequestDispatcher("/approve_request");
        rd.include(request, response);
        
        return viewName;
    }
}
