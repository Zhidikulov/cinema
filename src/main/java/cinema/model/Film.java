package cinema.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long filmId;

    private String filmName;

    private int year;

    private double rating;

    private String description;

    public Film(Long filmId, String filmName, int year, double rating, String description){
        this.description = description;
        this.filmId = filmId;
        this.filmName = filmName;
        this.year = year;
        this.rating = rating;
    }
}
