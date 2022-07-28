package hoon.pepper.common.config.jpa;

import hoon.pepper.common.config.datasource.DatabaseSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
	basePackages = {
		"hoon.pepper.**.repository"
	}
)
@ComponentScan({"hoon.pepper.common.config.datasource"})
public class RepositoryConfig {

	// datasource config
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
		factoryBean.setPackagesToScan(RepositoryConfig.class.getPackage().getName(), "hoon.pepper.**");
		factoryBean.setJpaProperties(commonJpaProperty().getProperties());

		return factoryBean;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.jpa")
	JpaProperty commonJpaProperty(){
		return new JpaProperty();
	}
}
