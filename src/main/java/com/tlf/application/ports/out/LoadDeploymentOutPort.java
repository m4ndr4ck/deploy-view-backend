package com.tlf.application.ports.out;

import com.tlf.domain.Deployment;

import java.util.List;

public interface LoadDeploymentOutPort {

    List<Deployment> loadDeployment(String application);

}
