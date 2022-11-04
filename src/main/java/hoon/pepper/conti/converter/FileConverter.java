package hoon.pepper.conti.converter;

import hoon.pepper.conti.controller.model.FileModel;
import hoon.pepper.conti.persistence.entity.FileEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface FileConverter {
	List<FileModel> converts(List<FileEntity> fileEntityList);
}
