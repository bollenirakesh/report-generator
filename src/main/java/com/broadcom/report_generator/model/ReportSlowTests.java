package com.broadcom.report_generator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReportSlowTests {
    @JsonProperty("max")
    private int max;
    @JsonProperty("threshold")
    private int threshold;
}
