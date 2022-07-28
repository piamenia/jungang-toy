package hoon.pepper.template.service.vo;

import hoon.pepper.common.code.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private String accountId;

	private Authority authority;

	private LocalDateTime lastLoginAt;
}
