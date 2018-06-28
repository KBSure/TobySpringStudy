package springbook.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    public static void main(String[] args) {
        DaoFactory daoFactory = new DaoFactory();
        UserDao dao1 = daoFactory.userDao();
        UserDao dao2 = daoFactory.userDao();

        System.out.println(dao1);
        System.out.println(dao2);
        System.out.println("dao1 == dao2 : " + (dao1 == dao2));

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao3 = context.getBean("userDao", UserDao.class);
        UserDao dao4 = context.getBean("userDao", UserDao.class);

        System.out.println(dao3);
        System.out.println(dao4);
        System.out.println("dao3 == dao4 : "+ (dao3 == dao4));

        SingletonUserDao singletonUserDao1 = SingletonUserDao.getInstance();
        SingletonUserDao singletonUserDao2 = SingletonUserDao.getInstance();

        System.out.println(singletonUserDao1);
        System.out.println(singletonUserDao2);
        System.out.println("singletonUserDao1 == singletonUserDao2 : "+ (singletonUserDao1 == singletonUserDao2));

    }

}
