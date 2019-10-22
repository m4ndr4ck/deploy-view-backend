package com.tlf.framework.adapters.out.integration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class DeploymentJSONEntity {

    private String application;

    private String module;

    private String version;

    private String environment;

    private String date;

    private String status;

}