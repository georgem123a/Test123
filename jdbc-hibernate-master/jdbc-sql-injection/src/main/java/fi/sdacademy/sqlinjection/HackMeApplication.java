package fi.sdacademy.sqlinjection;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

@ComponentScan("fi.sdacademy.sqlinjection")
@SpringBootApplication
public class HackMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HackMeApplication.class, args);
    }

    @Bean
    public DataSource getDataSource() throws Exception {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:test;MODE=MYSQL");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        DataSource result = dataSourceBuilder.build();

        initDb(result);

        return result;
    }

    private void initDb(DataSource db) throws Exception {
        Connection con = db.getConnection();
        String sql = IOUtils.toString(getClass().getResourceAsStream("/db.sql"), StandardCharsets.UTF_8);
        for (String statement : sql.split(";")) {
            con.createStatement().execute(statement);
        }
    }
}
