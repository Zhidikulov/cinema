package cinema.client;

import cinema.model.FilmRequestDto;
import cinema.model.FilmResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FilmClient {

    private final Environment env;
    private final RestTemplate restTemplate;

    @SneakyThrows
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
        HttpEntity<String> req = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(builder, HttpMethod.GET, req, String.class);
        String str = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        FilmResponseDto film = mapper.readValue(str, FilmResponseDto.class);
        return film;
    }


}
