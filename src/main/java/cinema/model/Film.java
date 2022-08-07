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

    private String genres;

    public Film(Integer kinopoiskId, String nameRu, Integer year, Integer ratingImdb, String genres){
        this.genres = genres;
        this.kinopoiskId = kinopoiskId;
        this.nameRu = nameRu;
        this.year = year;
        this.ratingImdb = ratingImdb;
    }
}
