package com.elkased.dao;


import com.elkased.model.TodoBean;
import com.elkased.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoListDao {

    public List<TodoBean> getAllTodoOf(String username) {

        try (Connection connection = DBUtils.getConnection()) {
            String fetchAllTodo = "SELECT * FROM todolist.usertodo WHERE usertodo.username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(fetchAllTodo);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetToList(resultSet);
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + e.getMessage());
            return new ArrayList<>();
        }

    }

    private List<TodoBean> resultSetToList(ResultSet resultSet) throws SQLException {
        try {
            List<TodoBean> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(creareTodoBean(resultSet));
            }
            return list;
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
            return null;
        }
    }

    public void createTodo(TodoBean todoBean, String username) {
        try (Connection connection = DBUtils.getConnection()) {
            String sql = "INSERT INTO ToDoList.usertodo VALUES (todo_id, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, todoBean.getTitle());
            preparedStatement.setString(2, todoBean.getDescription());
            preparedStatement.setString(3, todoBean.getStatus());
            preparedStatement.setDate(4, todoBean.getDate());
            preparedStatement.setString(5, username);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
    }

    public TodoBean getTodo(int todoId) {
        try (Connection connection = DBUtils.getConnection()) {

            String sql = "SELECT * FROM todolist.usertodo WHERE todo_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, todoId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return creareTodoBean(resultSet);
            }
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
        return null;
    }

    public void updateTodo(TodoBean todoBean, int todoId) {
        try (Connection connection = DBUtils.getConnection()) {

            String sql = "UPDATE todolist.usertodo SET title=?, description=?, status=?,date=? WHERE todo_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, todoBean.getTitle());
            preparedStatement.setString(2, todoBean.getDescription());
            preparedStatement.setString(3, todoBean.getStatus());
            preparedStatement.setDate(4, todoBean.getDate());
            preparedStatement.setInt(5, todoId);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
    }

    private TodoBean creareTodoBean(ResultSet resultSet) throws SQLException {
        TodoBean todoBean = new TodoBean();
        todoBean.setTodo_id(resultSet.getInt("todo_id"));
        todoBean.setTitle(resultSet.getString("title"));
        todoBean.setDescription(resultSet.getString("description"));
        todoBean.setStatus(resultSet.getString("status"));
        todoBean.setDate(resultSet.getDate("date"));

        return todoBean;
    }

    public void deleteTodo(int todoId) {
        try (Connection connection = DBUtils.getConnection()) {
            String sql = "DELETE FROM todolist.usertodo WHERE todo_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, todoId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
    }
}
