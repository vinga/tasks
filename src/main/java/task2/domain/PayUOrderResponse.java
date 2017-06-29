package task2.domain;

import lombok.Data;

@Data
public class PayUOrderResponse {
    private Status status;
    private String redirectUri;
    private String orderId;
    private String extOrderId;

    @Data
    public static class Status {
        private StatusCode statusCode;
    }

    public enum StatusCode {
        SUCCESS,
        WARNING_CONTINUE_REDIRECT,
        WARNING_CONTINUE_3DS,
        WARNING_CONTINUE_CVV,
        ERROR_SYNTAX,
        ERROR_VALUE_INVALID,
        ERROR_VALUE_MISSING,
        ERROR_ORDER_NOT_UNIQUE,
        UNAUTHORIZED,
        UNAUTHORIZED_REQUEST,
        DATA_NOT_FOUND,
        TIMEOUT,
        BUSINESS_ERROR,
        ERROR_INTERNAL,
        GENERAL_ERROR,
        WARNING,
        SERVICE_NOT_AVAILABLE
    }
}
