package cinema.controller;

import cinema.model.Film;
import cinema.model.FilmParamDto;
import cinema.model.FilmRequestDto;
import cinema.model.FilmResponseDto;
import cinema.service.FilmService;
import com.thoughtworks.xstream.XStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @GetMapping("/")
    public FilmResponseDto addFilm(@ModelAttribute FilmRequestDto filmRequestDto) {
        FilmResponseDto film = filmService.addFilm(filmRequestDto);
        return film;
    }

    @GetMapping("/films")
    public List<Film> getFilm(@ModelAttribute FilmParamDto f) {
        List<Film> films = filmService.filtr(f);
        return films;
    }

    @GetMapping("/email")
    public void getEmail(@ModelAttribute FilmParamDto f) throws IOException, MessagingException {
        filmService.getEmail(f);
    }

}