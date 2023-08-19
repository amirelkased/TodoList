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
import java.sql.Date;

@WebServlet("/todo/update")
public class TodoUpdater extends HttpServlet {
    private TodoListDao todoListDao;

    @Override
    public void init() throws ServletException {
        todoListDao = new TodoListDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TodoBean todoBean = todoListDao.getTodo(Integer.parseInt(req.getParameter("id")));
            HttpSession session = req.getSession(false);
            session.setAttribute("todo", todoBean);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/todo/EditTodo.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            TodoBean todoBean = getTodoBean(req);

            HttpSession session = req.getSession(false);

            int todoId = ((TodoBean) session.getAttribute("todo")).getTodo_id();
            todoListDao.updateTodo(todoBean, todoId);

            resp.sendRedirect(req.getContextPath() + "/todo");
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
    }

    protected static TodoBean getTodoBean(HttpServletRequest req) {
        String todoTitle = req.getParameter("title");
        String todoDescription = req.getParameter("description");
        String todoStatus = req.getParameter("status");
        Date todoDeadline = Date.valueOf(req.getParameter("deadline"));

        return new TodoBean(todoTitle, todoDescription, todoStatus, todoDeadline);
    }
}
