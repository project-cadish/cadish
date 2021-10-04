package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BookServiceimpl implements BookService {
    @Autowired
    private Environment env;

    @Override
    public Mono<String> getBookList(String query) {
        String url = "https://openapi.naver.com/v1/search/book.json";

        WebClient webClient = WebClient.create();

        Mono<String> result = webClient.get()
                .uri(url + "?query=" + query)
                .headers(headers -> {
                    headers.add("X-Naver-Client-Id", env.getProperty("naver.client.id"));
                    headers.add("X-Naver-Client-Secret", env.getProperty("naver.client.secret"));
                })
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus != HttpStatus.OK,
                        clientResponse -> {
                            return clientResponse.createException()
                                    .flatMap(it -> Mono.error(new RuntimeException("code: " + clientResponse.statusCode())));
                        }
                )
                .bodyToMono(String.class)
                        .onErrorResume(throwable -> {
                            return Mono.error(new RuntimeException(throwable));
                        });

        return result;
    }
}
