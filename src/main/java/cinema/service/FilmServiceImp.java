package cinema.service;

import cinema.dao.FilmDao;
import cinema.model.Film;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class FilmServiceImp implements FilmService{

    private final FilmDao filmDao;

    @Override
    public void add(Film film) {
        filmDao.save(film);
    }
}
