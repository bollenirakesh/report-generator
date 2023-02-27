package com.broadcom.report_generator.processor;

import com.broadcom.report_generator.header.RouterHeader;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFileMessage;
import org.springframework.stereotype.Component;

@Component
public class AggregateReportPreProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        GenericFileMessage genericFileMessage = exchange.getIn(GenericFileMessage.class);
        String backUpFileName = genericFileMessage.getGenericFile().getAbsoluteFilePath()
                .replace("build_reports", "build_reports_backup");
        exchange.getIn().setHeader(RouterHeader.backup_file_name.name(), backUpFileName);
    }
}
