package com.aurosks.kpac_project.presentation;

import com.aurosks.kpac_project.application.exception.MessageException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final ResponseEntity<ErrorResponse> INTERNAL_ERROR = ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("Internal error occurred"));

    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    public GlobalExceptionHandler(MessageSource messageSource, LocaleResolver localeResolver) {
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Exception ex) {
        if (ex instanceof MessageException msgEx) {
            Locale locale = localeResolver.resolveLocale(request);
            String formatted = msgEx.format(messageSource, locale);
            log.trace("Handling message exception '{}' ({}): {}", msgEx.getMessage(), locale, formatted);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(formatted));
        }

        log.error("Internal error occurred while handling {}", request.getRequestURI(), ex);
        return INTERNAL_ERROR;
    }

    public record ErrorResponse(String message) {
    }
}