package com.aurosks.kpac_project.application.exception;

import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;

import java.util.Locale;

/**
 * Lightweight exception class for throwing exceptions with messages code,
 * which can be used to display user-friendly messages.
 */
public class MessageException extends RuntimeException {
    private static final Object[] EMPTY_ARGS = new String[0];
    private final Object[] args;

    public MessageException(String message, String... args) {
        this(message, null, args);
    }

    public MessageException(String message, Throwable cause, String... args) {
        this(message, cause, false);
    }

    public MessageException(String message, Throwable cause, boolean enableSuppression, String... args) {
        super(message, cause, enableSuppression, false);
        this.args = args == null ? EMPTY_ARGS : args;
    }

    public Object[] getArgs() {
        return args;
    }

    public String format(MessageSource messageSource, Locale locale) {
        String code = getMessage();
        return messageSource.getMessage(code, args, locale);
    }
}
