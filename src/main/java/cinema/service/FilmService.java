package cinema.service;


import cinema.model.Film;
import cinema.model.FilmParamDto;

import java.util.List;

public interface FilmService{
    void add(Film film);
    public List<Film> filtr(FilmParamDto f);

}
