package com.demo.booklist.model;

import java.util.Objects;

/**
 * Model class of a Book.
 * @author tjahnsen
 */
public class Book implements java.io.Serializable {

	private String author;
	protected String title;

	public Book() {
	}

	public Book(String author, String title) {
		this.author = author;
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Checks if either author or title contains the input value.
	 * It compares to lowercase values, so input must be lowercased.
	 * Throws NullPointerException if input is NULL.
	 * @param input Lowercase filter, not null.
	 * @return 
	 */
	public boolean containsString(String input) {
		boolean contains = false;
		return (
			((author != null) && (author.toLowerCase().contains(input)))
				||
			((title != null) && (title.toLowerCase().contains(input)))
		);
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 37 * hash + Objects.hashCode(this.author);
		hash = 37 * hash + Objects.hashCode(this.title);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Book other = (Book) obj;
		return (Objects.equals(this.author, other.author) && Objects.equals(this.title, other.title));
	}

	@Override
	public String toString() {
		return "Book{" + "author=" + author + ", title=" + title + '}';
	}
}
