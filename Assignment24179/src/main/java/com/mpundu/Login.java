package com.mpundu;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // Expected valid Email and Password
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hard-coded email and password (replace with your own)
        String correctEmail = "admin@auca.ac.rw";
        String correctPassword = "12345678";

        // Get user-entered credentials
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email.equals(correctEmail) && password.equals(correctPassword)) {
            // Successful login, redirect to the home page or another protected resource
            response.sendRedirect("homePage.html");
        } else {
            // Incorrect credentials, redirect to the forgot password page
            response.sendRedirect("PassRcover.html");
        }
    }
}

