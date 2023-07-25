package com.chatbot.jbdev.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Slf4j
@RestController
public class ChatController {

    @Value("${url.space}")
    private String URL;

    private static final Gson gson = new Gson();
    private static final HttpClient client = HttpClient.newHttpClient();

    @PostMapping("/push")
    public ResponseEntity<String> pushMessage(@RequestParam String content) throws IOException, InterruptedException {
        String message = gson.toJson(Map.of("text", content));

        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(URL))
                .header("accept", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(message))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.ok(response.body());
    }

    @PostMapping("/chat")
    public void handleChat(@RequestBody String message) {
        log.info("--------------------------");
        log.info("Message: " + message);
        log.info("--------------------------");
    }

    @PostMapping("/")
    public void handleChat2(@RequestBody String message) {
        log.info("--------------------------");
        log.info("Message: " + message);
        log.info("--------------------------");
    }
}
