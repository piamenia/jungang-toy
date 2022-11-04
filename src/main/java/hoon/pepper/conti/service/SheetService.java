package hoon.pepper.conti.service;

import hoon.pepper.conti.persistence.entity.SheetEntity;
import hoon.pepper.conti.persistence.repository.SheetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SheetService {
	private final SheetRepository sheetRepository;

	public List<SheetEntity> getSheetList(Long songId) {
		return sheetRepository.findBySongId(songId);
	}
}
