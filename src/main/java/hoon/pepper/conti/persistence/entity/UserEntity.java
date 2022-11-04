package hoon.pepper.conti.persistence.entity;

import hoon.pepper.common.code.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@Entity
@Slf4j
@DynamicInsert
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

	@Id
	@Column(nullable = false, updatable = false, length = 20)
	private String accountId;

	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private Authority authority;

	public UserEntity(String accountId) {
		this.accountId = accountId;
		this.authority = Authority.USER;
	}
}
