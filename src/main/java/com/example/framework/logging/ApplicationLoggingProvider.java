package com.example.framework.logging;

import static com.example.framework.common.LoggingConstants.EXCEPTION_ATTRIBUTE;
import static com.example.framework.common.LoggingConstants.LOGGER_ATTRIBUTE;
import static com.example.framework.common.LoggingConstants.MESSAGE_ATTRIBUTE;
import static com.example.framework.common.LoggingConstants.SEVERITY_ATTRIBUTE;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.quarkiverse.loggingjson.JsonGenerator;
import io.quarkiverse.loggingjson.JsonProvider;
import io.smallrye.common.constraint.Nullable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.AllArgsConstructor;
import org.jboss.logmanager.ExtLogRecord;

/**
 * @author jure.repe
 */
@Singleton
@AllArgsConstructor(onConstructor_ = @Inject)
public class ApplicationLoggingProvider implements JsonProvider {

    CommonLoggingFieldsHelper commonFieldsHelper;

    @Override
    public void writeTo(JsonGenerator generator, ExtLogRecord logRecord) throws IOException {
        commonFieldsHelper.setCommonLogFields(generator);
        generator.writeStringField(SEVERITY_ATTRIBUTE, logRecord.getLevel().toString());
        generator.writeStringField(LOGGER_ATTRIBUTE, logRecord.getLoggerName());

        String stacktrace = getStacktraceIfAvailable(logRecord);
        if (!isNullOrEmpty(stacktrace)) {
            generator.writeStringField(MESSAGE_ATTRIBUTE, "\n" + stacktrace);
            generator.writeStringField(EXCEPTION_ATTRIBUTE, stacktrace);
        }
    }

    @Nullable
    private String getStacktraceIfAvailable(ExtLogRecord logRecord) {
        String stacktrace = null;
        var throwable = logRecord.getThrown();
        if (throwable != null) {
            var stringWriter = new StringWriter();
            var printWriter = new PrintWriter(stringWriter);
            throwable.printStackTrace(printWriter);
            stacktrace = stringWriter.toString();
        }
        return stacktrace;
    }
}
