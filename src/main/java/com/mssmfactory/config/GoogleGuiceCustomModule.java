package com.mssmfactory.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mssmfactory.dao.AccountDao;
import com.mssmfactory.dao.GenericDao;
import com.mssmfactory.restcontrollers.AccountRestController;
import com.mssmfactory.restcontrollers.RestController;

import java.sql.Connection;

public class GoogleGuiceCustomModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Connection.class).toProvider(DatabaseConnexionConfig.class);

        bind(GenericDao.class).to(AccountDao.class);
        bind(RestController.class).to(AccountRestController.class);
    }

    @Provides
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
