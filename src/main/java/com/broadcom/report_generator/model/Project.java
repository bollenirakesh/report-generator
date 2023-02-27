package com.broadcom.report_generator.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Project {
    @JsonProperty("outputDir")
    private String outputDir;
    @JsonProperty("repeatEach")
    private int repeatEach;
    @JsonProperty("retries")
    private int retries;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("testDir")
    private String testDir;
    @JsonProperty("testIgnore")
    private List<Object> testIgnore;
    @JsonProperty("testMatch")
    private List<String> testMatch;
    @JsonProperty("timeout")
    private int timeout;
}
