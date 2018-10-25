package com.springboot.crud.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springboot.crud.model.Invited;

public interface InvitedRepository extends CrudRepository<Invited, String>{

	public Optional<Invited> findByNationalID(String nationalID);
}
