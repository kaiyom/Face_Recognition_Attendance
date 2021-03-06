package db_connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Connection {
    public Connection dbConnection;
    public ResultSet resultSet;

    public boolean ifTableExists(String tableName){
        boolean ans=false;
        try{
            DatabaseMetaData databaseMetaData = dbConnection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "student", new String[]{"TABLE"});
            ans = resultSet.next();
        }catch(SQLException exception){
            System.out.println(exception);
        }
        return ans;
    }

    public void createTable(String tableName) {
        try {
            PreparedStatement preparedStatement = dbConnection.
                    prepareStatement("CREATE TABLE student (id int, first_name varchar(255), last_name varchar(255), student_class int, class_section varchar(22))");
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public void connectDatabase() {
        try {
            System.setProperty("jdbc.Driver", "org.mysql.Driver");
            dbConnection = DriverManager.
                    getConnection("jdbc:mysql://127.0.0.1/face_recognition", "root", "");
            System.out.println("Successfuly connected to database");
        } catch (SQLException e) {
            System.out.println("Unsuccessful database connection " + e);
        }
    }

    public void disconnectDatabase() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("Could not disconnect database connection " + e);
        }
    }

    public void executesql(String sql) {
        try {
            Statement statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);
        } catch (Exception e) {
            System.out.println("Could not execute query " + e);
        }
    }
}
