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

    private String imdbId;

    private String nameRu;

    private Integer year;

    private Integer ratingImdb;

    private String nameOriginal;

    public Film(String imdbId, String nameRu, Integer year, Integer ratingImdb, String nameOriginal){
        this.nameOriginal = nameOriginal;
        this.imdbId = imdbId;
        this.nameRu = nameRu;
        this.year = year;
        this.ratingImdb = ratingImdb;
    }
}
