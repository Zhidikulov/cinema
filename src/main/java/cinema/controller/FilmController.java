package cinema.controller;

import cinema.model.Film;
import cinema.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;


}
