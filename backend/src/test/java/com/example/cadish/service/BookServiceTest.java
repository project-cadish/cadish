package com.example.cadish.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;


public class BookServiceTest {

    @Autowired
    Environment env;

    @Test
    void bookListTest() {

        WebClient webClient;
    }
}
