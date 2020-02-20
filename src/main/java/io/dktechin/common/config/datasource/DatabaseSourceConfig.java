package io.dktechin.common.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.vault.core.VaultOperations;

import javax.sql.DataSource;

/**
 * DatabaseSourceConfig
 * <pre>
 * Describe here
 * </pre>
 *
 * <pre>
 * <b>History:</b>
 * lia.jung, 1.0, 2020/02/11 초기작성
 * </pre>
 *
 * @author lia.jung
 * @version 1.0,
 */

@Configuration
public class DatabaseSourceConfig {

//	@Autowired
//	private VaultOperations vaultOperations;

//	@Value("${vault.path}")
//	private String vaultPath;

	@Value("${datasource.host}")
	private String host;
	@Value("${datasource.port}")
	private String port;
	@Value("${datasource.database}")
	private String database;
	@Value("${datasource.username}")
	private String username;
	@Value("${datasource.password}")
	private String password;

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.hikari")
	public DataSource dataSource() {
//		DatabaseSourceDto dto = vaultOperations.read(vaultPath, DatabaseSourceDto.class).getData();
		return DataSourceBuilder
			.create()
			.type(HikariDataSource.class)
			.driverClassName("com.mysql.cj.jdbc.Driver")
			.url(
				String.format(
					"jdbc:mysql://%s:%s/%s?serverTimezone=UTC",
					host,
					port,
					database
				)
			)
			.username(username) // username
			.password(password) // password
			.build();
	}

}
