package com.github.thfm4rt1n5.services;

import java.util.List;

import com.github.thfm4rt1n5.entities.Variety;
import com.github.thfm4rt1n5.repositories.VarietyRepository;

public class VarietyService {

	private final VarietyRepository repository;

	public VarietyService(VarietyRepository repository) {
		this.repository = repository;
	}
	
	public List<Variety> findAll() {
		return repository.findAll();
	}
	
	public Variety findById(final int id) {
		return repository.findById(id).get();
	}
}
