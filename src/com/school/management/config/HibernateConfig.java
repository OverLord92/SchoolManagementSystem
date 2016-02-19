package com.school.management.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:com/school/management/properties/hibernate.properties")
public class HibernateConfig {

	@Autowired
	Environment env;

	@Bean
	public DataSource dataSource(){
		
		JndiDataSourceLookup jndi = new JndiDataSourceLookup();
		DataSource dataSource = jndi.getDataSource("jdbc/school");
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource());
		
		sessionFactory.setPackagesToScan(new String[]{ "com.school.management.model" });
		
		Properties hibernateProps = new Properties();
		hibernateProps.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProps.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl"));
		hibernateProps.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		sessionFactory.setHibernateProperties(hibernateProps);
		
		return sessionFactory;
	}
	
	@Bean 
	public DataSourceTransactionManager transactionManager(){
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
	
		dstm.setDataSource(dataSource());
		return dstm;
	}
	
	@Bean
	PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
		PersistenceExceptionTranslationPostProcessor exceptionTranslator =
				new PersistenceExceptionTranslationPostProcessor();
		
		return exceptionTranslator;
	}
	
}
