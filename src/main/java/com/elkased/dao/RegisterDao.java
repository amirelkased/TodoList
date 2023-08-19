package com.elkased.dao;

import com.elkased.model.UserBean;
import com.elkased.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterDao {

    public boolean register(UserBean userBean) {

        int i = 0;
        String sql = "INSERT INTO todolist.user VALUES(user_id,?,?,?,?);";

        try (Connection connection = DBUtils.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userBean.getUsername());
            preparedStatement.setString(2, userBean.getPassword());
            preparedStatement.setString(3, userBean.getFirstName());
            preparedStatement.setString(4, userBean.getLastName());

            i = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return i != 0;
    }
}
