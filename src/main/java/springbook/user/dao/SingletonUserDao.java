package springbook.user.dao;

public class SingletonUserDao {
    private static SingletonUserDao INSTANCE;
    
    private ConnectionMaker connectionMaker;
    
    private SingletonUserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
    
    public static synchronized SingletonUserDao getInstance() {
        if(INSTANCE == null) INSTANCE = new SingletonUserDao(new DConnectionMaker());
        return INSTANCE;
    }
}
