package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.RoleDTO;
import com.devsuperior.dscatalog.entities.Role;
import com.devsuperior.dscatalog.repositories.RoleRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@Service 
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	@Transactional(readOnly = true)
	public List<RoleDTO> findAll() {
		List<Role> list = repository.findAll();
		
		List<RoleDTO> listDto = list.stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
		
		return listDto;
	}

	@Transactional(readOnly = true)
	public RoleDTO findById(Long id) {
		
		Optional<Role> obj = repository.findById(id);
		Role entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new RoleDTO(entity);
	}
}