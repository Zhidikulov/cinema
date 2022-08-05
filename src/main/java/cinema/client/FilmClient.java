package cinema.client;

import cinema.model.FilmRequestDto;
import cinema.model.FilmResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class FilmClient {

    private final Environment env;
    private final RestTemplate restTemplate;

    public FilmResponseDto restFilm(FilmRequestDto filmRequestDto){
        String urlFilms = "https://kinopoiskapiunofficial.tech/api/v2.2/films";



        String builder = UriComponentsBuilder
                .fromUriString(urlFilms)
                .queryParam("keyword", filmRequestDto.getKeyword())
                .queryParam("genre", filmRequestDto.getGenre())
                .queryParam("yearFrom", filmRequestDto.getYearFrom())
                .queryParam("ratingFrom", filmRequestDto.getRatingFrom())
                .queryParam("page", filmRequestDto.getPage())
                .build().toUriString();



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", env.getProperty("my_apikey"));
        HttpEntity<FilmResponseDto> req = new HttpEntity<>(headers);
        ResponseEntity<FilmResponseDto> response = restTemplate.exchange(builder, HttpMethod.GET, req, FilmResponseDto.class);
        FilmResponseDto film = response.getBody();
        return film;
    }
}
