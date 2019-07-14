package com.example.library;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.library.model.Book;
import com.example.library.model.Subject;
import com.example.library.repository.BookRepository;
import com.example.library.repository.SubjectRepository;
import com.example.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
//@ContextConfiguration(classes=LibraryManagementApplication.class)
//@WebMvcTest
//@DataJpaTest
public class LibraryManagementApplicationTests {
	
	public LibraryManagementApplicationTests() {
		// TODO Auto-generated constructor stub
	}
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	BookService bookService;
	
	@Mock
	BookRepository bookRepository;
	
	@MockBean
	SubjectRepository subjectRepository;
	
	@Test
	public void fetchAllBooks() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
		Book book1 = new Book(1L,"Syllables", 50.00, 2, dateFormat.parse("07/21/2016"));
		Book book2 = new Book(2L,"Grammatic Mistakes", 50.00, 2, dateFormat.parse("07/01/2015"));
		List<Book> books = new ArrayList<Book>();
		books.add(book1);
		books.add(book2);
		Mockito.when(bookService.findAllBooks()).thenReturn(books);
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/books/")
				.accept(MediaType.APPLICATION_JSON)
		).andReturn();
		
		System.out.println("Mvc Response : " + mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void fetchBookById()  throws Exception {
		
		
		Book book1 = new Book(1L,"Syllables", 50.00, 2, dateFormat.parse("07/21/2016"));
		Mockito.when(bookService.findById(1L)).thenReturn(book1);
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/books/1")
				.accept(MediaType.APPLICATION_JSON)
		).andReturn();
		
		System.out.println("Mvc Response : " + mvcResult.getResponse().getContentAsString());
		
	}
	
	@Test
	public void createBook() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Book book1 = new Book(20L,"Cystals Science", 50.00, 2, dateFormat.parse("07/21/2016"));
		Subject subject = new Subject(3L,"Chemistry",20);
		book1.setSubject(subject);
		String mockBookJson = mapper.writeValueAsString(book1);
		System.out.println("Input JSON : " + mockBookJson);
		Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(book1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/books/3")
				.accept(MediaType.APPLICATION_JSON).content(mockBookJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		
	}

}
