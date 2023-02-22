package com.example.ServiceA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServiceA.request.ServiceARequest;
import com.example.ServiceA.response.ServerAGetResponse;
import com.example.ServiceA.response.ServerAPostResponse;
import com.example.ServiceA.service.ServiceForA;

@RestController
@RequestMapping("/serviceA")
public class ServiceAController {
	@Autowired
	private ServiceForA serviceA;

	@GetMapping("/get/book")
	public ResponseEntity<ServerAGetResponse> getRequest() {
		return new ResponseEntity<ServerAGetResponse>(serviceA.getBooksFromServiceB(), (HttpStatus.OK));
	}
	
	@PostMapping("/post/book")
	public ResponseEntity<ServerAPostResponse> postRequest(@RequestBody ServiceARequest request) {
		return new ResponseEntity<ServerAPostResponse>(serviceA.addBookToServiceB(request), (HttpStatus.OK));
	}

}
