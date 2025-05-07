package shared.hub.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum NotifyCode {

    SUCCESS(20000, "Success", HttpStatus.OK),
    CREATED(20001, "Created success", HttpStatus.CREATED),
    ACCEPTED(20002, "Request accepted", HttpStatus.ACCEPTED),
    INVALID_PARAM(40000, "Invalid parameter", HttpStatus.BAD_REQUEST),
    COMMON_ERROR(40000, "Something when wrong!", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(40101, "You must login to see this resource", HttpStatus.UNAUTHORIZED),
    PERMISSION_DENY(40103, "Access deny", HttpStatus.FORBIDDEN),
    NOT_FOUND(40004, "This resource is not exist", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(50000, "Server error", HttpStatus.INTERNAL_SERVER_ERROR),


    ;

    NotifyCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
