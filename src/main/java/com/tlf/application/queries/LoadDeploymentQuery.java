package com.tlf.application.queries;

import com.tlf.domain.Deployment;
import com.tlf.framework.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface LoadDeploymentQuery {


    List<Deployment> loadDeployment(LoadDeploymentCommand deploymentCommand);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class LoadDeploymentCommand  extends SelfValidating<LoadDeploymentCommand> {

        @NotNull
        private final String application;

        public LoadDeploymentCommand(String application) {
            this.application = application;
            this.validateSelf();
        }
    }

}
