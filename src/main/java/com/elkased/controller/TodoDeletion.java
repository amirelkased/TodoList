package com.elkased.controller;

import com.elkased.dao.TodoListDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/todo/delete")
public class TodoDeletion extends HttpServlet {
    TodoListDao todoListDao;

    @Override
    public void init() throws ServletException {
        todoListDao = new TodoListDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int todoId = Integer.parseInt(req.getParameter("id"));

            todoListDao.deleteTodo(todoId);

            resp.sendRedirect(req.getContextPath() + "/todo");
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
    }
}
