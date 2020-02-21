package io.dktechin.template.converter;

import io.dktechin.template.controller.model.UserModel;
import io.dktechin.template.persistence.entity.UserEntity;
import io.dktechin.template.service.vo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserConverter {

	@Mappings({
	})
	User converts(UserEntity entity);

	UserModel converts(User user);
}
