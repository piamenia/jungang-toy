package hoon.pepper.conti.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongModel {
	@ApiModelProperty(value = "찬양 ID", example = "1")
	private Long songId;
	@ApiModelProperty(value = "제목", example = "")
	private String title;
	@ApiModelProperty(value = "찬양 순서")
	private Integer songOrder;
	@ApiModelProperty(value = "유튜브 링크")
	private String link;
	@ApiModelProperty(value = "악보 목록")
	private List<SheetModel> sheetList;
}
