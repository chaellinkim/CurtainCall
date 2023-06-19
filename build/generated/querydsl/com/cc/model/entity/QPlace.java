package com.cc.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlace is a Querydsl query type for Place
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlace extends EntityPathBase<Place> {

    private static final long serialVersionUID = -638695070L;

    public static final QPlace place = new QPlace("place");

    public final NumberPath<java.math.BigDecimal> la = createNumber("la", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> lo = createNumber("lo", java.math.BigDecimal.class);

    public final StringPath placeAddr = createString("placeAddr");

    public final StringPath placeId = createString("placeId");

    public final StringPath placeLink = createString("placeLink");

    public final StringPath placeName = createString("placeName");

    public final StringPath placePhone = createString("placePhone");

    public final ListPath<Play, QPlay> playList = this.<Play, QPlay>createList("playList", Play.class, QPlay.class, PathInits.DIRECT2);

    public QPlace(String variable) {
        super(Place.class, forVariable(variable));
    }

    public QPlace(Path<? extends Place> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlace(PathMetadata metadata) {
        super(Place.class, metadata);
    }

}

