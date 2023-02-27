package com.broadcom.report_generator.processor;

import com.broadcom.report_generator.header.RouterHeader;
import com.broadcom.report_generator.model.Report;
import com.broadcom.report_generator.model.Spec;
import com.broadcom.report_generator.model.Suite;
import com.broadcom.report_generator.model.Test;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AggregateReportProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Report report = exchange.getIn().getBody(Report.class);
        Map<String, List<Test>> projectTestsMap = getProjectTestsMap(report);
        exchange.getIn().setBody(projectTestsMap);
        exchange.getIn().setHeader(RouterHeader.project_tests_map.name(), projectTestsMap);
    }

    private Map<String, List<Test>> getProjectTestsMap(Report report) {
        Map<String, List<Test>> projectTestsMap = new HashMap<>();
        for (Suite suite : report.getSuites()) {
            if (suite.getSpecs() == null || suite.getSpecs().size() == 0) {
                for (Suite suite1 : suite.getSuites()) {
                    getProjectTestsMap(projectTestsMap, suite);
                }
            } else {
                getProjectTestsMap(projectTestsMap, suite);
            }
        }
        return projectTestsMap;
    }
    private Map<String, List<Test>> getProjectTestsMap(Map<String, List<Test>> projectTestsMap, Suite suite) {
        if (suite.getSpecs() != null) {
            for (Spec spec : suite.getSpecs()) {
                if (spec.getTests() != null) {
                    for (Test test : spec.getTests()) {
                        String projectName = test.getProjectName();
                        if (projectName != null) {
                            if (projectTestsMap.containsKey(projectName)) {
                                projectTestsMap.get(projectName).add(test);
                            } else {
                                projectTestsMap.put(projectName, Collections.singletonList(test));
                            }
                        }
                    }
                }
            }
        }
        return projectTestsMap;
    }
}
