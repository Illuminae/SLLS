/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import commands.Command;
import commands.CommandFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Creation of command by CommandFactory
         */
        Command command = CommandFactory.createCommand(request, response);
        /**
         * method execute of respective command created by factory executed,
         * returns respective view
         */
        
        String userAgent = request.getHeader("user-agent").trim().toLowerCase();
        String viewPath = "";
        if (userAgent.startsWith("sllsclient")) {
            viewPath = "xml/";
        } 
        String viewPage;
        if (command == null) {
            viewPage = viewPath + "/index.jsp";
        } else {
            viewPage = command.execute();
        }
        RequestDispatcher rd = request.getRequestDispatcher(viewPage);
        rd.forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
