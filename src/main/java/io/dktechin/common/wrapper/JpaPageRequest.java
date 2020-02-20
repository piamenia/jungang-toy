package io.dktechin.common.wrapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class JpaPageRequest {
	/**
	 * 페이지 요청정보를 반환한다.
	 * @param offset 현재 페이지 (1부터 시작)
	 * @param limit 보여줄 데이터의 수 (0일 경우 모두 보여준다.)
	 * @return Jpa 페이지 요청 정보
	 */
	public static PageRequest of(int offset, int limit) {
		offset = offset > 0 ? offset - 1 : 0;
		limit = limit == 0 ? Integer.MAX_VALUE : limit;
		return PageRequest.of(offset, limit);
	}

	public static PageRequest of(int offset, int limit, Sort sort) {
		offset = offset > 0 ? offset - 1 : 0;
		limit = limit == 0 ? Integer.MAX_VALUE : limit;
		return PageRequest.of(offset, limit, sort);
	}
}
