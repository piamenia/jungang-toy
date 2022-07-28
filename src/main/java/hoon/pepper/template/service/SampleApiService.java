package hoon.pepper.template.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hoon.pepper.common.client.sample.SampleHandler;
import hoon.pepper.common.client.sample.vo.SampleResult;
import hoon.pepper.common.client.sample.vo.SampleVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SampleApiService {
	private ObjectMapper mapper;
	private SampleHandler sampleHandler;

	public SampleResult selectSample(String param) {
		SampleVO sampleVO = new SampleVO();
		try {
			Map<String,String> map = new HashMap<>();
			map.put("param", param);
			String response = sampleHandler.selectSample(map);
			log.info("response: " + response);
			// response 파싱
			Gson gson = new Gson();
			JsonParser jsonParser = new JsonParser();
			JsonObject responseObject = (JsonObject) jsonParser.parse(response);
			JsonObject dataObject = (JsonObject) responseObject.get("data");
			log.info("dataObject: " + dataObject.toString());
			SampleResult sampleResult = gson.fromJson(dataObject, SampleResult.class);
			log.info("sampleResult: " + sampleResult.toString());
			sampleVO.setResult(sampleResult);
			log.info("sampleVO: " + sampleVO.toString());
		} catch (Exception e) {
			log.error("Exception: ", e);
		}
		return sampleVO.getResult();
	}
}
