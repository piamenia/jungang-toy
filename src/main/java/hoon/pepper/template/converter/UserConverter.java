package hoon.pepper.template.converter;

import hoon.pepper.template.controller.model.UserModel;
import hoon.pepper.template.persistence.entity.UserEntity;
import hoon.pepper.template.service.vo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserConverter {

	@Mappings({
	})
	User converts(UserEntity entity);

	UserModel converts(User user);
}
