/**
 * 
 */
package com.example.library.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.library.model.Book;
import com.example.library.model.Subject;
import com.example.library.repository.BookRepository;
import com.example.library.repository.SubjectRepository;
import com.example.library.service.BookService;

/**
 * @author viswa
 *
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/books")
public class BookRestController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@GetMapping(value="/",produces="application/json")
	public List<Book> fetchAllBooks() {
		return bookService.findAllBooks();
	}
	
	@GetMapping("/{id}")
	public Book fetchBookById(@PathVariable long id) {
		
		Book book = bookService.findById(new Long(id));
		if (book == null) {
			return null;
		}
		
		return book;
	}
	
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable long id) {
		bookService.deleteBook(new Long(id));
	}
	
	@PostMapping(path="/{subjectId}",consumes="application/json", produces="application/json;charset=UTF-8")
	public ResponseEntity<Book> createBook(@RequestBody Book book, @PathVariable long subjectId) {
		Subject subject = subjectRepository.getOne(subjectId);
		if (subject == null) {
			return ResponseEntity.notFound().build();
		}
		book.setSubject(subject);
		Book savedBook = bookService.createBook(book);
		/*URI location = ServletUriComponentsBuilder.fromCurrentServletMapping()
						.path("api/books/{id}")
						.buildAndExpand(savedBook.getId())
						.toUri();*/
		return new ResponseEntity<Book>(savedBook,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}/{subjectId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable long id, @PathVariable long subjectId) {
		Subject subject = subjectRepository.getOne(subjectId);
		if (subject == null) {
			return ResponseEntity.notFound().build();
		}
		book.setSubject(subject);
		Book existingBook = bookService.findById(new Long(id));
		if (existingBook == null) {
			return ResponseEntity.notFound().build();
		}
		
		book.setId(id);
		Book updatedBook = bookService.createBook(book);
		return new ResponseEntity<Book>(updatedBook,HttpStatus.ACCEPTED);
	}

}
