package yo.app.service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import yo.app.entidades.Juego;
import yo.app.repositories.JuegoRepository;

import java.util.Arrays;
import java.util.List;

@Service

public class JuegoServiceImpl implements JuegoService {

    private final JuegoRepository juegoRepository;

    public  JuegoServiceImpl(JuegoRepository juegoRepository){
        this.juegoRepository = juegoRepository;
    }

    @Override
    public Juego add(Juego entity) {
        // probe esto utilizando el existByNombre en la capa de repositories
        if (juegoRepository.existsByNombre(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe");
        }
        return this.juegoRepository.save(entity);
    }

    @Override
    // en eso se podria separar la logica y dsp crear un endpoint diferente para cada parte q se quiere modificar, seguramente lo haga pero mientras tanto paja
    public Juego update(Juego entity) {
        Juego juegoExistente = this.getByNombre(entity.getNombre());
        if (juegoExistente != null){
            juegoExistente.setNombre(entity.getNombre());
            juegoExistente.setHistoria(entity.getHistoria());
            juegoExistente.setPersonaje(entity.getPersonaje());
            juegoExistente.setBandaSonora(entity.getBandaSonora());
            juegoExistente.setGameplay(entity.getGameplay());
            juegoExistente.setApartadoGrafico(entity.getApartadoGrafico());
            this.juegoRepository.save(juegoExistente);
            return juegoExistente;}
        else {
            throw new EntityNotFoundException("no existe ese juego");
        }
    }

    @Override
    public Juego updatePersonaje(String nombre, float personaje) {
        Juego juegoExistente = this.getByNombre(nombre);
        if (juegoExistente != null){
            juegoExistente.setPersonaje(personaje);
            this.juegoRepository.save(juegoExistente);
            return juegoExistente;
        } else {
            throw new EntityNotFoundException("No existe juego con ese nombre");
        }
    }

    @Override
    public Juego delete(Long id) {
        Juego juego = this.getById(id);
        if (juego != null){
            this.juegoRepository.delete(juego);
            return juego;
        } else {
            throw new EntityNotFoundException("No existe juego con esa id");
        }
    }

    @Override
    public Juego getById(Long id) {
        return this.juegoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Juego> getAll() {
        return this.juegoRepository.findAll();
    }

    @Override
    public Juego getByNombre(String nombre) {
        return this.juegoRepository.findByNombre(nombre);
    }

    @Override
    public float getPuntos(String nombre) {
        Juego juego = this.getByNombre(nombre);
        if (juego == null) {
            throw new EntityNotFoundException("Juego no encontrado");
        }

        List<Float> valores = Arrays.asList(
                juego.getPersonaje(),
                juego.getHistoria(),
                juego.getBandaSonora(),
                juego.getGameplay(),
                juego.getApartadoGrafico()
        );

        double suma = valores.stream().filter(v -> v > 0).mapToDouble(Float::floatValue).sum();
        long contador = valores.stream().filter(v -> v > 0).count();

        return contador > 0 ? (float) (suma / contador) : 0f;
    }
}
