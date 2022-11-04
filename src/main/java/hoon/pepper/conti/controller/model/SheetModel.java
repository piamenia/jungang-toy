package hoon.pepper.conti.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SheetModel {
	@ApiModelProperty(value = "악보 ID", example = "1")
	private Long sheetId;
	@ApiModelProperty(value = "업로드파일명", example = "item.jpg")
	private String fileName;
	@ApiModelProperty(value = "원본파일명", example = "example.jpg")
	private String orgFileName;
	@ApiModelProperty(value = "파일 유형", example = "jpg")
	private String fileType;
	@ApiModelProperty(value = "파일 크기", example = "5000")
	private Long fileSize;
	@ApiModelProperty(value = "다운로드 URL", example = "")
	private String downloadUrl;
	@ApiModelProperty(value = "file access key", example = "")
	private String fileAccessKey;
	@ApiModelProperty(value = "file path")
	private String filePath;
	@ApiModelProperty(value = "악보 순서")
	private Integer sheetOrder;
}
