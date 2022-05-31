package com.example.framework.common;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jure.repe
 */
@Slf4j
@UtilityClass
public class LoggingConstants {

    public static final String QUERY_LABEL = "q";
    public static final String PATH_LABEL = "p";

    // MDC Field Constants
    public static final String MDC_FIELD_TRACE_ID = "X-B3-TraceId";

    // Stackdriver Json Field Constants
    public static final String MESSAGE_ATTRIBUTE = "message";
    public static final String EXCEPTION_ATTRIBUTE = "exception";
    public static final String LOGGER_ATTRIBUTE = "logger";
    public static final String SEVERITY_ATTRIBUTE = "severity";
    public static final String TRACE_ID_ATTRIBUTE = "logging.googleapis.com/trace";
}
