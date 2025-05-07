package shared.hub.auth.dto;

import ch.qos.logback.core.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import shared.hub.auth.exception.NotifyCode;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int code;

    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data, NotifyCode code) {
        return ApiResponse.<T>builder()
                .data(data)
                .message(code.getMessage())
                .code(code.getCode())
                .build();
    }

    public static <T> ApiResponse<T> fail(String message, NotifyCode code) {
        message = !StringUtil.isNullOrEmpty(message) ? message : code.getMessage();
        return ApiResponse.<T>builder()
                .message(message)
                .code(code.getCode())
                .build();
    }
}
