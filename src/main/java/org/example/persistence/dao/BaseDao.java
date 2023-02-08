package org.example.persistence.dao;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public abstract class BaseDao<K extends Number> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private K id;
}
