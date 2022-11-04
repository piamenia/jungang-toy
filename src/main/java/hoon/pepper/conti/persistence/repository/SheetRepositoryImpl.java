package hoon.pepper.conti.persistence.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hoon.pepper.conti.controller.model.SheetModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static hoon.pepper.conti.persistence.entity.QSheetEntity.sheetEntity;
import static hoon.pepper.conti.persistence.entity.QFileEntity.fileEntity;

@RequiredArgsConstructor
public class SheetRepositoryImpl implements SheetRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private BooleanBuilder where;

    @Override
    public List<SheetModel> getSheetModelBySongId(Long songId) {
        return queryFactory
            .select(Projections.fields(SheetModel.class,
                sheetEntity.sheetId,
                sheetEntity.sheetOrder,
                fileEntity.fileName,
                fileEntity.orgFileName,
                fileEntity.fileAccessKey,
                fileEntity.fileType,
                fileEntity.fileSize
                ))
            .from(sheetEntity)
            .join(fileEntity).on(sheetEntity.fileId.eq(fileEntity.fileId))
            .where(sheetEntity.songId.eq(songId))
            .fetch();
    }
}
