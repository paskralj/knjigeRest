package com.suba.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Book entities.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
