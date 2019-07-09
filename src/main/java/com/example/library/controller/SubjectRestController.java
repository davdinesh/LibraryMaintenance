/**
 * 
 */
package com.example.library.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.library.model.Subject;
import com.example.library.repository.SubjectRepository;



/**
 * @author viswa
 *
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class SubjectRestController {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@GetMapping("/api/subjects")
	public List<Subject> fetchAllSubjects() {
		return subjectRepository.findAll();
	}
	
	@GetMapping("/api/subjects/{id}")
	public Subject fetchSubjectById(@PathVariable long id) {
		
		Subject subject = subjectRepository.getOne(new Long(id));
		if (subject == null) {
			return null;
		}
		
		return subject;
	}
	
	@DeleteMapping("/api/subjects/{id}")
	public void deleteSubject(@PathVariable long id) {
		subjectRepository.deleteById(new Long(id));
	}
	
	@PostMapping("/api/subjects/save")
	public ResponseEntity<Subject> saveSubject(@RequestBody Subject subject) {
		
		Subject savedSubject = subjectRepository.save(subject);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/id")
						.buildAndExpand(savedSubject.getId())
						.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/api/subjects/{id}")
	public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject, @PathVariable long id) {
		Subject existingSubject = subjectRepository.getOne(new Long(id));
		if (existingSubject == null) {
			System.out.println("Subject already exits!!!");
			return ResponseEntity.notFound().build();
		}
		
		subject.setId(id);
		subjectRepository.save(subject);
		return ResponseEntity.noContent().build();
	}

}
