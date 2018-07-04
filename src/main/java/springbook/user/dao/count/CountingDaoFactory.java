package springbook.user.dao.count;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import springbook.user.dao.UserDao;

import javax.sql.DataSource;

@Configuration
public class CountingDaoFactory {

    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/springbook?serverTimezone=UTC&useSSL=false");
        dataSource.setUsername("spring");
        dataSource.setPassword("book");

        return dataSource;
    }

    @Bean
    public UserDao userDao(){
        UserDao userDao = new UserDao(dataSource());
        return userDao;
    }

//    @Bean
//    public ConnectionMaker realConnectionMaker(){
//        return new NConnectionMaker();
//    }

}
