/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import active_record.RegisteredUserActiveRecord;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public class PurchaseCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public PurchaseCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }   
    
    @Override
         public String execute()
    {  
        String viewName = "settings.jsp";
        RegisteredUserActiveRecord currentUser = (RegisteredUserActiveRecord)request.getSession().getAttribute("currentUser");
        currentUser.setUser_type(2);
        boolean success = currentUser.update();
        request.setAttribute("purchaseSuccess", success);
        request.getSession().setAttribute("currentUser", currentUser);
        return viewName;
    }

}
