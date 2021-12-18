package com.example.endtoenddemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.endtoenddemo.entity.model.Tutorial;
import com.example.endtoenddemo.model.Request;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
	List<Tutorial> findByPublished(String published);

	List<Tutorial> findByTitleContaining(String title);
	
}
