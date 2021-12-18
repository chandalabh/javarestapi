package com.example.endtoenddemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.endtoenddemo.entity.model.Tutorial;
import com.example.endtoenddemo.model.Response;
import com.example.endtoenddemo.model.Request;
import com.example.endtoenddemo.repository.TutorialRepository;

@RestController
@RequestMapping("/api")
public class DemoController {
	@Autowired
	TutorialRepository tutorialRepository;

	@Autowired
	ObjectMapping objectMapping;

	@GetMapping("/tutorial")
	public String get() {
		return "Hello world";
	}

	@PostMapping("/tutorials")
	public ResponseEntity<Response> createTutorial(@RequestBody Request request) {
		try {
			Tutorial tutorial = objectMapping.requestMappingToEntityModel(request);
			Tutorial _tutorial = tutorialRepository.save(tutorial);
			Response response = objectMapping.entityModelMappingToResponse(_tutorial);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			if (title == null)
				tutorialRepository.findAll().forEach(tutorials::add);
			else
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials/{id}")
	public String removeById(@PathVariable("id") long id) {
		tutorialRepository.deleteById(id);
		return "deleted succeffully";
	}

	@DeleteMapping("/delete/tutorials")
	public String delete() {
		tutorialRepository.deleteAll();
		return "deleted succeffully";
	}

	@PutMapping("/update/tutorials/{id}")
	public String removeById(@RequestBody Request request, @PathVariable("id") long id) {
		Tutorial tutorials = new Tutorial();
		Optional<Tutorial> tutorial_ = tutorialRepository.findById(id);
		if (tutorial_.isPresent()) {
			tutorials.setDescription(request.getDescription());
			tutorials.setTitle(request.getTitle());
			tutorials.setPublished(request.getPublished());
			tutorialRepository.save(tutorials);
		}

		return "updated successfully";

	}

}
