package io.dktechin.common.config.jpa;

import com.zaxxer.hikari.HikariDataSource;
import io.dktechin.common.config.datasource.DatabaseSourceConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.vault.core.VaultOperations;

import javax.sql.DataSource;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
	basePackages = {
		"io.dktechin.**.repository"
	}
)
public class RepositoryConfig {

	// vault config
	@Autowired
	private DatabaseSourceConfig databaseSourceConfig;

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(EntityUtil.generateDDL(commonJpaProperty()));
		jpaVendorAdapter.setShowSql(EntityUtil.showSQL(commonJpaProperty()));

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(databaseSourceConfig.dataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan(RepositoryConfig.class.getPackage().getName(), "io.dktechin.**");
		factoryBean.setJpaProperties(commonJpaProperty().getProperties());

		return factoryBean;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.jpa")
	JpaProperty commonJpaProperty(){
		return new JpaProperty();
	}
}
