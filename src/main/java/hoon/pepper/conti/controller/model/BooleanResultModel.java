package hoon.pepper.conti.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BooleanResultModel {
	@ApiModelProperty
	private Boolean result;

	public BooleanResultModel(Boolean result) {
		this.result = result;
	}
}
