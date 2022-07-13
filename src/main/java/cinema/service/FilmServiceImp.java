package cinema.service;

import cinema.dao.FilmDao;
import cinema.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmServiceImp implements FilmService{


    private final FilmDao filmDao;

    @Override
    public void add(Film film) {
        filmDao.save(film);
    }
}
