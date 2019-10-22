package com.tlf.application.ports.in;

import com.tlf.application.ports.out.LoadDeploymentOutPort;
import com.tlf.application.queries.LoadDeploymentQuery;
import com.tlf.domain.Deployment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
@Transactional
public class LoadDeploymentInPort implements LoadDeploymentQuery {

    private final LoadDeploymentOutPort loadDeploymentOutPort;

    @Override
    public List<Deployment> loadDeployment(LoadDeploymentCommand deploymentCommand){
        return loadDeploymentOutPort.loadDeployment(
                deploymentCommand.getApplication(),
                deploymentCommand.getMinDate(),
                deploymentCommand.getMaxDate());
    }

}