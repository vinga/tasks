package task2.domain;

import lombok.Data;

import java.util.UUID;

public class PayUOrderRequest {
    public enum CurrencyCode {
        PLN
    }

    @Data
    public static class OrderRequest {
        private String notifyUrl = "https://your.eshop.com/notify";
        private String customerIp = "127.0.0.1";
        private String merchantPosId = "145227";
        private String description = "RTV market";
        private CurrencyCode currencyCode = CurrencyCode.PLN;
        private int totalAmount = 145227;
        private String extOrderId = UUID.randomUUID().toString();
        private Buyer buyer=new Buyer();
        private Settings settings = new Settings();
        private Product[] products = new Product[]{new Product()};
    }

    @Data
    public static class Buyer {
        private String email = "john.doe@example.com";
        private String phone = "654111654";
        private String firstName = "John";
        private String lastName = "Doe";
        private String language = "pl";
    }

    @Data
    public static class Settings {
        private String invoiceDisabled = "true";
    }

    @Data
    public static class Product {
        private String name = "Wireless Mouse for Laptop";
        private String unitPrice = "15000";
        private String quantity = "1";
    }
}
