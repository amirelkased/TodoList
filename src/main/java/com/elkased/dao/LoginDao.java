package com.elkased.dao;

import com.elkased.model.LoginBean;
import com.elkased.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

    public boolean validate(LoginBean loginBean) {

        boolean status = false;

        String sql = "SELECT * FROM todolist.user WHERE username=? and password=?";
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return status;
    }
}
