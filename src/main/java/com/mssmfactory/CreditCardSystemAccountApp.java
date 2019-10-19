package com.mssmfactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mssmfactory.config.GoogleGuiceCustomModule;
import com.mssmfactory.restcontrollers.RestController;
import spark.Spark;

public class CreditCardSystemAccountApp {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new GoogleGuiceCustomModule());

        CreditCardSystemAccountApp.initServer();
        CreditCardSystemAccountApp.initRestControllers(injector);
    }

    public static void initServer() {
        Spark.port(8080);
    }

    public static void initRestControllers(Injector injector) {
        RestController restController = injector.getInstance(RestController.class);
        restController.initEndPoints();
    }
}
