package com.broadcom.report_generator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Spec {
    @JsonProperty("title")
    private String title;
    @JsonProperty("ok")
    private boolean ok;
    @JsonProperty("tags")
    private List<Object> tags;
    @JsonProperty("tests")
    private List<Test> tests;
    @JsonProperty("id")
    private String id;
    @JsonProperty("file")
    private String file;
    @JsonProperty("line")
    private int line;
    @JsonProperty("column")
    private int column;
}
