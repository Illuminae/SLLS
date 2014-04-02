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
public class RegisterCommand implements Command{
    private HttpServletRequest request;
    private HttpServletResponse response;
    
    public RegisterCommand(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
    }
     public String execute() throws ServletException, IOException
    {   
        String viewName;
        RequestDispatcher rd = request.getRequestDispatcher("/create_user");
        rd.include(request,response);
         viewName = "/index.html";
        
        
        
        return viewName;
    }
}
