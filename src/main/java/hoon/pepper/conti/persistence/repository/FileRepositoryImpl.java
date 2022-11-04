package hoon.pepper.conti.persistence.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hoon.pepper.conti.controller.model.FileModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static hoon.pepper.conti.persistence.entity.QFileEntity.fileEntity;

@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private BooleanBuilder where;

    @Override
    public List<FileModel> getFiles(List<Long> fileIdList) {
        return queryFactory
            .select(Projections.fields(FileModel.class,
                fileEntity.fileId,
                fileEntity.fileAccessKey
                ))
            .from(fileEntity)
            .where(fileEntity.fileId.in(fileIdList))
            .fetch();
    }
}
