package com.example.shuffle.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ShuffleService {

    private final RestTemplate restTemplate;
    private final String logServiceUrl;

    public ShuffleService(RestTemplate restTemplate, @Value("${service.log.url}") String logServiceUrl) {
        this.restTemplate = restTemplate;
        this.logServiceUrl = logServiceUrl;
    }

    public List<Integer> generateShuffledArray(int number) {
        List<Integer> array = IntStream.rangeClosed(1, number)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(array);
        return array;
    }

    @Async
    public void sendRequestToLogger(Integer number) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Integer> request = new HttpEntity<>(number, headers);
            restTemplate.postForEntity(this.logServiceUrl, request, Void.class);
        } catch (HttpStatusCodeException e) {
            log.error("HTTP error while logging: {}, status: {}", e.getResponseBodyAsString(), e.getStatusCode());
        } catch (Exception e) {
            log.error("Unexpected error during logging request", e);
        }
    }

    @SpringBootApplication
    @EnableAsync
    public static class ServiceShuffleApplication {
        public static void main(String[] args) {
            SpringApplication.run(ServiceShuffleApplication.class, args);
        }
    }
}