package com.broadcom.report_generator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Reporter {
    @JsonProperty("json")
    private JsonConfig jsonConfig;

    @Data
    @Getter
    @Setter
    public static class JsonConfig {
        @JsonProperty("outputFile")
        private String outputFile;
    }
}
