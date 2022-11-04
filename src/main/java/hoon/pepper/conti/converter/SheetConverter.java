package hoon.pepper.conti.converter;

import hoon.pepper.conti.controller.model.SheetModel;
import hoon.pepper.conti.persistence.entity.SheetEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface SheetConverter {
	List<SheetModel> converts(List<SheetEntity> sheetEntityList);
}
