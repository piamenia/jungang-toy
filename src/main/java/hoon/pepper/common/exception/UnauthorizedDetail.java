package hoon.pepper.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnauthorizedDetail {
	/**
	 * 인증되지 않음
	 */
	UNAUTHORIZED(40100),

	/**
	 * 로그인 실패
	 */
	LOGIN_FAIL(40101),
	/**
	 * 만료된 토큰
	 */
	EXPIRED_TOKEN(40102),
	/**
	 * 토큰 없음
	 */
	TOKEN_IS_NULL(40103),
	/**
	 * 토큰 오류
	 */
	TOKEN_PARSE_ERROR(40104),
	/**
	 * 리프레시토큰 없음
	 */
	NOT_FOUND_REFRESHTOKEN(40105),
	/**
	 * 리프레시토큰 에러
	 */
	INVALID_REFRESHTOKEN(40106),

	/** 접근 권한 없는 api */
	NOT_ACCESS_API(40107),

	/** 접근 허용되지 않은 사용자, IP (접근 제한) */
	NOT_ACCESS_ALLOWED(40110)
	;

	private int code;

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.code;
	}
}
