package com.demo.async.client;

import com.demo.annotation.Consume;
import lombok.SneakyThrows;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class Client {

    @Async
    @SneakyThrows
    @Consume(unit = "ms")
    public Future<String> httpclient(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        return new AsyncResult<>("");
    }

    @Async
    @Consume(unit = "ms")
    public Future<String> asyncHttpClient(String url) throws ExecutionException, InterruptedException {
        AsyncHttpClient asyncHttpClient = Dsl.asyncHttpClient();
        Future<Response> response = asyncHttpClient.prepareGet(url).execute();
        System.out.println(response.get().getStatusCode());
        return new AsyncResult<>("");
    }

}
