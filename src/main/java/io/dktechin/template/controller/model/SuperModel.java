package io.dktechin.template.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SuperModel", discriminator = "foo", subTypes = {SubModel.class})
public class SuperModel {
	@ApiModelProperty(value = "검색 키워드") // swagger UI 에서  allowableValues = "0, 1, 2"
	private String keyword;

	@ApiModelProperty(hidden = true) // swagger modal 에서 제외시킨다.
	private String type;
}
