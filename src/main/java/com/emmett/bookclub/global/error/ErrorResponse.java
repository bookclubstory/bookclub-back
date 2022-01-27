package com.emmett.bookclub.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private List<FieldError> errors;


    private ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(final HttpStatus status, String message, final List<FieldError> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public static ErrorResponse of(final HttpStatus status, String message) {
        return new ErrorResponse(status,message);
    }

    public static ErrorResponse of(final HttpStatus status, String message, final BindingResult bindingResult) {
        return new ErrorResponse(status, message, FieldError.of(bindingResult));
    }

    public static ErrorResponse of(final HttpStatus status, String message, final List<FieldError> errors) {
        return new ErrorResponse(status, message, errors);
    }

    public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<FieldError> errors = FieldError.of(e.getName(), value, e.getErrorCode());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage() ,errors);
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldError{
        private String field;
        private String value;
        private String reason;

        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
