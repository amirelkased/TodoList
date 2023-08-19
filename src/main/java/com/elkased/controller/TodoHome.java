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
import java.util.List;

@WebServlet("/todo")
public class TodoHome extends HttpServlet {

    private TodoListDao todoListDao;

    @Override
    public void init() throws ServletException {
        todoListDao = new TodoListDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            String username = session.getAttribute("Active-user").toString();

            List<TodoBean> todoList = todoListDao.getAllTodoOf(username);
            session.setAttribute("todoList", todoList);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/todo/ListTodo.jsp");
            requestDispatcher.forward(req, resp);

        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
    }
}
