package com.cc.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserPlay is a Querydsl query type for UserPlay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPlay extends EntityPathBase<UserPlay> {

    private static final long serialVersionUID = 1509817188L;

    public static final QUserPlay userPlay = new QUserPlay("userPlay");

    public final StringPath merchantUid = createString("merchantUid");

    public final StringPath playId = createString("playId");

    public final StringPath playTitle = createString("playTitle");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserPlay(String variable) {
        super(UserPlay.class, forVariable(variable));
    }

    public QUserPlay(Path<? extends UserPlay> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPlay(PathMetadata metadata) {
        super(UserPlay.class, metadata);
    }

}

