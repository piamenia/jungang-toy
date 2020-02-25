package io.dktechin.template.converter;

import io.dktechin.common.client.sample.vo.SampleResult;
import io.dktechin.template.controller.model.SampleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface SampleConverter {
	@Mappings({
		@Mapping(source = "first_name", target = "firstName"),
		@Mapping(source = "last_name", target = "lastName")
	})
	SampleModel resultToModel(SampleResult sampleResult);
}
