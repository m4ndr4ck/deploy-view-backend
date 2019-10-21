package com.tlf.framework.adapters.in.rest;

import com.tlf.application.queries.LoadDeploymentQuery;
import com.tlf.application.queries.LoadDeploymentQuery.LoadDeploymentCommand;
import com.tlf.domain.Deployment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class LoadDeploymentAdapter {

    private final LoadDeploymentQuery loadDeploymentQuery;

    @GetMapping(path = "/deployments/get/{application}")
    List<Deployment> loadDeployment(
            @PathVariable("application") String application) {

        LoadDeploymentCommand command = new LoadDeploymentCommand(application);

        return loadDeploymentQuery.loadDeployment(command);
    }

}