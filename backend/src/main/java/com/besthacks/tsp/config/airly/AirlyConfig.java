package com.besthacks.tsp.config.airly;

import com.besthacks.tsp.service.AirlyClient;
import com.besthacks.tsp.service.DefaultAirlyClient;
import com.besthacks.tsp.service.MockedAirlyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;

@Configuration
public class AirlyConfig {

    @Value("${airly.api.key}")
    private String airlyApiKey;

    @Value("${airly.api.url}")
    private String airlyApiBaseUrl;

    @Value("${airly.mock}")
    private Boolean airlyMocked;

    @Bean("airlyRestTemplate")
    public RestTemplate getAirlyRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(airlyApiBaseUrl)
                .setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .interceptors((request, body, execution) -> {
                    request.getHeaders().setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    request.getHeaders().set("apikey", airlyApiKey);
                    return execution.execute(request, body);
                }).build();
    }

    @Bean
    public AirlyClient getAirlyClient(RestTemplate airlyRestTemplate) {
        if (airlyMocked) {
            return new MockedAirlyClient();
        } else
            return new DefaultAirlyClient(airlyRestTemplate);
    }

}
