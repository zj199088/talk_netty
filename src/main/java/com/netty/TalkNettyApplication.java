package com.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TalkNettyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalkNettyApplication.class, args);
		System.out.println("talk_netty:start...");
	}
}
