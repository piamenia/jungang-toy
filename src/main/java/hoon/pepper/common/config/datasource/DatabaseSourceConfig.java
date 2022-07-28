package hoon.pepper.common.config.datasource;

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
 * pepper.hoon, 1.0, 2020/02/20 인트라넷 소스에서 가져옴
 * pepper.hoon, 1.1, 2020/02/20 템플릿프로젝트는 vault를 제외하므로 datasource 정보를 직접 설정
 * </pre>
 *
 * @author pepper.hoon
 * @version 1.1,
 */
@Configuration
public class DatabaseSourceConfig {

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
		return DataSourceBuilder
			.create()
			.type(HikariDataSource.class)
			.driverClassName("com.mysql.cj.jdbc.Driver")
			.url(
				String.format(
					"jdbc:mysql://%s:%s/%s?serverTimezone=UTC&verifyServerCertificate=false&useSSL=false&requireSSL=false&allowPublicKeyRetrieval=true",
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
