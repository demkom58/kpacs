package com.aurosks.kpac_project.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.MySqlDialect;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.NonNull;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableJdbcRepositories(basePackages = "com.aurosks.kpac_project.repository")
@PropertySource("classpath:application.properties")
public class DataJdbcConfiguration extends AbstractJdbcConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DataJdbcConfiguration.class);

    @Bean
    public HikariDataSource dataSource(Environment env) {
        final HikariConfig hikariConfig = hikariConfig(env);
        log.info("Creating a Hikari Pooled datasource for {} to {}",
                hikariConfig.getDriverClassName(), hikariConfig.getJdbcUrl());
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    private static HikariConfig hikariConfig(Environment env) {
        final HikariConfig conf = new HikariConfig();

        if (env.containsProperty("jdbc.driverClassName"))
            conf.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        if (env.containsProperty("jdbc.url"))
            conf.setJdbcUrl(env.getProperty("jdbc.url"));
        if (env.containsProperty("jdbc.username"))
            conf.setUsername(env.getProperty("jdbc.username"));
        if (env.containsProperty("jdbc.password"))
            conf.setPassword(env.getProperty("jdbc.password"));

        if (env.containsProperty("jdbc.maximumPoolSize"))
            conf.setMaximumPoolSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("jdbc.maximumPoolSize"))));
        if (env.containsProperty("jdbc.minimumIdle"))
            conf.setMinimumIdle(Integer.parseInt(Objects.requireNonNull(env.getProperty("jdbc.minimumIdle"))));
        if (env.containsProperty("jdbc.idleTimeout"))
            conf.setIdleTimeout(Long.parseLong(Objects.requireNonNull(env.getProperty("jdbc.idleTimeout"))));
        if (env.containsProperty("jdbc.connectionTimeout"))
            conf.setConnectionTimeout(Long.parseLong(Objects.requireNonNull(env.getProperty("jdbc.connectionTimeout"))));
        if (env.containsProperty("jdbc.maxLifetime"))
            conf.setMaxLifetime(Long.parseLong(Objects.requireNonNull(env.getProperty("jdbc.maxLifetime"))));
        if (env.containsProperty("jdbc.connectionTestQuery"))
            conf.setConnectionTestQuery(env.getProperty("jdbc.connectionTestQuery"));
        if (env.containsProperty("jdbc.poolName"))
            conf.setPoolName(env.getProperty("jdbc.poolName"));
        if (env.containsProperty("jdbc.autoCommit"))
            conf.setAutoCommit(Boolean.parseBoolean(env.getProperty("jdbc.autoCommit")));
        if (env.containsProperty("jdbc.readOnly"))
            conf.setReadOnly(Boolean.parseBoolean(env.getProperty("jdbc.readOnly")));
        if (env.containsProperty("jdbc.registerMbeans"))
            conf.setRegisterMbeans(Boolean.parseBoolean(env.getProperty("jdbc.registerMbeans")));
        if (env.containsProperty("jdbc.initializationFailTimeout"))
            conf.setInitializationFailTimeout(Long.parseLong(Objects.requireNonNull(env.getProperty("jdbc.initializationFailTimeout"))));
        if (env.containsProperty("jdbc.isolateInternalQueries"))
            conf.setIsolateInternalQueries(Boolean.parseBoolean(env.getProperty("jdbc.isolateInternalQueries")));
        if (env.containsProperty("jdbc.allowPoolSuspension"))
            conf.setAllowPoolSuspension(Boolean.parseBoolean(env.getProperty("jdbc.allowPoolSuspension")));

        return conf;
    }

    @Override
    public @NonNull Dialect jdbcDialect(@NonNull NamedParameterJdbcOperations operations) {
        return MySqlDialect.INSTANCE;
    }
}
