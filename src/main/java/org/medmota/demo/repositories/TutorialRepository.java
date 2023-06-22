package org.medmota.demo.repositories;

import java.util.List;

import org.medmota.demo.entities.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
	
	List<Tutorial> findByPublished(boolean published);
	List<Tutorial> findByTitleContaining(String title);

}
