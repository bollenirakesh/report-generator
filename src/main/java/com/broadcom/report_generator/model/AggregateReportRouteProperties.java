package com.broadcom.report_generator.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aggregate-report-route")
@Getter
@Setter
public class AggregateReportRouteProperties {
    private String rootDirectory;
    private String backupDirectory;
    private String targetDirectory;
    private String redisHost;
    private String redisPort;
}
