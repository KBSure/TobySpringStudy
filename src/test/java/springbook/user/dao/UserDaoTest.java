package springbook.user.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import springbook.user.domain.User;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    UserDao dao;

    public static void main(String[] args) {
        JUnitCore.main("springbook.user.dao.UserDaoTest");
    }

    @Before
    public void setup(){
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        this.dao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void count() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        //User필요
        User user1 = new User("kbs", "김병수", "7");
        User user2 = new User("mbc", "마봉춘", "11");
        User user3 = new User("sbs", "신봉선", "5");

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.get("unknown_id");
    }

    @Test
    public void addAndGet() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        User user1 = new User("kbs", "김병수", "7");
        User user2 = new User("mbc", "마봉춘", "11");

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User userGet1 = dao.get(user1.getId());
        assertThat(userGet1.getName()).isEqualTo(user1.getName());
        assertThat(userGet1.getPassword()).isEqualTo(user1.getPassword());

        User userGet2 = dao.get(user2.getId());
        assertThat(userGet2.getName()).isEqualTo(user2.getName());
        assertThat(userGet2.getPassword()).isEqualTo(user2.getPassword());
    }
}
