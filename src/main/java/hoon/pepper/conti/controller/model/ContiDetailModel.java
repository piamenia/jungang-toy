package hoon.pepper.conti.controller.model;

import hoon.pepper.common.code.Depart;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@ApiModel(value = "콘티 상세 모델")
public class ContiDetailModel {

	@ApiModelProperty(value = "콘티 ID")
	private Long contiId;

	@ApiModelProperty(value = "카테고리 명")
	private String categoryName;

	@ApiModelProperty(value = "부서 구분")
	private Depart depart;

	@ApiModelProperty(value = "콘티 날짜")
	private LocalDate date;

	@ApiModelProperty(value = "콘티 제목")
	private String title;

	@ApiModelProperty(value = "찬양 목록")
	private List<SongModel> songList;
}
