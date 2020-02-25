package io.dktechin.common.client.sample;

import io.dktechin.common.client.sample.vo.SampleVO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.Map;

public interface SampleService {
	@GET("/api/users/2")
//	Call<String> selectSample(@Body Map<String,String> body);
	Call<String> selectSample();
}
