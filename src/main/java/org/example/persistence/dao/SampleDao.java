package org.example.persistence.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class SampleDao extends BaseDao<Long> {
    private String data;
}
