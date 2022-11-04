package hoon.pepper.conti.persistence.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hoon.pepper.conti.controller.model.ContiDetailModel;
import hoon.pepper.conti.controller.model.ContiListModel;
import hoon.pepper.conti.controller.model.request.ContiListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static hoon.pepper.conti.persistence.entity.QCategoryEntity.categoryEntity;
import static hoon.pepper.conti.persistence.entity.QContiEntity.contiEntity;

@RequiredArgsConstructor
public class ContiRepositoryImpl implements ContiRepositoryCustom {
    @Autowired
    private final JPAQueryFactory queryFactory;
    private BooleanBuilder where;
    @Override
    public Page<ContiListModel> getContiList(ContiListRequest contiListRequest, Pageable pageable) {
        this.where = new BooleanBuilder();
        if (!ObjectUtils.isEmpty(contiListRequest.getCategoryId())) {
            this.where.and(contiEntity.categoryId.eq(contiListRequest.getCategoryId()));
        }
        if (!ObjectUtils.isEmpty(contiListRequest.getDepart())) {
            this.where.and(contiEntity.depart.eq(contiListRequest.getDepart()));
        }
        QueryResults<ContiListModel> results = queryFactory
            .select(Projections.fields(ContiListModel.class,
                contiEntity.contiId,
                contiEntity.title,
                categoryEntity.categoryName,
                contiEntity.depart,
                contiEntity.date
                ))
            .from(contiEntity)
            .join(categoryEntity).on(contiEntity.categoryId.eq(categoryEntity.categoryId))
            .where(
                contiEntity.date.year().eq(contiListRequest.getYear()),
                contiEntity.date.month().eq(contiListRequest.getMonth())
            )
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(contiEntity.createdAt.desc())
            .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Optional<ContiDetailModel> getContiDetail(Long contiId) {
        return Optional.ofNullable(queryFactory
            .select(Projections.fields(ContiDetailModel.class,
                contiEntity.contiId,
                contiEntity.date,
                categoryEntity.categoryName,
                contiEntity.title,
                contiEntity.depart
                ))
            .from(contiEntity)
            .join(categoryEntity).on(contiEntity.categoryId.eq(categoryEntity.categoryId))
            .where(contiEntity.contiId.eq(contiId))
            .fetchOne()
        );
    }
}
