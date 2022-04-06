package ca.jrvs.apps.jdbc;

import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private final String url;
    private final Properties proerties;

    public DatabaseConnectionManager(String host, String databaseName, String username, String password){
        
            this.url = "jdbc:postgresql://"+ host + "/" + databaseName;
            this.proerties = new Properties();
            this.proerties.setProperty("user", username);
            this.proerties.setProperty("password", password);

    }

    public Connection getConnection () throws SQLException{
        return DriverManager.getConnection(this.url, this.proerties);
    }
}