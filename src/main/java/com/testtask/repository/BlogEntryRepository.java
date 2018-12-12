package com.testtask.repository;

import com.testtask.model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogEntryRepository extends JpaRepository<BlogEntity, Integer> {
}
