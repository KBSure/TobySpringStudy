package springbook.user.dao;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springbook.user.domain.User;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    public static void main(String[] args) {
        JUnitCore.main("springbook.user.dao.UserDaoTest");
    }

    //getCount()와 deleteAll() 점검
//    @Test
//    public void getCount() throws SQLException {
//        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//        UserDao dao = context.getBean("userDao",UserDao.class);
////        dao.deleteAll();
//        assertThat(dao.getCount()).isEqualTo(0);
//        addAndGet();
//        assertThat(dao.getCount()).isEqualTo(1);
//    }


    @Test
    public void addAndGet() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        User user = new User();
        user.setId("gyumee");
        user.setName("박성철");
        user.setPassword("springno1");

        dao.add(user);
        assertThat(dao.getCount()).isEqualTo(1);

        User user2 = dao.get(user.getId());

        assertThat(user2.getName()).isEqualTo(user.getName());
        assertThat(user2.getPassword()).isEqualTo(user.getPassword());
    }
}
