package hoon.pepper.common.client.sample.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SampleResult {
	private String id;
	private String email;
	private String first_name;
	private String last_name;
	private String avatar;
}
