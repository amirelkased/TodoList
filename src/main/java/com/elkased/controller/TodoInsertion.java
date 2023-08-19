package com.elkased.controller;

import com.elkased.dao.TodoListDao;
import com.elkased.model.TodoBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/todo/new")
public class TodoInsertion extends HttpServlet {
    private TodoListDao todoListDao;

    @Override
    public void init() throws ServletException {
        todoListDao = new TodoListDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/todo/AddTodo.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + "=>" + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TodoBean todoBean = TodoUpdater.getTodoBean(req);

            HttpSession session = req.getSession(false);
            todoListDao.createTodo(todoBean, session.getAttribute("Active-user").toString());

            resp.sendRedirect(req.getContextPath() + "/todo");
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + "=>" + e.getMessage());
        }
    }
}
