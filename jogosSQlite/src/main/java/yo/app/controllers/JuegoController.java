package yo.app.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yo.app.entidades.Juego;
import yo.app.service.JuegoService;

import java.util.List;

@RestController
@RequestMapping("/api/juego")
@Validated
public class JuegoController {
    private final JuegoService juegoService;

    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }
    @GetMapping
    public ResponseEntity<List<Juego>> getAll(){
        List<Juego> values = this.juegoService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid Juego juego){
        try {
            Juego jg = this.juegoService.add(juego);
            return ResponseEntity.status(HttpStatus.CREATED).body(juego);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            Juego result = this.juegoService.delete(id);
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        //Juego result = (Juego)this.juegoService.delete(id);
        //return result != null ? ResponseEntity.ok(result) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        // de esta forma se me rompia por como tenia la capa de servicio
    }

    @GetMapping("/api/juego/id/{id}")
    public ResponseEntity<Juego> getById(@PathVariable("id") long id) {
        Juego result = this.juegoService.getById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/api/juego/{nombre}")
    public ResponseEntity<Juego> getByNombre(@PathVariable String nombre) {
        Juego juego = juegoService.getByNombre(nombre);
        return juego != null ? ResponseEntity.ok(juego) : ResponseEntity.notFound().build();
    }
    @PutMapping("/api/juego/update")
    public ResponseEntity<Juego> update(@RequestBody @Valid Juego juego) {
        Juego updatedJuego = this.juegoService.update(juego);
        return updatedJuego != null ? ResponseEntity.ok(updatedJuego) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/api/juego/puntos/{nombre}")
    public ResponseEntity<?> getPuntos(@PathVariable String nombre) {
        try {
            float puntos = juegoService.getPuntos(nombre);
            return ResponseEntity.ok(puntos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("api/juego/updateJuego/{nombre}/{personaje}")
    public ResponseEntity <?> updatePersonaje(@PathVariable String nombre, @PathVariable float personaje){
        if (personaje < 0 || personaje > 10) {
            return ResponseEntity.badRequest().body("La puntación debe estar entre 0 y 10.");
        }
        try {
            Juego result = this.juegoService.updatePersonaje(nombre, personaje);
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
