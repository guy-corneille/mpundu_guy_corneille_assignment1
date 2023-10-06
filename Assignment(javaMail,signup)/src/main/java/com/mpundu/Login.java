package com.mpundu;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate user credentials using UserRepository
        if (UserRepository.isValidUser(email, password)) {
            // Successful login
            HttpSession session = request.getSession(true); // Create a session if it doesn't exist
            session.setAttribute("user", email); // Store user information in the session
            response.sendRedirect("homePage.html");
        } else {
            // Invalid login
            response.sendRedirect("login.html?error=1");
        }
    }
}


