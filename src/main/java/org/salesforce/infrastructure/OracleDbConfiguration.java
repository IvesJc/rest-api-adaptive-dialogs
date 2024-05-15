package org.salesforce.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleDbConfiguration {
    public static String URL_CONNECTION;
    public static String USER;
    public static String PASSWORD;

    public OracleDbConfiguration(){
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties"))
        {
            var prop = new Properties();
            prop.load(input);
            URL_CONNECTION = prop.getProperty("database.url");
            USER = prop.getProperty("database.user");
            PASSWORD = prop.getProperty("database.password");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection()  {
        try {
            return DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
