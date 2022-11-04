package hoon.pepper.conti.persistence.repository;

import hoon.pepper.conti.controller.model.ContiDetailModel;
import hoon.pepper.conti.controller.model.ContiListModel;
import hoon.pepper.conti.controller.model.request.ContiListRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContiRepositoryCustom {
    Page<ContiListModel> getContiList(ContiListRequest contiListRequest, Pageable pageable);
    Optional<ContiDetailModel> getContiDetail(Long contiId);
}
