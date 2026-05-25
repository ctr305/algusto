package com.algusto.algusto;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AlgustoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(AlgustoApplication.class)
            .run(args);
    }
}
