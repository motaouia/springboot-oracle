package org.medmota.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.medmota.demo.entities.Tutorial;
import org.medmota.demo.repositories.TutorialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8998")
@RestController
@RequestMapping("/api")
public class TutorialController {

	private TutorialRepository tutorialRepository;

	public TutorialController(TutorialRepository tutorialRepository) {
		this.tutorialRepository = tutorialRepository;
	}

	@GetMapping(value = "/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Tutorial> listTutorials = new ArrayList<>();

			if (title == null) {
				tutorialRepository.findAll().forEach(listTutorials::add);
			} else {
				tutorialRepository.findByTitleContaining(title).forEach(listTutorials::add);
			}
			if (listTutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(listTutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable Long id) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/tutorials")
	public ResponseEntity<Tutorial> saveTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception exception) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable Long id, @RequestBody Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {
			List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
