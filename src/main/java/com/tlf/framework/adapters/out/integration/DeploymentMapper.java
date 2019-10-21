package com.tlf.framework.adapters.out.integration;

import com.tlf.domain.Deployment;
import org.springframework.stereotype.Component;

@Component
public class DeploymentMapper {

    Deployment mapToDomainEntity(DeploymentJSONEntity deployment){
        return Deployment.build(
                deployment.getApplication(),
                deployment.getModule(),
                deployment.getEnvironment(),
                deployment.getDate(),
                deployment.getStatus());
    }

}
