package springbook.user.dao;

import lombok.Getter;
import lombok.Setter;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

@Getter @Setter
public class UserDao {

    DataSource dataSource;

    public UserDao() {
    }

    public UserDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void deleteAll() throws SQLException {
        Connection con = dataSource.getConnection();

        PreparedStatement ps = con.prepareStatement("delete from users");
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public int getCount() throws SQLException {
        Connection con = dataSource.getConnection();

        PreparedStatement ps = con.prepareStatement("select count(*) from users");
        ResultSet rs = ps.executeQuery();
        rs.next();

        int count = rs.getInt(1);

        rs.close();
        ps.close();
        con.close();

        return count;
    }

    public void add(User user) throws SQLException {

        Connection con = dataSource.getConnection();
        String sql = "insert into users(id, name, password) values(?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public User get(String id) throws SQLException {

        Connection con = dataSource.getConnection();

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
