package hoon.pepper.conti.persistence.repository;

import hoon.pepper.conti.controller.model.SheetModel;

import java.util.List;

public interface SheetRepositoryCustom {
    List<SheetModel> getSheetModelBySongId(Long songId);
}
