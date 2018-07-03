package springbook.user.dao.count;

import lombok.Getter;
import springbook.user.dao.connectionMaker.ConnectionMaker;

import java.sql.Connection;
import java.sql.SQLException;

@Getter
public class CountingConnectionMaker implements ConnectionMaker {

    int count = 0;
    ConnectionMaker realConnectionMaker;

    public CountingConnectionMaker(){
    }
    public CountingConnectionMaker(ConnectionMaker connectionMaker){
        this.realConnectionMaker = connectionMaker;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.count++;
        return realConnectionMaker.makeConnection();
    }
}
