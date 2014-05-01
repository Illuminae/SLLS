package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public abstract class CommandFactory
{
    public static Command createCommand(HttpServletRequest request, 
                                        HttpServletResponse response)
    {
        Command command = null;
        String userCommand = request.getParameter("command");
        
        if (userCommand != null && !userCommand.equals(""))
        {
            if (userCommand.equalsIgnoreCase("register"))
            {
                command = new RegisterCommand(request, response);
            } else if (userCommand.equalsIgnoreCase("login")){
                command = new LoginCommand(request, response);
            }
            
        }
        return command;
        
        
    }
}
    
