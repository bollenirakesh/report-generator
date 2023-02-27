package com.broadcom.report_generator.service.impl;

import com.broadcom.report_generator.sao.NotificationClient;
import com.broadcom.report_generator.service.NotificationService;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService, InitializingBean {

    @Value("${retry.maxAttempts}")
    private String retryMaxAttempts;
    @Value("${notification-service.url}")
    private String notificationServiceUrl;
    private static NotificationClient notificationClient;

    @Override
    public void notify(String projectName) {
        notificationClient.notify(projectName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(Integer.parseInt(this.retryMaxAttempts))
                .build();
        Retry notificationSeriviceRetry = Retry.of("notificationSeriviceRetry", retryConfig);
        FeignDecorators feignDecorators = FeignDecorators.builder()
                .withRetry(notificationSeriviceRetry)
                .build();
        this.notificationClient = Resilience4jFeign.builder(feignDecorators)
                .target(NotificationClient.class, this.notificationServiceUrl);
    }
}
