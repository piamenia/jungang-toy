package hoon.pepper.conti.converter;

import hoon.pepper.conti.controller.model.ContiListModel;
import hoon.pepper.conti.controller.model.request.ContiRequest;
import hoon.pepper.conti.persistence.entity.ContiEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ContiConverter {
	ContiListModel converts(ContiListModel contiListModel);
	ContiEntity converts(ContiRequest contiRequest);
}
