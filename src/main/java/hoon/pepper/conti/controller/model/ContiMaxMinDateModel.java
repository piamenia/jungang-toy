package hoon.pepper.conti.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "콘티 목록 모델")
public class ContiMaxMinDateModel {

	@ApiModelProperty("콘티 최소일")
	private LocalDate minContiDate;

	@ApiModelProperty("콘티 최대일")
	private LocalDate maxContiDate;
}
