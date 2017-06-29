package task2;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import task2.domain.PayUConfig;
import task2.domain.PayUOrderRequest.OrderRequest;
import task2.domain.PayUOrderResponse;
import task2.domain.PayUOrderResponse.StatusCode;

public class PayUService {
    private final PayUConfig config;

    public PayUService(PayUConfig config) {

        this.config = config;
    }

    public PayUOrderResponse createOrder(OrderRequest orderRequest) {
        orderRequest.setDescription(config.getRequestDataConfig().getDescription());
        orderRequest.setMerchantPosId(config.getRequestDataConfig().getMerchantPosId());
        orderRequest.setNotifyUrl(config.getRequestDataConfig().getNotifyUrl());
        if (orderRequest.getBuyer()==null)
            throw new IllegalArgumentException("Buyer must be set");

        final RestTemplate restTemplate = config.newRestTemplate();
        final HttpEntity<OrderRequest> entityReq = new HttpEntity<>(orderRequest, config.headers());
        final ResponseEntity<PayUOrderResponse> result = restTemplate.postForEntity(config.getOrdersUrl(), entityReq, PayUOrderResponse.class);
        if (result.getStatusCode() != HttpStatus.FOUND)
            throw new RuntimeException("Unexpected result " + result.getStatusCode().toString());
        final PayUOrderResponse ss = result.getBody();
        if (ss.getStatus().getStatusCode() != StatusCode.SUCCESS) {
            throw new RuntimeException("Unexpected result " + ss.getStatus().getStatusCode());
        }
        return ss;
    }
}
