package com.elkased.controller;

import com.elkased.dao.RegisterDao;
import com.elkased.model.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/register")
public class UserRegister extends HttpServlet {

    private UserBean userBean;

    @Override
    public void init() {
        userBean = new UserBean();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("register/Register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        userBean = new UserBean(fname, lname, username, password);
        RegisterDao registerDAO = new RegisterDao();

        if (registerDAO.register(userBean)) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login/LoginPage.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            session.setAttribute("notify", "Username is already exists!");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/register/Register.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
