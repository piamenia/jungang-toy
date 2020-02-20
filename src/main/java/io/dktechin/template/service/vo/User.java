package io.dktechin.template.service.vo;

import io.dktechin.common.code.Authority;
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

	private String name;

	private String departmentName;

	private String imgUrl;

	private LocalDateTime lastLoginAt;
}
