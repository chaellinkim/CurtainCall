package com.cc.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QActor is a Querydsl query type for Actor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActor extends EntityPathBase<Actor> {

    private static final long serialVersionUID = -652797360L;

    public static final QActor actor = new QActor("actor");

    public final StringPath actorBirth = createString("actorBirth");

    public final NumberPath<Long> actorId = createNumber("actorId", Long.class);

    public final StringPath actorImg = createString("actorImg");

    public final StringPath actorName = createString("actorName");

    public final StringPath actorSNS = createString("actorSNS");

    public QActor(String variable) {
        super(Actor.class, forVariable(variable));
    }

    public QActor(Path<? extends Actor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActor(PathMetadata metadata) {
        super(Actor.class, metadata);
    }

}

