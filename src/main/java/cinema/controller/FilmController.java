package cinema.controller;

import cinema.model.Film;
import cinema.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addFilm(String keyword){

        System.out.println(1);
        String urlFilms = "https://kinopoiskapiunofficial.tech/api/v2.2/films/?keyword="+keyword;
        String token = "fab0471f-1dfa-464b-8572-a78594f2ed5f";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-API-KEY", token);
        HttpEntity<Film> req = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Film> response = restTemplate.exchange(urlFilms, HttpMethod.GET, req, Film.class);
        Film film = response.getBody();
        filmService.add(film);
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void  addFilms(@ModelAttribute("keyword") String keyword){
        addFilm(keyword);
    }
}
