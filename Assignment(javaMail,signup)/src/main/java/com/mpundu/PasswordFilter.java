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
 * Servlet Filter implementation class PasswordFilter
 */
public class PasswordFilter implements Filter {

    /**
     * Default constructor.
     */
    public PasswordFilter() {
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

        // Get the password parameter from the request
        String password = request.getParameter("password");

        if (isValidPassword(password)) {
            // Password is valid, proceed with the request
            chain.doFilter(request, response);
        } else {
            // Invalid password, you can handle this as needed (e.g., show an error message)
            request.setAttribute("error", "Invalid password");
            request.getRequestDispatcher("/error.html").forward(request, response);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

    /**
     * Validate a password using a simple regular expression.
     * You can customize the regex and validation logic as needed.
     *
     * @param password The password to validate.
     * @return true if the password is valid, false otherwise.
     */
    private boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        // Define a simple regular expression for password validation (e.g., at least 8 characters)
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
