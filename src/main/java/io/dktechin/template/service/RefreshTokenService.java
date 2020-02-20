package io.dktechin.template.service;

import io.dktechin.common.exception.EmptyDataException;
import io.dktechin.template.persistence.entity.RefreshTokenEntity;
import io.dktechin.template.persistence.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RefreshTokenService {

	private RefreshTokenRepository refreshTokenRepository;

	@Transactional(readOnly = true)
	public String getRefreshToken(String accountId) {
		RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findById(accountId)
			.orElseThrow(() -> new EmptyDataException("Not found RefreshToken"));

		return refreshTokenEntity.getRefreshToken();
	}

	@Transactional
	public void save(String accountId, String refreshToken) {
		RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
		refreshTokenEntity.setAccountId(accountId);
		refreshTokenEntity.setRefreshToken(refreshToken);

		refreshTokenRepository.save(refreshTokenEntity);
	}
}
