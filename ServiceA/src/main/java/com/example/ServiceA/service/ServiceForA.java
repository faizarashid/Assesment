package com.example.ServiceA.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.ServiceA.request.ServiceARequest;
import com.example.ServiceA.response.ServerAGetResponse;
import com.example.ServiceA.response.ServerAPostResponse;

@Service
public class ServiceForA {
	private File file = new File("ServiceALogs.txt");
	RestTemplate restTemplate;

	@Value("${ServiceBUrl}")
	public String base;

	@Autowired
	public ServiceForA(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ServerAGetResponse getBooksFromServiceB() {
		ResponseEntity<ServerAGetResponse> reponse = null;
		String contents = null;
		try (Writer writer = new BufferedWriter(new FileWriter(file))) {
			contents = "Service A ------------ Request" + LocalDateTime.now() + " "
					+ ServletUriComponentsBuilder.fromCurrentRequest();

			writer.write(contents);

			// create headers
			HttpHeaders headers = new HttpHeaders();
			// set `content-type` header
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);
			reponse = restTemplate.exchange(base + "/ServiceB/books", HttpMethod.GET, entity, ServerAGetResponse.class);
			contents = System.getProperty("line.separator") + "Service A ------------ Response" + LocalDateTime.now() + " " + reponse.getBody().getBooks();
			writer.write(contents);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (ServerAGetResponse) reponse.getBody().getBooks();
	}

	public ServerAPostResponse addBookToServiceB(ServiceARequest request) {
		HttpEntity requestBody = null;
		String contents = null;
		ResponseEntity<ServerAPostResponse> responseEntity = null;
		try (Writer writer = new BufferedWriter(new FileWriter(file))) {
			contents = System.getProperty("line.separator") +"Service A ------------ Request" + LocalDateTime.now() + " "
					+ ServletUriComponentsBuilder.fromCurrentRequest() + " " + request.toString();

			writer.write(contents);
			requestBody = new HttpEntity(request);
			responseEntity = restTemplate.postForEntity(base + "/ServiceB/post/book", requestBody,
					ServerAPostResponse.class);
			contents = System.getProperty("line.separator") + "Service A ------------ Response" + LocalDateTime.now() + " " + responseEntity.getBody();
			writer.write(contents);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseEntity.getBody();
	}

}
