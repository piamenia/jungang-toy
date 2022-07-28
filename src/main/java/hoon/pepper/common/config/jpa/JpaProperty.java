package hoon.pepper.common.config.jpa;

import lombok.Data;

import java.util.Properties;

@Data
public class JpaProperty {
	private Properties properties = new Properties();
}
