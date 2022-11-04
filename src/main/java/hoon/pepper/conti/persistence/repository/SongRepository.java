package hoon.pepper.conti.persistence.repository;

import hoon.pepper.common.config.jpa.DefaultJpaRepository;
import hoon.pepper.conti.persistence.entity.SongEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends DefaultJpaRepository<SongEntity, Long>, SongRepositoryCustom{
    List<SongEntity> findByContiId(Long contiId);
    void deleteByContiId(Long contiId);
}
