package yo.app.service;

import yo.app.entidades.Juego;

import java.util.List;

public interface Service <T,ID> {
    Juego add (T entity);
    T update(T entity);

    T updatePersonaje(String nombre, float personaje);

    T delete(ID id);
    T getById(ID id);

    List<T> getAll();

    T getByNombre(String nombre);

    float getPuntos(String nombre);

}
