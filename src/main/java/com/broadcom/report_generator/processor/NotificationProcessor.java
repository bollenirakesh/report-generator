package com.broadcom.report_generator.processor;

import com.broadcom.report_generator.service.NotificationService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationProcessor implements Processor {

    private static final String KEY = "CamelRedis.Key";

    @Autowired
    NotificationService notificationService;

    @Override
    public void process(Exchange exchange) throws Exception {
        String projectName = exchange.getIn().getHeader(KEY, String.class);
        this.notificationService.notify(projectName);
    }
}
