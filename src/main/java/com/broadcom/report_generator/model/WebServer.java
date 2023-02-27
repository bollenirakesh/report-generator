package com.broadcom.report_generator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebServer {
    @JsonProperty("command")
    private String command;
    @JsonProperty("url")
    private String url;
    @JsonProperty("timeout")
    private int timeout;
    @JsonProperty("reuseExistingServer")
    private boolean reuseExistingServer;
}

