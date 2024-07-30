package com.psuti.books.repository;

import com.psuti.books.model.BookLiterary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLiteraryRepository extends JpaRepository<BookLiterary, Long> {
}
