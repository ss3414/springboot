package com.demo.client;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Component
public class Client {

    @Async
    @SneakyThrows
    public Future<String> httpclient(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(String.valueOf(response.statusCode()));
        return new CompletableFuture<>();
    }

    @Async
    @SneakyThrows
    public Future<String> asyncHttpClient(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        future.whenComplete((response, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
            } else {
                log.info(String.valueOf(response.statusCode()));
            }
        }).join();
        return new CompletableFuture<>();
    }

}
