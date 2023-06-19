package com.cc.model.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.cc.model.entity.Play;
import com.cc.model.entity.QPlay;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PlayRepositoryCustomImpl implements PlayRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
    public Page<Play> selectPossible(Pageable pageable) {;
        QPlay play = QPlay.play;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        String today = LocalDate.now().toString();
        BooleanBuilder predicate = new BooleanBuilder()
                .and(play.playTo.goe(today))
                .andNot(play.playPrice.likeIgnoreCase("%무료%"));

        List<Play> plays = queryFactory.selectFrom(play)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = queryFactory.selectFrom(play)
                .where(predicate)
                .fetchCount();

        return new PageImpl<>(plays, pageable, totalCount);
    }
	@Override
    public Page<Play> searchPossible(String keyword, Pageable pageable) {
        QPlay play = QPlay.play;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        String today = LocalDate.now().toString();
        BooleanBuilder predicate = new BooleanBuilder()
                .and(play.playTo.goe(today))
                .andNot(play.playPrice.likeIgnoreCase("%무료%"))
                .and(play.playTitle.contains(keyword));

        List<Play> plays = queryFactory.selectFrom(play)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = queryFactory.selectFrom(play)
                .where(predicate)
                .fetchCount();

        return new PageImpl<>(plays, pageable, totalCount);
    }

}

