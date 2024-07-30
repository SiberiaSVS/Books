package com.psuti.books.repository;

import com.psuti.books.model.BookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookResponseRepository extends JpaRepository<BookResponse, Long> {
}
