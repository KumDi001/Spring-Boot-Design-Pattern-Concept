package com.sample.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api")
public class RestControllerForKafka {
	@GetMapping("/producerMessage")
	public void getMessageFromClient(@RequestParam("message") String message) {
		
	}

}
