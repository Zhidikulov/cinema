package cinema.client;

import cinema.model.FilmRequestDto;
import cinema.model.FilmResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class FilmClient {

    private final Environment env;
    private final RestTemplate restTemplate;
    private String urlFilms = "https://kinopoiskapiunofficial.tech";
    private String urlApi = "/api/v2.2/films";

    private String getUrlFilms(FilmRequestDto filmRequestDto){
        String builder = UriComponentsBuilder
                .fromUriString(urlFilms + urlApi)
                .queryParam("keyword", filmRequestDto.getKeyword())
                .queryParam("genre", filmRequestDto.getGenre())
                .queryParam("yearFrom", filmRequestDto.getYearFrom())
                .queryParam("ratingFrom", filmRequestDto.getRatingFrom())
                .queryParam("page", filmRequestDto.getPage())
                .build().toUriString();
        return builder;
    }

    @SneakyThrows
    public FilmResponseDto restFilm(FilmRequestDto filmRequestDto){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", env.getProperty("my_apikey"));
        HttpEntity<FilmResponseDto> req = new HttpEntity<>(headers);
        ResponseEntity<FilmResponseDto> response = restTemplate.exchange(getUrlFilms(filmRequestDto),
                HttpMethod.GET, req, FilmResponseDto.class);
        FilmResponseDto film = response.getBody();
        return film;
    }


}
