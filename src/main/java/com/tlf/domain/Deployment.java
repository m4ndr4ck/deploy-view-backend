package com.tlf.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Deployment {


    @Getter private final String application;

    @Getter private final String module;

    @Getter private final String environment;

    @Getter private final String date;

    @Getter private final String status;

    public static Deployment build(
            String application,
            String module,
            String environment,
            String date,
            String status) {
        return new Deployment(application, module, environment, date, status);
    }


}
