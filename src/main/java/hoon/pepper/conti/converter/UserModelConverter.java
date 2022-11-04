package hoon.pepper.conti.converter;

import hoon.pepper.conti.controller.model.UserModel;
import hoon.pepper.conti.service.vo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModelConverter {
	UserModel converts(User vo);
}
