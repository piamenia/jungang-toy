package io.dktechin.common.client.sample;

import io.dktechin.common.client.sample.vo.SampleVO;
import io.dktechin.common.retrofit.RetrofitExecutor;
import io.dktechin.common.retrofit.RetrofitFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SampleHandler {
	SampleService sampleService;

	/**
	 * reqres 샘플 api
	 * @param domain
//	 * @param headerItem
	 * @param factory
	 */
	@Autowired
	public SampleHandler(@Value("https://reqres.in/") String domain,
//						 @Value("") String headerItem,
						 RetrofitFactory<SampleService> factory) {
		Map<String,String> headers = new HashMap<>();
//		headers.put("headerItem", headerItem);
		sampleService = factory.createService(domain, SampleService.class, headers);
	}

	public String selectSample( Map<String,String> model ){
//		return new RetrofitExecutor<String>().executeString(sampleService.selectSample(model));
		return new RetrofitExecutor<String>().executeString(sampleService.selectSample());
	}
}
