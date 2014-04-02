package commands;
import java.io.IOException;
import javax.servlet.ServletException;

public interface Command
{
    String execute() throws ServletException, IOException;
}