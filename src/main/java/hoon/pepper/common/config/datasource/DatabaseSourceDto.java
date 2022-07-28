package hoon.pepper.common.config.datasource;

import lombok.Getter;
import lombok.Setter;

/**
 * DatabaseSourceDto
 * <pre>
 * Describe here
 * </pre>
 *
 * <pre>
 * <b>History:</b>
 * lia.jung, 1.0, 2020/02/11 초기작성
 * pepper.hoon, 1.0, 2020/02/20 인트라넷 소스에서 가져옴
 * </pre>
 *
 * @author lia.jung
 * @version 1.0,
 */
@Setter
@Getter
public class DatabaseSourceDto {
	private String host;
	private Integer port;
	private String database;
	private String username;
	private String password;
}
