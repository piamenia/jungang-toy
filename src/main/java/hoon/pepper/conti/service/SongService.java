package hoon.pepper.conti.service;

import hoon.pepper.conti.persistence.entity.SongEntity;
import hoon.pepper.conti.persistence.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class SongService {
	private final SongRepository songRepository;

	public List<SongEntity> getSongList(Long contiId) {
		return songRepository.findByContiId(contiId);
	}
}
