package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver"); //각 DB 만든 회사들은 다르다. 패키지명도 다 다를테니까 호환성

        String url = "jdbc:mysql://localhost:3306/springbook";
        String dbUser = "spring";
        String password = "book";
        Connection con = DriverManager.getConnection(url, dbUser, password); //Connection도 벤더사가 구현 //DriverManager가 Class영역의 것과 어떻게 연관?

        String sql = "insert into users(id, name, password) values(?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql); //벤더사가 구현  //PS는 ? 사용 해서 동적 sql 가능 //sqlInjection 공격에 안전
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/springbook";
        String dbUser = "spring";
        String password = "book";
        Connection con = DriverManager.getConnection(url, dbUser, password);

        String sql = "select * from users where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        con.close();

        return user;
    }

}
