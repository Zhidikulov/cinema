package cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmParamDto {
    Integer ratingFrom;
    Integer ratingTo;
    Integer yearFrom;
    Integer yearTo;
    String keyword;
    String genre;
    int pageNumber;
    int pageSize;
    String to;
}
