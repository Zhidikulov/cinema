package cinema.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Films")
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer kinopoiskId;

    private String nameRu;

    private Integer year;

    private Integer ratingImdb;

//    @ElementCollection
//    @CollectionTable(name="Genres_films", joinColumns=@JoinColumn(name="film_id"))
    private String nameOriginal;

    public Film(Integer kinopoiskId, String nameRu, Integer year, Integer ratingImdb, String nameOriginal){
        this.nameOriginal = nameOriginal;
        this.kinopoiskId = kinopoiskId;
        this.nameRu = nameRu;
        this.year = year;
        this.ratingImdb = ratingImdb;
    }
}
