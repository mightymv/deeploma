package com.deeploma.bettingshop;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



@Configuration
@ConfigurationProperties(prefix = "hikari.datasource")
public class DataSourceConfig extends HikariConfig {

    @Bean
    @Primary
    public DataSource dataSource() throws SQLException {
        return new HikariDataSource(this);
    }

}