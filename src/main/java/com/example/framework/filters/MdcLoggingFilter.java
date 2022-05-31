package com.example.framework.filters;

import static com.example.framework.common.LoggingConstants.MDC_FIELD_TRACE_ID;
import static com.example.framework.common.LoggingConstants.PATH_LABEL;
import static com.example.framework.common.LoggingConstants.QUERY_LABEL;
import static com.example.framework.common.LoggingTraceIdUtils.extractTraceIdFromRequest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * @author jure.repe
 */
@Slf4j
@Provider
@PreMatching
public class MdcLoggingFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        MDC.clear();
        mdcPutIfNotNull(PATH_LABEL, requestContext.getUriInfo().getPath());
        mdcPutIfNotNull(QUERY_LABEL, requestContext.getUriInfo().getRequestUri().getQuery());
        mdcPutIfNotNull(MDC_FIELD_TRACE_ID, extractTraceIdFromRequest(requestContext));
    }

    private void mdcPutIfNotNull(String mdcFieldName, String mdcFieldValue) {
        if (mdcFieldValue != null) {
            MDC.put(mdcFieldName, mdcFieldValue);
        }
    }
}
