package com.example.ServiceA.mapping;


public class Book {
	private Long id;
    private String title;
    private String author;
    private int year;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
    public String toString() {
        return "Book [id=" + id + ", title =" + title + ", author=" + author + ", year=" + year+ "]";
    }
}
