package cinema.dao;

import cinema.model.Film;
import cinema.model.FilmParamDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FilmDaoImp {


    private final EntityManager en;


    public List<Film> filtr(FilmParamDto f) {
        int pageNumber = 1;
        int pageSize = 20;
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
            pr.add(root.get("nameRu").in(str));
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
        while(pageNumber < count.intValue()){
            query.setFirstResult(pageNumber - 1);
            query.setMaxResults(pageSize);
            pageNumber += pageSize;
            return query.getResultList();

        }
        films = query.getResultList();
        return films;
    }
}
