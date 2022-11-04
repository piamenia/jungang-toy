package hoon.pepper.conti.controller.user;

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
@RequestMapping("/user/file")
@RequiredArgsConstructor
@Api(tags = "파일 컨트롤러")
public class FileController {
	private final FileService fileService;

	@GetMapping("/downloadUrl")
	@ApiOperation(value="임시 url 생성하기")
	public ResultResponse<String> getDownloadUrl(@RequestParam String key) {
		return new ResultResponse<>(fileService.getDownloadUrl(key));
	}

}
