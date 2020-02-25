package io.dktechin.template.controller;

import io.dktechin.common.client.sample.vo.SampleResult;
import io.dktechin.common.wrapper.ResultResponse;
import io.dktechin.template.controller.model.SampleModel;
import io.dktechin.template.converter.SampleConverter;
import io.dktechin.template.service.SampleApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
