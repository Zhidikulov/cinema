package cinema.service;


import cinema.model.Film;
import cinema.model.FilmParamDto;
import cinema.model.FilmRequestDto;
import cinema.model.FilmResponseDto;

import java.util.List;

public interface FilmService{
    List<Film> filtr(FilmParamDto f);
    FilmResponseDto addFilm(FilmRequestDto filmRequestDto);

}
