package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erik
 */
public abstract class CommandFactory {

    public static Command createCommand(HttpServletRequest request,
            HttpServletResponse response) {
        Command command = null;
        String userCommand = request.getParameter("command");

        if (userCommand != null && !userCommand.equals("")) {
            if (userCommand.equalsIgnoreCase("register")) {
                command = new RegisterCommand(request, response);
            } else if (userCommand.equalsIgnoreCase("login")) {
                command = new LoginCommand(request, response);
            } else if (userCommand.equalsIgnoreCase("logout")) {
                command = new LogoutCommand(request, response);
            } else if (userCommand.equalsIgnoreCase("booksearch")) {
                command = new BookSearchCommand(request, response);
            } else if (userCommand.equalsIgnoreCase("mybooks")) {
                command = new MyBooksCommand(request, response);
            } else if (userCommand.equalsIgnoreCase("add_book")) {
                command = new AddBookCommand(request, response);
            } else if (userCommand.equalsIgnoreCase("lend_book")) {
                command = new LendBookCommand(request, response);
            } else if (userCommand.equalsIgnoreCase("borrow_requests")) {
                command = new BorrowRequestCommand(request, response);
            } else if (userCommand.equalsIgnoreCase("approve_request")) {
                command = new ApproveRequestCommand(request, response);
            } else if (userCommand.equalsIgnoreCase("reject_request")) {
                command = new RejectRequestCommand(request, response);
            }

        }
        return command;

    }
}
