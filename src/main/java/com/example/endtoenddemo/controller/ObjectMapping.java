package com.example.endtoenddemo.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.endtoenddemo.entity.model.Tutorial;
import com.example.endtoenddemo.model.Response;
import com.example.endtoenddemo.repository.TutorialRepository;

import com.example.endtoenddemo.model.Request;

@Component
public class ObjectMapping {
	@Autowired
	TutorialRepository tutorialRepository;
	
public Tutorial requestMappingToEntityModel(Request request) {
	Tutorial tutorial=new Tutorial();
	tutorial.setDescription(request.getDescription());
	tutorial.setTitle(request.getTitle());
	tutorial.setPublished(request.getPublished());
	return tutorial;
}
public Response entityModelMappingToResponse(Tutorial tutorial) {
	Response response=new Response();
	response.setDescription(tutorial.getDescription());
	response.setTitle(tutorial.getTitle());
	response.setPublished(tutorial.getPublished());
	return response;
}
}

