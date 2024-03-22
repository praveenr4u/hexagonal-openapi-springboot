package com.launch.template.service.exception;

import com.launch.template.domain.exception.EntityAlreadyExistException;
import com.launch.template.domain.exception.EntityNotFoundException;
import com.launch.template.model.ProblemDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String DOCS_WEB_HTTP_STATUS_400 = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400";
    public static final String DOCS_WEB_HTTP_STATUS_500 = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/500";
    private final Logger logger = LogManager.getLogger(ControllerExceptionHandler.class);

    // Exception method for Invalid request body and Method Argument.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> details = new HashMap<>();
        // Get all errors
        List<ObjectError> bindResult = ex.getBindingResult().getAllErrors();
        bindResult.forEach(error -> {
            // Field error
            if (error instanceof FieldError fieldError) {
                details.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        });

        ProblemDetails error = new ProblemDetails();
        error.setDetail(details);
        error.setInstance(URI.create(((ServletWebRequest) request).getRequest().getRequestURI()));
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle("Bad request body or method argument");
        error.setType(URI.create(DOCS_WEB_HTTP_STATUS_400));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetails> handleConstraintViolationException(
            ConstraintViolationException ex, HttpServletRequest request) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        Map<String, String> detail = new HashMap<>();
        if(violations != null && !violations.isEmpty()) {
            detail = violations.stream()
                    .collect(Collectors.toMap(
                            violation -> violation.getPropertyPath().toString(),
                            ConstraintViolation::getMessage));
        }
        return buildErrorResponseEntity(detail,
                URI.create(request.getRequestURI()),
                "Bad request body or method argument",
                URI.create(DOCS_WEB_HTTP_STATUS_400));

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetails> handleEntityNotFoundException(
            EntityNotFoundException ex, HttpServletRequest request) {
        Map<String, String> detail = new HashMap<>();
        detail.put("error", ex.getMessage());
        return buildErrorResponseEntity(ex, request, HttpStatus.NOT_FOUND, detail, URI.create("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404"));
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ProblemDetails> handleEntityAlreadyExistException(
            EntityAlreadyExistException ex, HttpServletRequest request) {
        Map<String, String> detail = new HashMap<>();
        detail.put("reason", ex.getMessage());
        detail.put("value", ex.getValue());
        detail.put("entity", ex.getEntityName());
        detail.put("field", ex.getFieldName());
        return buildErrorResponseEntity(
                detail,
                URI.create(request.getRequestURI()),
                String.format("The %s is not unique", ex.getFieldName()),
                URI.create(DOCS_WEB_HTTP_STATUS_400));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> handleUnexpectedException(
            Exception ex, HttpServletRequest request) {
        Map<String, String> detail = new HashMap<>();
        detail.put("error", ex.getMessage());
        return buildErrorResponseEntity(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, detail, URI.create(DOCS_WEB_HTTP_STATUS_500));
    }

    private ResponseEntity<ProblemDetails> buildErrorResponseEntity(Exception ex, HttpServletRequest request,
                                                                          HttpStatus httpStatus, Map<String, String> detail,
                                                                          URI type) {
        logger.error(ex.getMessage());
        ProblemDetails error = new ProblemDetails();
        error.setDetail(detail);
        error.setInstance(URI.create(request.getRequestURI()));
        error.setStatus(httpStatus.value());
        error.setTitle(ex.getMessage());
        error.setType(type);
        return new ResponseEntity<>(
                error, new HttpHeaders(), httpStatus);
    }

    private ResponseEntity<ProblemDetails> buildErrorResponseEntity(Map<String, String> detail,
                                                                    URI instance,
                                                                    String title,
                                                                    URI type) {
        ProblemDetails error = new ProblemDetails();
        error.setDetail(detail);
        error.setInstance(instance);
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(title);
        error.setType(type);
        return new ResponseEntity<>(
                error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
