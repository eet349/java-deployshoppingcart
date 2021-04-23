package school.lambda.shoppingcartdeployed.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Value("${local.run.db:h2}")
    private String dbValue;

    @Value("${spring.datasource.url:}")
    private String dbURL;

    @Bean
    public DataSource dataSource() {
        if(dbValue.equalsIgnoreCase("POSTGRESQL")) {
            //  Assume Heroku
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.postgresql.Driver");
            config.setJdbcUrl(dbURL);
            return new HikariDataSource(config);
        } else {
            //  Assume h2
            String URLString = "jdbc:h2:mem:testdb";
            String driverClass = "org.h2.driver";
            String dbUser = "sa";
            String dbPassword = "";
            return DataSourceBuilder.create()
                    .username(dbUser)
                    .password(dbPassword)
                    .url(URLString)
                    .driverClassName(driverClass)
                    .build();
        }
    }
}
