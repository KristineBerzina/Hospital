package com.company.connectToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

        public static Connection getConnection() {
            Connection connection = null;

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Betmens_07");
            }catch (SQLException e) {
                e.printStackTrace();
            }

            return connection;
        }
}
