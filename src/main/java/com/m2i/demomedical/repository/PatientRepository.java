package com.m2i.demomedical.repository;


import com.m2i.demomedical.entities.PatientEntity;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<PatientEntity, Integer> {

	Optional<PatientEntity> findByEmail(String email);

}
