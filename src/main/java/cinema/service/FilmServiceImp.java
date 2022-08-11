package cinema.service;

import cinema.client.Email;
import cinema.client.FilmClient;
import cinema.dao.FilmDao;
import cinema.dao.FilmDaoImp;
import cinema.model.*;
import com.thoughtworks.xstream.XStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FilmServiceImp implements FilmService{


    private final FilmDao filmDao;
    private  final FilmDaoImp filmDaoImp;
    private final FilmClient filmClient;
    private final XStream xStream;
    private final Email email;



    @Override
    public List<Film> filtr(FilmParamDto f) {
        return filmDaoImp.filtr(f);
    }



    @Override
    public FilmResponseDto addFilm(FilmRequestDto filmRequestDto){
        FilmResponseDto frd = filmClient.restFilm(filmRequestDto);
        List<Film> films = frd.getItems();
        for(Film f: films){
            Integer a = f.getKinopoiskId();
            if(!a.equals(filmDaoImp.idFilm(a))){
                filmDao.save(f);
            }
        }
        return frd;
    }

    @Override
    public void getEmail(FilmParamDto f) throws IOException, MessagingException {
        List<Film> films = filmDaoImp.filtrs(f);
        File f1 = new File("C:\\XML/file.xml");
        if(!f1.exists()){
            f1.createNewFile();
        }
        BufferedWriter file = new BufferedWriter(new FileWriter(f1));
        for(Film f2: films) {
            String dataXML = xStream.toXML(f2);
            file.write(dataXML);
        }
        file.close();

        email.getEmail(f.getTo());


    }


}
