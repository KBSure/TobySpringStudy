package springbook.user.dao.connectionMaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {

    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/springbook?serverTimezone=UTC&useSSL=false";
        String dbUser = "spring";
        String password = "book";
        Connection con = DriverManager.getConnection(url, dbUser, password);

        return con;
    }
}
