package com.broadcom.report_generator.model;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Getter
@Setter
public class Test {
    @JsonProperty("timeout")
    private int timeout;
    @JsonProperty("annotations")
    private List<Object> annotations;
    @JsonProperty("expectedStatus")
    private String expectedStatus;
    @JsonProperty("projectId")
    private String projectId;
    @JsonProperty("projectName")
    private String projectName;
    @JsonProperty("results")
    private List<Result> results;
    @JsonProperty("status")
    private String status;

    @Data
    @Getter
    @Setter
    public static class Result {
        @JsonProperty("workerIndex")
        private int workerIndex;
        @JsonProperty("status")
        private String status;
        @JsonProperty("duration")
        private int duration;
        @JsonProperty("error")
        private Error error;
        @JsonProperty("errors")
        private List<Error> errors;
        @JsonProperty("stdout")
        private List<Object> stdout;
        @JsonProperty("stderr")
        private List<Object> stderr;
        @JsonProperty("retry")
        private int retry;
        @JsonProperty("startTime")
        private String startTime;
        @JsonProperty("attachments")
        private List<Object> attachments;

        @Data
        @Getter
        @Setter
        public static class Error {
            @JsonProperty("message")
            private String message;
            @JsonProperty("stack")
            private String stack;
        }
    }
}

