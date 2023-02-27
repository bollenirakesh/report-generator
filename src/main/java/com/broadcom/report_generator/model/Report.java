package com.broadcom.report_generator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Report {
        @JsonProperty("config")
        private Config config;
        @JsonProperty("suites")
        private List<Suite> suites;
        @JsonProperty("errors")
        private List<Object> errors;
}
