package mx.edu.utez.library.service;

import mx.edu.utez.library.CustomResponse;
import mx.edu.utez.library.model.Book;
import mx.edu.utez.library.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Book>> getAll() {
        return new CustomResponse<>(
                this.bookRepository.findAll(),
                false,
                200,
                "Ok"
        );
    }


    @Transactional(readOnly = true)
    public CustomResponse<Book> getOne(Integer id) {
        return new CustomResponse<>(
                this.bookRepository.findById(id).orElse(null),
                false,
                200,
                "Ok"
        );
    }


    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Book> insert(Book libro) {
           return new CustomResponse<>(
                    this.bookRepository.save(libro),
                    false,
                    200,
                    "Libro registrado correctamente"
            );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Book> update(Book libro) {
        if (this.bookRepository.existsById(libro.getId())) {
            return new CustomResponse<>(
                    this.bookRepository.save(libro),
                    false,
                    200,
                    "Libro actualizado correctamente"
            );
        } else {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No se encontro el libro"
            );
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Integer id) {
        if (this.bookRepository.existsById(id)) {
            this.bookRepository.deleteById(id);
            return new CustomResponse<>(
                    true,
                    false,
                    200,
                    "Libro eliminado correctamente"
            );
        } else {
            return new CustomResponse<>(
                    false,
                    true,
                    400,
                    "No se encontro el libro"
            );
        }
    }

}
