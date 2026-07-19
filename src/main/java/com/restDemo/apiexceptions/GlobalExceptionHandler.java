package com.restDemo.apiexceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    // Handle all ApiExceptions
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex,
                                                            WebRequest request) {
        if (ex.getCause() != null) {
            log.error("Exception while processing request", ex.getCause());
        }

        return ResponseEntity.status(ex.getStatus()).body(
                new ErrorResponse(
                        ex.getStatus().value(),
                        ex.getErrors(),
                        ex.getMessage(),
                        request.getDescription(false).replace("uri=", "")
                )
        );
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDBException(DataAccessException ex, WebRequest request) {

        Throwable root = ex.getRootCause();

        if (ex.getCause() != null) {
            log.error("Exception while processing request", ex.getCause());
        }

        return ResponseEntity.status(500).body(
                new ErrorResponse(
                        500,
                        ex.getMessage(),
                        root != null ? root.getMessage() : "No root cause",
                        request.getDescription(false).replace("uri=", "")
                )
        );
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        log.error("UNEXPECTED ERROR", ex);
        ErrorResponse errorResponse = new ErrorResponse(
                500,
                "INTERNAL_ERROR",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(500).body(errorResponse);
    }



    // Handle method unsupported exceptions
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex, WebRequest request) {

        String message = ex.getMethod() + " method is not supported for this endpoint. Supported methods are " + ex.getSupportedHttpMethods();

        ErrorResponse errorResponse = new ErrorResponse(
                405,
                "METHOD_NOT_ALLOWED",
                message,
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(405).body(errorResponse);
    }

    private void logFlow(Exception ex) {
        for (StackTraceElement ste : ex.getStackTrace()) {
            if (ste.getClassName().startsWith("com.restDemo")) {
                log.error("at {}", ste);
            }
        }
    }
}
