package com.mssmfactory.dao;

import org.jooq.Record;

public interface GenericDao<T> {

    String getTableName();
    String getIdColumnName();

    T fromRecord(Record record);
}
