package com.cc.model.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cc.model.entity.Play;
import com.cc.model.entity.QPlay;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PlayRepositoryCustomImpl implements PlayRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
    public List<Play> selectPossible() {
        QPlay play = QPlay.play;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        String today = LocalDate.now().toString();
        BooleanBuilder predicate = new BooleanBuilder()
                .and(play.playTo.goe(today))
                .andNot(play.playPrice.likeIgnoreCase("%무료%"));

        return queryFactory.selectFrom(play)
                .where(predicate)
                .fetch();
    }

	    

}

