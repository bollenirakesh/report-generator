package com.broadcom.report_generator.sao;

import feign.Headers;
import feign.RequestLine;

@Headers({"Content-Type: application/json"})
public interface NotificationClient {
    @RequestLine("POST /notify")
    void notify(String projectName);
}
