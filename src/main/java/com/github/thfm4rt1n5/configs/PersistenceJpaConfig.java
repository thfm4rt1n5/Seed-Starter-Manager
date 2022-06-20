package com.github.thfm4rt1n5.configs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
public class PersistenceJpaConfig {
	private final static String[] PACKAGES_TO_SCAN = { "com.github.thfm4rt1n5.entity" };

	private final Environment environment;

	public PersistenceJpaConfig(Environment environment) {
		this.environment = environment;
	}

	@Bean
	DataSource dataSource() {
		var dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));

		return dataSource;
	}

	@Bean
	HibernateJpaVendorAdapter jpaVendorAdapter() {
		var jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(environment.getProperty("spring.jpa.show-sql", Boolean.class));
		jpaVendorAdapter.setShowSql(environment.getProperty("spring.jpa.generate-ddl", Boolean.class));

		return jpaVendorAdapter;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		var managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		managerFactoryBean.setDataSource(dataSource());
		managerFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
		managerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());

		return managerFactoryBean;
	}

	@Bean
	PlatformTransactionManager transactionManager() {
		var transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
