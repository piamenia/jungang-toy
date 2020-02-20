package io.dktechin.template.controller;

import io.dktechin.template.controller.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(value = "/", description = "Swagger 테스트", tags = {"/"})
@RestController
@Slf4j
@RequestMapping(value = "/api")
public class TestController {

	// hidden=true 추가하면, 해당 API는 조회되지 않음
	@ApiOperation(value = "스웨거 테스트" , responseContainer = "ModelAndView.page")
	@RequestMapping("/test")
	public String test(){
		return "Swagger test!";
	}

	// 별도로 선언하지 않으면 파싱된 내용으로 swagger-ui 에서 표시
	// @PathVariable 파리미터는 parameterType="path"
	// @RequestParam 파라미터는 parameterType="string" 으로 설정
	@ApiImplicitParams({
		@ApiImplicitParam(name = "param1", value = "파라미터1", required = true, dataType = "string", paramType = "path", defaultValue = ""),
		@ApiImplicitParam(name = "param2", value = "파라미터2", required = true, dataType = "string", paramType = "path", defaultValue = "")
	})
	@RequestMapping("/test/{param1}/{param2}")
	public String paramTest(@PathVariable String param1, @PathVariable String param2) {
		return "Swagger param test! => " + param1 + ", " + param2;
	}

	@RequestMapping("/model")
	public String modelTest(@RequestBody SuperModel superModel) {
		return "Swagger model test! => " + superModel.toString();
	}
}
