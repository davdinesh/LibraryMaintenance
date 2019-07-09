/**
 * 
 */
package com.example.library.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * @author viswa
 *
 */
@Entity
@Table(name="subject")
public class Subject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
	private Long id;
	
	@Column(name = "subject_title")
	private String title;
	
	@Column(name = "subject_duration")
	private int duration;
	
	//@OneToMany(mappedBy="subject", cascade = CascadeType.ALL, orphanRemoval = true)
	//@JsonBackReference
	//private Set<Book> books;
	
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
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	//sort by title
	public static Comparator<Subject> sortByTitle = new Comparator<Subject>() {
		
		public int compare(Subject obj1, Subject obj2) {
			
			//sort in ascending order
			return obj1.getTitle().compareTo(obj2.getTitle());
			
			//sort in descending order
			//return obj2.name.compareTo(obj1.name);
		}
	};
	
	@Override
	public String toString() {
		return "id: " + id + ", Title: " + title + ", Duration: " + duration; 
	}

}
