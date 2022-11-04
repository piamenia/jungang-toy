package hoon.pepper.conti.converter;

import hoon.pepper.conti.controller.model.UserModel;
import hoon.pepper.conti.persistence.entity.UserEntity;
import hoon.pepper.conti.service.vo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserConverter {

	@Mappings({
	})
	User converts(UserEntity entity);

	UserModel converts(User user);
}
