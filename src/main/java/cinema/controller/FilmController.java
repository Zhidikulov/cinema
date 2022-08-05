package cinema.controller;

import cinema.model.Film;
import cinema.model.FilmParamDto;
import cinema.model.FilmRequestDto;
import cinema.model.FilmResponseDto;
import cinema.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public FilmResponseDto addFilm(@ModelAttribute FilmRequestDto filmRequestDto) {
        FilmResponseDto film = filmService.addFilm(filmRequestDto);
        return film;
    }

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public List<Film> getFilm(@ModelAttribute FilmParamDto f){
      List<Film> films = filmService.filtr(f);
      return films;
    }

}