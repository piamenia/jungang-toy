package io.dktechin.template.controller.model;

import io.dktechin.common.code.Authority;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User 모델")
public class UserModel {

	@ApiModelProperty(value = "사용자 ID", example = "breeze.hwi")
	private String accountId;

	@ApiModelProperty(value = "권한", example = "USER")
	private Authority authority;
}
