package hoon.pepper.conti.converter;

import hoon.pepper.conti.controller.model.SongModel;
import hoon.pepper.conti.controller.model.request.SongRequest;
import hoon.pepper.conti.persistence.entity.SongEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface SongConverter {
	List<SongModel> converts(List<SongEntity> songEntityList);

	SongEntity converts(SongRequest songRequest);
}
