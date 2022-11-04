package hoon.pepper.conti.controller.admin;

import hoon.pepper.common.wrapper.ResultResponse;
import hoon.pepper.conti.controller.model.EmptyResultModel;
import hoon.pepper.conti.controller.model.FileModel;
import hoon.pepper.conti.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/file")
@RequiredArgsConstructor
@Api(tags = "관리자 파일 컨트롤러")
public class AdminFileController {
	private final FileService fileService;

	@PostMapping("/upload")
	@ApiOperation(value="파일 업로드", httpMethod = "POST", produces = "multipart/form-data", notes = "")
	public ResultResponse<List<FileModel>> postFile(@RequestPart(value="fileList") MultipartFile[] fileList) {
		return new ResultResponse<>(fileService.uploadFile(fileList));
	}

	@DeleteMapping("/remove")
	@ApiOperation(value="파일 삭제", notes = "")
	public ResultResponse<EmptyResultModel> deleteFile(@RequestBody List<Long> fileIdList) {
		fileService.removeFiles(fileIdList);
		return new ResultResponse<>(new EmptyResultModel());
	}

	@GetMapping("/downloadUrl")
	@ApiOperation(value="임시 url 생성하기")
	public ResultResponse<String> getDownloadUrl(@RequestParam String key) {
		return new ResultResponse<>(fileService.getDownloadUrl(key));
	}
}
