package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection con = getConnection("jdbc:mysql://localhost:3306/springbook");

        String sql = "insert into users(id, name, password) values(?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql); //벤더사가 구현  //PS는 ? 사용 해서 동적 sql 가능 //sqlInjection 공격에 안전
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    private Connection getConnection(String s) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); //각 DB 만든 회사들은 다르다. 패키지명도 다 다를테니까 호환성

        String url = s;
        String dbUser = "spring";
        String password = "book";
        return DriverManager.getConnection(url, dbUser, password);
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection con = getConnection("jdbc:mysql://localhost:3306/springbook?serverTimezone=UTC&useSSL=false");

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

    public static void main(String[] args) throws SQLException, ClassNotFoundException { //main 메서드 :
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }

}
