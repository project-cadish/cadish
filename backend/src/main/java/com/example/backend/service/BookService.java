package com.example.backend.service;

import reactor.core.publisher.Mono;

public interface BookService {
    Mono<String> getBookList(String query);
}
