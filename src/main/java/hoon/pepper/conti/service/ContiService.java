package hoon.pepper.conti.service;

import hoon.pepper.common.exception.EmptyDataException;
import hoon.pepper.conti.controller.model.*;
import hoon.pepper.conti.controller.model.request.ContiListRequest;
import hoon.pepper.conti.controller.model.request.ContiRequest;
import hoon.pepper.conti.controller.model.request.SheetRequest;
import hoon.pepper.conti.converter.ContiConverter;
import hoon.pepper.conti.converter.FileConverter;
import hoon.pepper.conti.converter.SheetConverter;
import hoon.pepper.conti.converter.SongConverter;
import hoon.pepper.conti.persistence.entity.*;
import hoon.pepper.conti.persistence.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContiService {
	private final ContiRepository contiRepository;
	private final ContiConverter contiConverter;
	private final SongConverter songConverter;
	private final SongRepository songRepository;
	private final SheetRepository sheetRepository;
	private final SheetConverter sheetConverter;
	private final FileService fileService;
	private final FileRepository fileRepository;
	private final FileConverter fileConverter;

	@Transactional(readOnly = true)
	public Page<ContiListModel> getContiList(ContiListRequest contiListRequest, Pageable pageable) {
		Page<ContiListModel> page = contiRepository.getContiList(contiListRequest, pageable);
		page.getContent().forEach(conti -> {
			conti.setSongTitleList(songRepository.findByContiId(conti.getContiId()).stream().map(song -> song.getTitle()).collect(Collectors.toList()));
		});
		return page;
	}

	@Transactional(readOnly = true)
	public ContiDetailModel getContiDetail(Long contiId) {
		ContiDetailModel contiDetailModel = contiRepository.getContiDetail(contiId).orElseThrow(() -> new EmptyDataException("conti not present"));
		List<SongModel> songModelList = songConverter.converts(songRepository.findByContiId(contiId));
		songModelList.forEach(song -> {
			List<SheetModel> sheetModelList = sheetRepository.getSheetModelBySongId(song.getSongId());
			sheetModelList.forEach(sheet -> {
				sheet.setDownloadUrl(fileService.getDownloadUrl(sheet.getFileAccessKey()));
			});
			song.setSheetList(sheetModelList);
		});
		contiDetailModel.setSongList(songModelList);
		return contiDetailModel;
	}

	@Transactional
	public void postConti(ContiRequest conti) {
		ContiEntity contiEntity = contiRepository.save(contiConverter.converts(conti));
		conti.getSongList().forEach(song -> {
			SongEntity songEntity = songConverter.converts(song);
			songEntity.setContiId(contiEntity.getContiId());
			songRepository.save(songEntity);
			song.getSheetList().forEach(sheet -> {
				SheetEntity sheetEntity =
					SheetEntity.builder()
						.songId(songEntity.getSongId())
						.sheetOrder(sheet.getSheetOrder())
						.fileId(sheet.getFileId())
						.build();
				sheetRepository.save(sheetEntity);
			});
		});
	}

	@Transactional
	public void putConti(ContiRequest conti) {
		ContiEntity contiEntity = contiRepository.findById(conti.getContiId()).orElseThrow(() -> new EmptyDataException());
		contiEntity.setCategoryId(conti.getCategoryId());
		contiEntity.setDate(conti.getDate());
		contiEntity.setDepart(conti.getDepart());
		contiEntity.setTitle(conti.getTitle());
		contiRepository.save(contiEntity);
		// ??? ??????
		songRepository.deleteByContiId(conti.getContiId());
		conti.getSongList().forEach(song -> {
			List<SheetEntity> sheetEntityList = sheetRepository.findBySongId(song.getSongId());
			// ???????????? ??????
//			fileService.multiRemove(fileConverter.converts(fileRepository.findByFileIdIn(sheetRepository.findBySongId(song.getSongId()).stream().map(SheetEntity::getFileId).collect(Collectors.toList()))));
			// ????????????
			sheetRepository.deleteAll(sheetEntityList);
			// ?????? ??????
			SongEntity songEntity = songConverter.converts(song);
			songEntity.setContiId(conti.getContiId());
			songEntity = songRepository.save(songEntity);
			for (SheetRequest sheet : song.getSheetList()) {
				SheetEntity sheetEntity =
					SheetEntity.builder()
						.songId(songEntity.getSongId())
						.sheetOrder(sheet.getSheetOrder())
						.fileId(sheet.getFileId())
						.build();
				sheetRepository.save(sheetEntity);
			}
		});
	}

	@Transactional
	public void deleteConti(Long contiId) {
		songRepository.findByContiId(contiId).forEach(songEntity -> {
			fileService.multiRemove(fileConverter.converts(fileRepository.findByFileIdIn(sheetRepository.findBySongId(songEntity.getSongId()).stream().map(SheetEntity::getFileId).collect(Collectors.toList()))));
		});
		songRepository.deleteByContiId(contiId);
		contiRepository.deleteById(contiId);
	}

	@Transactional(readOnly = true)
	public boolean checkContiPassword(Long contiId, String password) {
		return contiRepository.findById(contiId).orElseThrow(() -> new EmptyDataException("conti not present")).getPassword().equals(password);
	}

	@Transactional(readOnly = true)
	public ContiMaxMinDateModel getContiMaxMinDate() {
		return contiRepository.getContiMaxMinDate();
	}
}
