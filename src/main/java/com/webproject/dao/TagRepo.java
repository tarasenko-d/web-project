package com.webproject.dao;

import com.webproject.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TagRepo extends JpaRepository<Tag, Long> {

    List<Tag> findAllByTitleIn(Set<String> names);
}
