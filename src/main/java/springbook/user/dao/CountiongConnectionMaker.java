package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class CountiongConnectionMaker implements ConnectionMaker {
    
    private int counter = 0;
    private ConnectionMaker realConnectionMaker;
    
    public CountiongConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
    }
    
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        
        return realConnectionMaker.makeConnection();
    }
    
    public int getCounter() {
        return this.counter;
    }

}
