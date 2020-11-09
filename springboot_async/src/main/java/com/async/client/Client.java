package com.async.client;

import com.annotation.Consume;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class Client {

    @Async
    @Consume(unit = "ms")
    public Future<String> httpclient(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        System.out.println(response.getStatusLine());
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
