package cinema.service;


import cinema.model.Film;
import cinema.model.FilmParamDto;
import cinema.model.FilmRequestDto;
import cinema.model.FilmResponseDto;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface FilmService{
    List<Film> filtr(FilmParamDto f);
    FilmResponseDto addFilm(FilmRequestDto filmRequestDto);
    void getEmail(FilmParamDto f) throws IOException, MessagingException;

}
