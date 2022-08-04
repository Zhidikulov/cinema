package cinema.controller;

import cinema.model.Film;
import cinema.model.FilmParamDto;
import cinema.model.FilmRequestDto;
import cinema.model.FilmResponseDto;
import cinema.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final Environment env;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public FilmResponseDto addFilm(@ModelAttribute FilmRequestDto filmRequestDto) {


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
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FilmResponseDto> response = restTemplate.exchange(builder, HttpMethod.GET, req, FilmResponseDto.class);
        FilmResponseDto film = response.getBody();
        Set<Film> f = film.getItems();
        for (Film a : f) {
            filmService.add(a);
        }
        return film;
    }

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public List<Film> getFilm(@ModelAttribute FilmParamDto f){
      List<Film> films = filmService.filtr(f);
      return films;
    }

}