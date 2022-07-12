package cinema.dao;

import cinema.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FilmDao extends JpaRepository<Film, Long> {
}
