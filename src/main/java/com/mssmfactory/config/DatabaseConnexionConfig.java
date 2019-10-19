package com.mssmfactory.config;

import com.google.inject.Provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnexionConfig implements Provider<Connection> {

    public static String DATABASE_HOSTNAME = "localhost:5432";
    public static String DATABASE_NAME = "credit_card_system_account";
    public static String DATABASE_USERNAME = "mssm1996";
    public static String DATABASE_PASSWORD = "mssm1996";

    @Override
    public Connection get() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + DATABASE_HOSTNAME + "/" + DATABASE_NAME;

            Properties props = new Properties();
            props.setProperty("user", DATABASE_USERNAME);
            props.setProperty("password", DATABASE_PASSWORD);
            props.setProperty("ssl", "true");

            return DriverManager.getConnection(url, props);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        throw new RuntimeException();
    }
}
