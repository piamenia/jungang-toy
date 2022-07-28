package hoon.pepper.common.client.sample;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SampleService {
	@GET("/api/users/2")
//	Call<String> selectSample(@Body Map<String,String> body);
	Call<String> selectSample();
}
