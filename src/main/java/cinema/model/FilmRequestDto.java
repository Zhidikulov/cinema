package cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmRequestDto {
    String keyword;
    Integer genre;
    Integer ratingFrom;
    Integer yearFrom;
    int max;
    long offset;

}
