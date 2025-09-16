package com.example.orm_elite_driving_school_system.dao;

import lk.ijse.elitedrivingschoolsystemormcoursework.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    public static ResultSet executeQuery(String sql, Object... ob) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        for (int i = 0; i < ob.length; i++) {
            pstm.setObject(i + 1, ob[i]);
        }
        return pstm.executeQuery();
    }
    public static boolean executeUpdate(String sql, Object... ob) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        for (int i = 0; i < ob.length; i++) {
            pstm.setObject(i + 1, ob[i]);
        }
        return pstm.executeUpdate()>0;
    }
}
