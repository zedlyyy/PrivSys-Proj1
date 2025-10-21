package pt.unl.fct.systemsprivacy.lab2client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;


@Configuration
public class RestClientConfig
{
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                    .baseUrl("http://127.0.0.1:8000/api/")
                    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
    }
    
}
