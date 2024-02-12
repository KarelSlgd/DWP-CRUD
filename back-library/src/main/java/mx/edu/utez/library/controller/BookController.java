package mx.edu.utez.library.controller;

import mx.edu.utez.library.CustomResponse;
import mx.edu.utez.library.model.Book;
import mx.edu.utez.library.model.BookDto;
import mx.edu.utez.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/")
@CrossOrigin(origins = {"*"})
public class BookController {

    @Autowired
    private BookService libroService;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Book>>> getAll() {
        return new ResponseEntity<>(
                this.libroService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Book>> getById(
            @PathVariable("id")
            Integer id) {
        return new ResponseEntity<>(
                this.libroService.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Book>> insert(@RequestBody BookDto book) {
        return new ResponseEntity<>(
                this.libroService.insert(book.getLibro()), HttpStatus.CREATED
        );
    }


    @PutMapping("/")
    public ResponseEntity<CustomResponse<Book>> update(@RequestBody BookDto bk) {
        Book book = bk.getLibro();
        return new ResponseEntity<>(
                this.libroService.update(book), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(
                this.libroService.delete(id),
                HttpStatus.OK
        );
    }
}
