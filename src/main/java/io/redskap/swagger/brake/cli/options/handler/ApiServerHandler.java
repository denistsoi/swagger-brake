package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ApiServerHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (!StringUtils.isBlank(propertyValue)) {
            // TODO: do schema check
            options.setApiServer(propertyValue);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.API_SERVER;
    }

    @Override
    public String getHelpMessage() {
        return "Sets the api-server URL to upload the results";
    }
}