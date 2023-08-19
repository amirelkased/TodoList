package com.elkased.controller;

import com.elkased.dao.LoginDao;
import com.elkased.model.LoginBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class UserLogin extends HttpServlet {
    private LoginDao loginDAO;

    public void init() {
        loginDAO = new LoginDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/login/LoginPage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (loginDAO.validate(loginBean)) {
                session.setAttribute("Active-user", username);
                resp.sendRedirect("todo");
            } else {
                session.setAttribute("notify", "Something wrong!");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login/LoginPage.jsp");
                requestDispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
    }
}
