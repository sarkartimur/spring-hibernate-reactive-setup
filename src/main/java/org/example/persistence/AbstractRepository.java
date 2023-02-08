package org.example.persistence;

import org.example.persistence.dao.BaseDao;
import org.hibernate.reactive.stage.Stage;

import java.util.concurrent.CompletionStage;


public abstract class AbstractRepository<K extends Number, T extends BaseDao<K>> {
    protected abstract Stage.SessionFactory getSessionFactory();

    protected abstract Class<T> getType();

    public CompletionStage<T> save(T entity) {
        return getSessionFactory().withTransaction((session, tx) -> {
            if (entity.getId() == null) {
                return session.persist(entity).thenApply(v -> entity);
            } else {
                return session.merge(entity);
            }
        });
    }

    public CompletionStage<T> findById(K id) {
        return getSessionFactory().withTransaction((session, tx) ->
                session.find(getType(), id)
        );
    }
}
