package cinema.service;

import cinema.dao.FilmDao;
import cinema.dao.FilmDaoImp;
import cinema.model.Film;
import cinema.model.FilmParamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmServiceImp implements FilmService{


    private final FilmDao filmDao;
    private  final FilmDaoImp filmDaoImp;

    @Override
    public void add(Film film) {
        filmDao.save(film);
    }

    @Override
    public List<Film> filtr(FilmParamDto f) {
        return filmDaoImp.filtr(f);
    }


}
