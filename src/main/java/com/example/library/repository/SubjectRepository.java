/**
 * 
 */
package com.example.library.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.model.Subject;

/**
 * @author viswa
 *
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	

}
