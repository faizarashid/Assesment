package com.example.ServiceB.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.ServiceB.entity.Book;
import com.example.ServiceB.repository.BookRepository;

@Service
public class ServiceBBookService {
	private File file = new File("ServiceBLogs.txt");
	@Autowired
	BookRepository bookRepository;
	

	public List<Book> getAllBooks() {
		String contents =null;
		List<Book> books = null;
		try (Writer writer = new BufferedWriter(new FileWriter(file))) {
			contents = "Service B ------------ Request" + LocalDateTime.now() + " "
					+ ServletUriComponentsBuilder.fromCurrentRequest();

			writer.write(contents);
			books= bookRepository.findAll();	
			contents = System.getProperty("line.separator") + "Service B ------------ Response" + LocalDateTime.now() + " " + books;
			writer.write(contents);
		}
	 catch (IOException e) {
		e.printStackTrace();
	}
		return books;
	}

	public Book saveBook(Book book) {
		String contents =null;
		Book saveBook=null;
		try (Writer writer = new BufferedWriter(new FileWriter(file))) {
			contents = System.getProperty("line.separator") +"Service B ------------ Request" + LocalDateTime.now() + " "
					+ book.toString();

			writer.write(contents);
			saveBook= bookRepository.save(book);	
			contents = System.getProperty("line.separator") +"Service B ------------ Response" + LocalDateTime.now() + " " + saveBook.toString();
			writer.write(contents);
		}
	 catch (IOException e) {
		e.printStackTrace();
	}
		return saveBook;
	}

}
