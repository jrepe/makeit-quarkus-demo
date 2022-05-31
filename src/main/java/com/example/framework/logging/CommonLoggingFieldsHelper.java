package com.example.framework.logging;

import static com.example.framework.common.LoggingConstants.MDC_FIELD_TRACE_ID;
import static com.example.framework.common.LoggingConstants.PATH_LABEL;
import static com.example.framework.common.LoggingConstants.QUERY_LABEL;
import static com.example.framework.common.LoggingConstants.TRACE_ID_ATTRIBUTE;
import static com.example.framework.common.LoggingTraceIdUtils.composeFullTraceLabel;
import static com.example.framework.common.LoggingTraceIdUtils.formatTraceId;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.quarkiverse.loggingjson.JsonGenerator;
import java.io.IOException;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import org.slf4j.MDC;

/**
 * @author jure.repe
 */
@ApplicationScoped
public class CommonLoggingFieldsHelper {

    private static final Set<String> MDC_COMMON_LABELS = Set.of(QUERY_LABEL, PATH_LABEL);
    private static final String PROJECT_ID = "jurer-playground";

    public void setCommonLogFields(JsonGenerator generator) throws IOException {
        addTraceId(generator);
        for (String label : MDC_COMMON_LABELS) {
            String value = MDC.get(label);
            if (value != null) {
                generator.writeStringField(label, value);
            }
        }
    }

    private void addTraceId(JsonGenerator generator) throws IOException {
        String traceId = MDC.get(MDC_FIELD_TRACE_ID);
        if (!isNullOrEmpty(traceId)) {
            traceId = composeFullTraceLabel(PROJECT_ID, formatTraceId(traceId));
            generator.writeStringField(TRACE_ID_ATTRIBUTE, traceId);
        }
    }
}
