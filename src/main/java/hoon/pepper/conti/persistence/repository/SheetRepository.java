package hoon.pepper.conti.persistence.repository;

import hoon.pepper.common.config.jpa.DefaultJpaRepository;
import hoon.pepper.conti.persistence.entity.SheetEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheetRepository extends DefaultJpaRepository<SheetEntity, Long>, SheetRepositoryCustom{
    List<SheetEntity> findBySongId(Long songId);
    void deleteAllBySheetIdIn(List<Long> sheetIdList);
    void deleteAllBySongId(Long songId);
}
