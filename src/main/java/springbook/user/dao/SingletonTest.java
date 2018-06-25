package springbook.user.dao;

public class SingletonTest {
    
    public static void main(String[] args) {
        DaoFactory daoFactory = new DaoFactory();
        UserDao dao1 = daoFactory.userDao();
        UserDao dao2 = daoFactory.userDao();
    
        System.out.println(dao1);
        System.out.println(dao2);
        System.out.println("dao1 == dao2" + (dao1 == dao2));
        
        
    }
    
}
