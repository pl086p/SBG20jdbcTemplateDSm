package db.jdbcTemplate;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JdbcApplication {
    
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = SpringApplication.run(JdbcApplication.class, args);

        DoWork doWork = new DoWork(context);
        doWork.showDataFrom_defaultDatasource();
        doWork.showDataFrom_dsA();
        doWork.showDataFrom_dsB();


    }
}
