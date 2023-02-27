package com.broadcom.report_generator.route;

import com.broadcom.report_generator.header.RouterHeader;
import com.broadcom.report_generator.model.AggregateReportRouteProperties;
import com.broadcom.report_generator.model.Report;
import com.broadcom.report_generator.model.Test;
import com.broadcom.report_generator.model.Tests;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.redis.RedisConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AggregateReportRoute extends RouteBuilder {
    @Autowired
    private AggregateReportRouteProperties aggregateReportRouteProperties;

    @Override
    public void configure() throws Exception {
        from("file:" + aggregateReportRouteProperties.getRootDirectory() + "?recursive=true")
                .routeId("aggregateReportRoute")
                .process("aggregateReportPreProcessor")
                .to("file:"+ aggregateReportRouteProperties.getBackupDirectory() + "?fileName=" + "${header.backup_file_name}" )
                .unmarshal().json(JsonLibrary.Jackson, Report.class)
                .process("aggregateReportProcessor")
                .to("direct:saveToRedis")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody(exchange.getIn().getHeader(RouterHeader.project_tests_map.name(), Map.class));
                    }
                })
                .marshal().json(JsonLibrary.Jackson)
                .to("file:" + aggregateReportRouteProperties.getTargetDirectory() + "?fileName=reports.json");

        from("direct:saveToRedis")
                .routeId("saveToRedisDirectRoute")
                .split(simple("${body.keySet()}"))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Map<String, List<Test>> projectTestsMap =  exchange.getIn().getHeader(RouterHeader.project_tests_map.name(), Map.class);
                        String key = exchange.getIn().getBody(String.class);
                        Tests value = projectTestsMap.get(key)  != null ? new Tests(projectTestsMap.get(key)) : new Tests(null);
                        exchange.getIn().setHeader(RedisConstants.KEY, key);
                        exchange.getIn().setHeader(RedisConstants.VALUE, value);
                    }
                })
                .toF("spring-redis:%s", "saveToRedis?redisTemplate=#redisTemplate")
                .process("notificationProcessor")
                .log("Saved report to Redis. Project Name: ${header." + RedisConstants.KEY + "}");
    }
}

