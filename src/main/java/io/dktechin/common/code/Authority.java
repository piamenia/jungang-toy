package io.dktechin.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority implements CodeInfo {
	USER("일반 사용자 권한"),
	ADMIN("관리자 권한");

	private String description;
}
