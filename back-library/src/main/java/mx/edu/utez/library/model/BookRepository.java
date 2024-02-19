package mx.edu.utez.library.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameContainingIgnoreCase(String term);

    List<Book> findByAuthorContainingIgnoreCase(String term);

    List<Book> findByYearBetween(Date start, Date end);

    List<Book> findByGenreContainingIgnoreCase(String term);

    List<Book> findByOrderByYearDesc();

}
