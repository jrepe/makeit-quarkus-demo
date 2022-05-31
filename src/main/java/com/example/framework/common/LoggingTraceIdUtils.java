package com.example.framework.common;

import com.google.common.base.Preconditions;
import io.smallrye.common.constraint.Nullable;
import javax.ws.rs.container.ContainerRequestContext;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jure.repe
 */
@Slf4j
@UtilityClass
public class LoggingTraceIdUtils {

    // Specific to GCP, every request has a trace value appended
    private static final String X_CLOUD_TRACE_HEADER = "x-cloud-trace-context";

    @Nullable
    public static String extractTraceIdFromRequest(ContainerRequestContext requestContext) {
        String traceId = requestContext.getHeaderString(X_CLOUD_TRACE_HEADER);
        if (traceId != null) {
            int slash = traceId.indexOf('/');
            if (slash >= 0) {
                traceId = traceId.substring(0, slash);
            }
        }
        return traceId;
    }

    @Nullable
    public static String formatTraceId(@Nullable String traceId) {
        // If traceId is 16 digit hex, we need to prepend 0's to make it 32-digit hex
        if (traceId != null && traceId.length() == 16) {
            return "0000000000000000" + traceId;
        }
        return traceId;
    }

    public static String composeFullTraceLabel(String projectId, @Nullable String traceId) {
        Preconditions.checkNotNull(traceId, "Trace ID cannot be null");
        return "projects/" + projectId + "/traces/" + traceId;
    }
}
