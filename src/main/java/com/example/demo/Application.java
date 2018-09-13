package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
	    SpringApplication.run(Application.class, args);
	}

    @EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
	    LOGGER.debug("onApplicationEvent() Received event: {}",  event);
	}
}
