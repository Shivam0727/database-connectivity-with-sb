package com.shivam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.entities.Book;
import com.shivam.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	// Get all Books :
	public List<Book> getAllBooks() {
		List<Book> list = (List<Book>) this.bookRepository.findAll();
		return list;
	}

	// Get single Book by ID :
	public Book getBookById(int id) {
		Book book = null;
		try {
			book = this.bookRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	// Adding the book :
	public Book addBook(Book book) {
		Book result = this.bookRepository.save(book);
		return result;
	}

	// Delete book :
	public void deleteBook(int id) {
		this.bookRepository.deleteById(id);
	}

	// Updating book :
	public void updateBook(Book book, int bookId) {
		this.bookRepository.save(book);
		book.setId(bookId);
	}
}
