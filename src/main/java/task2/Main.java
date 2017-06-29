package task2;

import task2.domain.PayUConfig;
import task2.domain.PayUOrderRequest.OrderRequest;
import task2.domain.PayUOrderResponse;

public class Main {

    public static void main(String[] args) {
        PayUConfig config = new PayUConfig();

        PayUService service = new PayUService(config);

        OrderRequest orderRequest = new OrderRequest();
        // fill order with user-specific data

        final PayUOrderResponse response = service.createOrder(orderRequest);

        // save extOrderId somewhere
        // redirect user to response.getRedirectUri()
        System.out.println(response.getRedirectUri());
    }
}
