package com.devsuperior.dscatalog.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.dto.EmailDTO;

@RestController
@RequestMapping(value = "/emails")
public class EmailResource {
	
	@PostMapping
	public ResponseEntity<Void> send(@RequestBody EmailDTO dto) {
		
		
		return ResponseEntity.noContent().build();
	}
}
