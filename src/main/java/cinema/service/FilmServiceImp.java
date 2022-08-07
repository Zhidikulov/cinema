package cinema.service;

import cinema.client.FilmClient;
import cinema.dao.FilmDao;
import cinema.dao.FilmDaoImp;
import cinema.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FilmServiceImp implements FilmService{


    private final FilmDao filmDao;
    private  final FilmDaoImp filmDaoImp;
    private final FilmClient filmClient;



    @Override
    public List<Film> filtr(FilmParamDto f) {
        return filmDaoImp.filtr(f);
    }

    @Override
    public FilmResponseDto addFilm(FilmRequestDto filmRequestDto){
        FilmResponseDto frd = filmClient.restFilm(filmRequestDto);
        List<FilmBody> films = frd.getItems();
        for(FilmBody f: films){
            Integer a = f.getKinopoiskId();
            if(a.equals(filmDaoImp.nameFilm(a)) != true){
                Film film = new Film(f.getKinopoiskId(), f.getNameRu(), f.getYear(), f.getRatingImdb(), String.join(", ", f.getGenres()));
                filmDao.save(film);
            }
        }
        return frd;
    }



}
