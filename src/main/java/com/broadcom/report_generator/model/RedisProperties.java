package com.broadcom.report_generator.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "redis")
@Getter
@Setter
public class RedisProperties {
    private String host;
    private String port;
    private String username;
    private String password;
}
