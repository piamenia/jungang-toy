package io.dktechin.template.converter;

import io.dktechin.common.client.sample.vo.SampleResult;
import io.dktechin.template.controller.model.SampleModel;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SampleConverter {
	SampleModel converters(SampleResult sampleResult);
}
