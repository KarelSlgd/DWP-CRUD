package mx.edu.utez.library.service;

import mx.edu.utez.library.utils.Response;
import mx.edu.utez.library.model.Book;
import mx.edu.utez.library.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional(readOnly = true)
    public Response<List<Book>> getAll() {
        return new Response<>(
                this.bookRepository.findAll(),
                false,
                200,
                "Ok"
        );
    }


    @Transactional(readOnly = true)
    public Response<Book> getOne(Integer id) {
        return new Response<>(
                this.bookRepository.findById(id).orElse(null),
                false,
                200,
                "Ok"
        );
    }


    @Transactional(rollbackFor = {SQLException.class})
    public Response<Book> insert(Book libro) {
           return new Response<>(
                    this.bookRepository.save(libro),
                    false,
                    200,
                    "Libro registrado correctamente"
            );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Book> update(Book libro) {
        if (this.bookRepository.existsById(libro.getId())) {
            return new Response<>(
                    this.bookRepository.save(libro),
                    false,
                    200,
                    "Libro actualizado correctamente"
            );
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "No se encontro el libro"
            );
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> delete(Integer id) {
        if (this.bookRepository.existsById(id)) {
            this.bookRepository.deleteById(id);
            return new Response<>(
                    true,
                    false,
                    200,
                    "Libro eliminado correctamente"
            );
        } else {
            return new Response<>(
                    false,
                    true,
                    400,
                    "No se encontro el libro"
            );
        }
    }
    @Transactional(readOnly = true)
    public Response<List<Book>> queryMethod(String name, String author, String genre, Date startYear, Date endYear) {
        if (name != null && !name.isEmpty()) {
                return new Response<>(bookRepository.findByNameContainingIgnoreCase(name), false, 200, "Encontrado");
        } else if (author != null && !author.isEmpty()) {
            return new Response<>(bookRepository.findByAuthorContainingIgnoreCase(author), false, 200, "Encontrado");
        } else if (genre != null && !genre.isEmpty()) {
            return new Response<>(bookRepository.findByGenreContainingIgnoreCase(genre), false, 200, "Encontrado");
        } else if (startYear != null && endYear != null) {
            return new Response<>(bookRepository.findByYearBetween(startYear, endYear), false, 200, "Encontrado");
        } else {
            return new Response<>(bookRepository.findAll(), false, 200, "Encontrado");
        }
    }
}
