package org.itmo.spacemarine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;


@Configuration
@EnableWebMvc
public class GeneralConfiguration {
//    private static final String JNDI = "jdbc/soa";
//
//    @Bean(destroyMethod = "") // disable inference of a potential close() method as a destroyer
//    public DataSource dataSource() throws DataSourceLookupFailureException {
//        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//        return dataSourceLookup.getDataSource(JNDI);
//    }


}
