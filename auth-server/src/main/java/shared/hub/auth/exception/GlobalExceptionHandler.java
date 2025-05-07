package shared.hub.auth.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shared.hub.auth.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse<?>> handlingAppException(AppException exception) {
        return ResponseEntity
                .status(exception.getErrorCode().getStatusCode())
                .body(ApiResponse.fail("", exception.getErrorCode()));
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ApiResponse<?>> handlingRuntimeException(RuntimeException exception) {
        var err = NotifyCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(err.getStatusCode())
                .body(ApiResponse.fail("", err));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse<?>> handlingCommonException(RuntimeException exception) {
        var err = NotifyCode.COMMON_ERROR;
        return ResponseEntity
                .status(err.getStatusCode())
                .body(ApiResponse.fail("", err));
    }
}
