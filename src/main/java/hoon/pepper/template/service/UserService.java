package hoon.pepper.template.service;

import hoon.pepper.template.persistence.entity.UserEntity;
import hoon.pepper.template.persistence.repository.UserRepository;
import hoon.pepper.template.converter.UserConverter;
import hoon.pepper.template.service.vo.User;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

	private UserRepository userRepository;

	private UserConverter userConverter;

	@Transactional(readOnly = true)
	public User get(String accountId) {
		UserEntity userEntity = userRepository.findById(accountId)
			.orElse(null);
		User user = userConverter.converts(userEntity);
		return user;
	}

	@Transactional
	public User insert(String accountId) {
		UserEntity userEntity = new UserEntity(accountId);
		userEntity = userRepository.save(userEntity);
		User user = userConverter.converts(userEntity);
		return user;
	}
}
