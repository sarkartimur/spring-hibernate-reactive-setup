package org.example.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.persistence.dao.SampleDao;
import org.hibernate.reactive.stage.Stage;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class SampleDaoRepository extends AbstractRepository<Long, SampleDao> {
    private final Stage.SessionFactory sessionFactory;
    private final Class<SampleDao> type = SampleDao.class;
}
