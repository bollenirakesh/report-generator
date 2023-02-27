package com.broadcom.report_generator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Suite {
    @JsonProperty("title")
    private String title;
    @JsonProperty("file")
    private String file;
    @JsonProperty("column")
    private int column;
    @JsonProperty("line")
    private int line;
    @JsonProperty("specs")
    List<Spec> specs;
    @JsonProperty("suites")
    List<Suite> suites;
}
