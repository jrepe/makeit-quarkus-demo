package com.example.rest.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jure.repe
 */
@Slf4j
public class ExternalException extends RuntimeException {

    public ExternalException(String errorMessage) {
        super(errorMessage);
    }
}
