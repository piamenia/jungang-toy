package io.dktechin.common.client.sample.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SampleResult {
	private String id;
	private String email;
	private String firstName;
	private String lastName;
	private String avatar;
}
