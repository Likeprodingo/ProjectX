package by.shibaev.university.controller.servlet;

import by.shibaev.university.controller.command.Command;
import by.shibaev.university.controller.command.provider.CommandProvider;
import by.shibaev.university.controller.command.util.PagePath;
import by.shibaev.university.controller.command.util.RequestData;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/controller")
public class MainController extends HttpServlet {

    private Logger logger = LogManager.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request,response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestData requestData = new RequestData(request);
        Optional<Command> processor = CommandProvider.provide(requestData);
        Command command = processor.orElseThrow(IllegalAccessError::new);
        logger.log(Level.INFO, command);
        String page = command.process(requestData);
        if (page != null){
            logger.log(Level.INFO, "Page: {}",page);
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(PagePath.ERROR);
        }
    }
}
