package shared.hub.auth.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {

    public AppException(NotifyCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    private NotifyCode errorCode;

}
