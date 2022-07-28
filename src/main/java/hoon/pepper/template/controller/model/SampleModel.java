package hoon.pepper.template.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SampleModel {
	@ApiModelProperty
	private String id;
	@ApiModelProperty
	private String email;
	@ApiModelProperty
	private String firstName;
	@ApiModelProperty
	private String lastName;
	@ApiModelProperty
	private String avatar;
}
