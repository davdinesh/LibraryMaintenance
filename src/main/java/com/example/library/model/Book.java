/**
 * 
 */
package com.example.library.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author viswa
 *
 */

@Entity
@Table(name="book")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
	private Long id;
	
	@Column(name="book_title")
	private String title;
	
	@Column(name="book_price")
	private double price;
	
	@Column(name="book_volume")
	private int volume;
	
	@Column(name="book_published_dt")
	@Temporal(TemporalType.DATE)
	private Date publishedDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="subject_id")
	//@JsonManagedReference
	private Subject subject;
	
	public Book() {
		
	}

	public Book(long id, String title, double price, int volume, Date publishedDate) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.volume = volume;
		this.publishedDate = publishedDate;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the volume
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * @return the publishedDate
	 */
	public Date getPublishedDate() {
		return publishedDate;
	}

	/**
	 * @param publishedDate the publishedDate to set
	 */
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	//sort by title
	public static Comparator<Book> sortByTitle = new Comparator<Book>() {
		
		public int compare(Book obj1, Book obj2) {
			
			//sort in ascending order
			return obj1.getTitle().compareTo(obj2.getTitle());
			
			//sort in descending order
			//return obj2.name.compareTo(obj1.name);
		}
	};
	
	public static Comparator<Book> sortByPubDate = new Comparator<Book>() {
		
		public int compare(Book obj1, Book obj2) {
			
			//sort in ascending order
			return obj1.getPublishedDate().compareTo(obj2.getPublishedDate());
			
			//sort in descending order
			//return obj2.name.compareTo(obj1.name);
		}
	};
	
	@Override
	public String toString() {
		return "id: " + id + ", Title: " + title + ", Price: " + price + ", Volume : " + volume + ", Published Date: "
				+ publishedDate.toString();
	}
	
}
