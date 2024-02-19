package mx.edu.utez.library.controller;

import mx.edu.utez.library.utils.Response;
import mx.edu.utez.library.model.Book;
import mx.edu.utez.library.model.BookDto;
import mx.edu.utez.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/book/")
@CrossOrigin(origins = {"*"})
public class BookController {

    @Autowired
    private BookService libroService;

    @GetMapping("/")
    public ResponseEntity<Response<List<Book>>> getAll() {
        return new ResponseEntity<>(
                this.libroService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Book>> getById(
            @PathVariable("id")
            Integer id) {
        return new ResponseEntity<>(
                this.libroService.getOne(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/query")
    public ResponseEntity<Response<List<Book>>> queryMethod(
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam String genre,
            @RequestParam String startYear,
            @RequestParam String endYear) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = null;
        Date endDate = null;
        if (!startYear.isEmpty()) {
            Date parsedStartDate = inputFormat.parse(startYear);
            startDate = outputFormat.parse(outputFormat.format(parsedStartDate));
        }
        if (!endYear.isEmpty()) {
            Date parsedEndDate = inputFormat.parse(endYear);
            endDate = outputFormat.parse(outputFormat.format(parsedEndDate));
        }
        return new ResponseEntity<>(
                this.libroService.queryMethod(name, author, genre, startDate, endDate),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Response<Book>> insert(@RequestBody BookDto book) {
        return new ResponseEntity<>(
                this.libroService.insert(book.getLibro()), HttpStatus.CREATED
        );
    }


    @PutMapping("/")
    public ResponseEntity<Response<Book>> update(@RequestBody BookDto bk) {
        Book book = bk.getLibro();
        return new ResponseEntity<>(
                this.libroService.update(book), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(
                this.libroService.delete(id),
                HttpStatus.OK
        );
    }
}
