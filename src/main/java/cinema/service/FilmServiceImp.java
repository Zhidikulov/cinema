package cinema.service;

import cinema.client.FilmClient;
import cinema.dao.FilmDao;
import cinema.dao.FilmDaoImp;
import cinema.model.Film;
import cinema.model.FilmParamDto;
import cinema.model.FilmRequestDto;
import cinema.model.FilmResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
        Set<Film> films = frd.getItems();
        for(Film f: films){
            Integer a = f.getKinopoiskId();
            if(a.equals(filmDaoImp.nameFilm(a)) != true){

                filmDao.save(f);
            }
        }
        return frd;
    }



}
