package com.mpundu;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet Filter implementation class EmailFilter
 */
public class EmailFilter implements Filter {

    /**
     * Default constructor.
     */
    public EmailFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Get the email parameter from the request
        String email = request.getParameter("email");

        if (isValidEmail(email)) {
            // Email is valid, proceed with the request
            chain.doFilter(request, response);
        } else {
            // Invalid email, you can handle this as needed (e.g., show an error message)
            request.setAttribute("error", "Invalid email address");
            request.getRequestDispatcher("/error.html").forward(request, response);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

    /**
     * Validate an email address using a simple regular expression.
     * You can use a more comprehensive email validation library for production.
     *
     * @param email The email address to validate.
     * @return true if the email is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Define a simple regular expression for email validation
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
