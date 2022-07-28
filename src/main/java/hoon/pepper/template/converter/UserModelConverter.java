package hoon.pepper.template.converter;

import hoon.pepper.template.controller.model.UserModel;
import hoon.pepper.template.service.vo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModelConverter {
	UserModel converts(User vo);
}
