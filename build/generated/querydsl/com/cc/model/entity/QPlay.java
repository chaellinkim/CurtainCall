package com.cc.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlay is a Querydsl query type for Play
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlay extends EntityPathBase<Play> {

    private static final long serialVersionUID = 1087775609L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlay play = new QPlay("play");

    public final NumberPath<Long> count = createNumber("count", Long.class);

    public final QPlace place;

    public final StringPath placeId = createString("placeId");

    public final StringPath playAge = createString("playAge");

    public final StringPath playFrom = createString("playFrom");

    public final StringPath playId = createString("playId");

    public final StringPath playPoster = createString("playPoster");

    public final StringPath playPrice = createString("playPrice");

    public final StringPath playTime = createString("playTime");

    public final StringPath playTitle = createString("playTitle");

    public final StringPath playTo = createString("playTo");

    public QPlay(String variable) {
        this(Play.class, forVariable(variable), INITS);
    }

    public QPlay(Path<? extends Play> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlay(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlay(PathMetadata metadata, PathInits inits) {
        this(Play.class, metadata, inits);
    }

    public QPlay(Class<? extends Play> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.place = inits.isInitialized("place") ? new QPlace(forProperty("place")) : null;
    }

}

