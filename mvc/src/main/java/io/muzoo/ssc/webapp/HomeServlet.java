package io.muzoo.ssc.webapp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // do MVC in here
        // Extract some parameters from request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        // Create User model
        User u = new User(firstName, lastName);
        // Pass user model object
        request.setAttribute("user", u);
        // Render home page using WEB-INF/home.jsp and user model
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
        rd.include(request, response);
    }
}
