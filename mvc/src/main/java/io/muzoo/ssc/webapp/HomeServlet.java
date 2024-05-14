package io.muzoo.ssc.webapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
        rd.include(request, response);
        request.getSession().removeAttribute("user");
    }
}
