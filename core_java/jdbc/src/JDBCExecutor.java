package ca.jrvs.apps.jdbc;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCExecutor{
    public static void main(String... args){
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport", "postgres", "password");
    
        try{
            Connection connection = dcm.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT (*) FROOM CUSTOMER");
            
            while(resultSet.next()){
                system.out.println(resultSet.getInt(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
