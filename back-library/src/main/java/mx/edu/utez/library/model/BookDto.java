package mx.edu.utez.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDto {
    private Integer id;
    private String name;
    private String author;
    private String genre;
    private Integer year;


    public Book getLibro() {
        return new Book(
                this.id,
                this.name,
                this.author,
                this.genre,
                this.year,
                true
        );
    }
}
