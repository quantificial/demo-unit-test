package demo.demounittest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandleAdvice {
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorVo processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private ValidationErrorVo processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorVo dto = new ValidationErrorVo();
        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = resolveErrorCode(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
        return dto;
    }

    private String resolveErrorCode(FieldError fieldError) {
        String[] fieldErrorCodes = fieldError.getCodes();
        return fieldErrorCodes[fieldErrorCodes.length - 1];
    }

    //@ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void returnHttpStatusCodeNotFound() {
        System.out.println("Something was found not. Returning HTTP status code 404");
    }

    public class FieldErrorVo {

        private final String field;
        private final String errorCode;

        public FieldErrorVo(String field, String errorCode) {
            this.field = field;
            this.errorCode = errorCode;
        }

        public String getField() {
            return field;
        }

        public String getErrorCode() {
            return errorCode;
        }
    }

    public class ValidationErrorVo {

        private final List<FieldErrorVo> fieldErrors = new ArrayList<>();

        public ValidationErrorVo() {
        }

        public void addFieldError(String path, String message) {
            FieldErrorVo error = new FieldErrorVo(path, message);
            fieldErrors.add(error);
        }

        public List<FieldErrorVo> getFieldErrors() {
            return fieldErrors;
        }
    }
}

