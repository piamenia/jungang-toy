package hoon.pepper.template.controller;

import hoon.pepper.common.wrapper.ResultResponse;
import hoon.pepper.template.converter.SampleConverter;
import hoon.pepper.common.client.sample.vo.SampleResult;
import hoon.pepper.template.controller.model.SampleModel;
import hoon.pepper.template.service.SampleApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(tags = "API 연동")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SampleController {
	private SampleApiService sampleApiService;
	private SampleConverter sampleConverter;

	@GetMapping("/sample/selectSample")
	@ApiOperation(value="", notes="")
//	@ApiImplicitParams(
//		@ApiImplicitParam(name="", value="", required = false,
//		dataType = "string", paramType="header", defaultValue = "")
//	)
	public ResultResponse<SampleModel> selectSample() {
		String param = "";
		SampleResult contents = sampleApiService.selectSample(param);
		SampleModel result = sampleConverter.resultToModel(contents);

		return new ResultResponse<>(result);
	}
}
