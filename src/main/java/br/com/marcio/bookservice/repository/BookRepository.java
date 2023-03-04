package br.com.marcio.bookservice.repository;

import br.com.marcio.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
