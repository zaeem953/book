package com.api.book.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondEntityManagerFactoryBean",
        basePackages = {"com.api.book.dao2"},
        transactionManagerRef = "secondTransactionManager"
)
public class DB2 {

    private final Environment environment;

    @Autowired
    public DB2(Environment environment) {
        this.environment = environment;
    }

    //datasource

    @Bean(name = "secondDatabase")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("second.datasource.url"));
        dataSource.setDriverClassName(environment.getProperty("second.datasource.driver-class-name"));
        dataSource.setUsername(environment.getProperty("second.datasource.username"));
        dataSource.setPassword(environment.getProperty("second.datasource.password"));

        return dataSource;
    }

    //entityManagerFactory

    @Bean(name = "secondEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        JpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);

        Map<String,Object> props=new HashMap<>();
        props.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.show_sql","true");
        props.put("hibernate.ddl.auto","update");
        bean.setJpaPropertyMap(props);
        bean.setPackagesToScan("com.api.book.entities");


        return bean;
    }


    //platformTransanctionManager

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager manager=new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return manager;
    }
}
