package com.webproject.dao;

import com.webproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Long> {

    Optional<Person> findById(Long id);
}
