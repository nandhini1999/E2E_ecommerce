package E2E.DBconnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class dbConnection {

    public Connection getConnection() throws IOException, SQLException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\E2E\\resources\\config.Properties");
        props.load(fis);
        String host = props.getProperty("host");
        String dbName = props.getProperty("dbName");
        String port = props.getProperty("port");
        String password = props.getProperty("password");

        String url = "jdbc:mysql://"+host+":"+port+"/"+dbName;
        System.out.println(url);
       Connection con =  DriverManager.getConnection(url,"root",password);
        return con;
    }
}
