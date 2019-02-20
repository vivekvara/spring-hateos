package com.myapp.controller;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.vo.Greeting;

@RestController
public class GreetingController {
	private static final String TEMPLATE = "Hello, %s!";
	
	@RequestMapping("/greeting")
	public HttpEntity<Greeting> greeting(@RequestParam String name){
		Greeting greeting = new Greeting(String.format(TEMPLATE, name));
		
		greeting.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(GreetingController.class).greeting(name)).withSelfRel());
	
		return new ResponseEntity<>(greeting, HttpStatus.OK);
	}
}
