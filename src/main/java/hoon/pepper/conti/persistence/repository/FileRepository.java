package hoon.pepper.conti.persistence.repository;

import hoon.pepper.common.config.jpa.DefaultJpaRepository;
import hoon.pepper.conti.persistence.entity.FileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends DefaultJpaRepository<FileEntity, Long>, FileRepositoryCustom{
    void deleteAllByFileIdIn(List<Long> sheetIdList);
    List<FileEntity> findByFileIdIn(List<Long> fileIdList);
}
