package com.broadcom.report_generator.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
    @JsonProperty("forbidOnly")
    private boolean forbidOnly;
    @JsonProperty("fullyParallel")
    private boolean fullyParallel;
    @JsonProperty("globalSetup")
    private Object globalSetup;
    @JsonProperty("globalTeardown")
    private Object globalTeardown;
    @JsonProperty("globalTimeout")
    private int globalTimeout;
    @JsonProperty("grep")
    private Object grep;
    @JsonProperty("grepInvert")
    private Object grepInvert;
    @JsonProperty("maxFailures")
    private int maxFailures;
    @JsonProperty("metadata")
    private Object metadata;
    @JsonProperty("preserveOutput")
    private String preserveOutput;
    @JsonProperty("projects")
    private List<Project> projects;
    // @JsonProperty("reporter")
    // private List<Reporter> reporter;
    @JsonProperty("reportSlowTests")
    private ReportSlowTests reportSlowTests;
    @JsonProperty("configFile")
    private String configFile;
    @JsonProperty("rootDir")
    private String rootDir;
    @JsonProperty("quiet")
    private boolean quiet;
    @JsonProperty("shard")
    private Object shard;
    @JsonProperty("updateSnapshots")
    private String updateSnapshots;
    @JsonProperty("version")
    private String version;
    @JsonProperty("workers")
    private int workers;
    @JsonProperty("webServer")
    private WebServer webServer;
}
