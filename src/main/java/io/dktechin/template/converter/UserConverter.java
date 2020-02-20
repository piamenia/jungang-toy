package io.dktechin.template.converter;

import io.dktechin.template.persistence.entity.UserEntity;
import io.dktechin.template.service.vo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserConverter {

	@Mappings({
		@Mapping(target = "name", ignore = true),
		@Mapping(target = "departmentName", ignore = true),
		@Mapping(target = "imgUrl", ignore = true),
	})
	User converts(UserEntity entity);
}
