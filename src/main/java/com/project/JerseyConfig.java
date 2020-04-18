package com.project;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.project");
        register(MainController.class);
        register(UserEndpoint.class);
        register(OrderController.class);
    }
}
