package com.example.ServiceA.response;

import java.util.List;

import com.example.ServiceA.mapping.Book;

public class ServerAGetResponse {
	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	

}
