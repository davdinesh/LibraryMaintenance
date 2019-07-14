/**
 * 
 */
package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

/**
 * @author viswa
 *
 */
@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}
	
	public Book findById(long id) {
		return bookRepository.getOne(id);
	}
	
	public void deleteBook(long id) {
		bookRepository.deleteById(id);
	}
	
	public Book createBook(Book book) {
		Book savedBook = bookRepository.save(book);
		return savedBook;
	}

}
