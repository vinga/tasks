package task2.domain;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Data
public final class PayUConfig {

    private String ordersUrl = "https://secure.payu.com/api/v2_1/orders";
    private String authKey = "3e5cac39-7e38-4139-8fd6-30adc06a61bd";
    private RequestDataConfig requestDataConfig = new RequestDataConfig();

    @Data
    public static final class RequestDataConfig {
        private String notifyUrl = "https://your.eshop.com/notify";
        private String merchantPosId = "145227";
        private String description = "RTV market";
    }

    public RestTemplate newRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.setPrettyPrint(true);
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
        //restTemplate.setInterceptors(Arrays.asList(new LoggingRequestInterceptor()));

        return restTemplate;
    }

    public HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + authKey);
        return headers;
    }
}
