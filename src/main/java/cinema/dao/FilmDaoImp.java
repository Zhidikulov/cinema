package cinema.dao;

import cinema.model.Film;
import cinema.model.FilmParamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FilmDaoImp {


    private final EntityManager en;
    private final FilmDao filmDao;


    public Integer idFilm(Integer id) {
        try {
            return filmDao.findByKinopoiskId(id).getKinopoiskId();
        } catch (NoResultException e) {
            return 0;
        } catch (NullPointerException e1) {
            return 0;
        }

    }

    public List<Film> filtrs(FilmParamDto f) {
        int pageNumber = f.getPageNumber();
        int pageSize = f.getPageSize();
        List<Film> films = new ArrayList<>();
        CriteriaBuilder cb = en.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(Film.class)));
        Long count = en.createQuery(cq).getSingleResult();
        CriteriaQuery<Film> cr = cb.createQuery(Film.class);
        Root<Film> root = cr.from(Film.class);
        List<Predicate> pr = new ArrayList<>();
        if (f.getKeyword() != null) {
            List<String> str = new ArrayList<>();
            str.add(f.getKeyword());
            for (String s : str) {
                pr.add(cb.like(root.get("nameRu"), "%" + s + "%"));
            }
        }
        if (f.getGenre() != null) {
            pr.add(cb.like(root.get("genres"), "%" + f.getGenre() + "%"));
        }
        if (f.getRatingFrom() != null) {
            pr.add(cb.gt(root.get("ratingImdb"), f.getRatingFrom()));
        }
        if (f.getRatingTo() != null) {
            pr.add(cb.lt(root.get("ratingImdb"), f.getRatingTo()));
        }
        if (f.getYearFrom() != null) {
            pr.add(cb.gt(root.get("year"), f.getYearFrom()));
        }
        if (f.getYearTo() != null) {
            pr.add(cb.lt(root.get("year"), f.getYearTo()));
        }
        cr.select(root).where(pr.toArray(new Predicate[0]));
        TypedQuery<Film> query = en.createQuery(cr);
        while (pageNumber < count.intValue()) {
            query.setFirstResult(pageNumber - 1);
            query.setMaxResults(pageSize);
            pageNumber += pageSize;
            return query.getResultList();
        }
        return films;
    }

    public List<Film> filtr(FilmParamDto f) {
        CriteriaBuilder cb = en.getCriteriaBuilder();
        CriteriaQuery<Film> cr = cb.createQuery(Film.class);
        Root<Film> root = cr.from(Film.class);
        List<Predicate> pr = new ArrayList<>();
        if (f.getKeyword() != null) {
            List<String> str = new ArrayList<>();
            str.add(f.getKeyword());
            for (String s : str) {
                pr.add(cb.like(root.get("nameRu"), "%" + s + "%"));
            }
        }
        if (f.getGenre() != null) {
            pr.add(cb.like(root.get("genres"), "%" + f.getGenre() + "%"));
        }
        if (f.getRatingFrom() != null) {
            pr.add(cb.gt(root.get("ratingImdb"), f.getRatingFrom()));
        }
        if (f.getRatingTo() != null) {
            pr.add(cb.lt(root.get("ratingImdb"), f.getRatingTo()));
        }
        if (f.getYearFrom() != null) {
            pr.add(cb.gt(root.get("year"), f.getYearFrom()));
        }
        if (f.getYearTo() != null) {
            pr.add(cb.lt(root.get("year"), f.getYearTo()));
        }
        cr.select(root).where(pr.toArray(new Predicate[0]));
        TypedQuery<Film> query = en.createQuery(cr);
        return query.getResultList();

    }
}
