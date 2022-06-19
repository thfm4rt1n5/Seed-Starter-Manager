package com.github.thfm4rt1n5.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.thfm4rt1n5.entities.SeedStarter;
import com.github.thfm4rt1n5.repositories.SeedStarterRepository;

@Service
public class SeedStarterService {

	private final SeedStarterRepository repository;
	
	public SeedStarterService(SeedStarterRepository repository) {
		
		this.repository = repository;
	}
	
	public List<SeedStarter> findAll() {
		return repository.findAll();
	}
	
	public void add(final SeedStarter seedStarter) {
		repository.add(seedStarter);
	}
}
