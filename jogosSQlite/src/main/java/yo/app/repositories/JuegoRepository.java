package yo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yo.app.entidades.Juego;

@Repository
public interface JuegoRepository extends JpaRepository<Juego,Long> {
    Juego findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
