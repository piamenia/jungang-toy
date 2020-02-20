package io.dktechin.template.converter;

import io.dktechin.template.controller.model.UserModel;
import io.dktechin.template.service.vo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModelConverter {
	UserModel converts(User vo);
}
