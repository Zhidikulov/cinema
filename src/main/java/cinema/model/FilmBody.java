package cinema.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
public class FilmBody implements Serializable {

    private Integer kinopoiskId;

    private String nameRu;

    private Integer year;

    private Integer ratingImdb;

    private List<String> genres;

    public FilmBody(Integer kinopoiskId, String nameRu, Integer year, Integer ratingImdb, List<String> genres) {
        this.genres = genres;
        this.kinopoiskId = kinopoiskId;
        this.nameRu = nameRu;
        this.year = year;
        this.ratingImdb = ratingImdb;
    }
}


