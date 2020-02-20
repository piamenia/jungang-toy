package io.dktechin.template.controller.model;

import io.dktechin.common.code.Authority;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserModel {

	@ApiModelProperty(value = "사용자 ID", example = "breeze.hwi")
	private String accountId;

	@ApiModelProperty(value = "권한", example = "USER")
	private Authority authority;

	@ApiModelProperty(value = "사용자명", example = "김정휘")
	private String name;

	@ApiModelProperty(value = "부서명", example = "플랫폼 개발팀")
	private String departmentName;

	@ApiModelProperty(value = "사용자 프로필사진 URL", example = "http://www.kakao.com/f98ewf98wf")
	private String imgUrl;
}
