package yo.app.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JUEGOS")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Juego {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) (en algun momento tengo q ver como solucionar el auto incremental
    // pero sin el autoincremental no se me rompe
    private long id;
    @Basic
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;
    @Basic
    @Min(value = 0, message = "La puntacion debe ser mayor a 0")
    @Max(value = 10, message = "La puntacion no puede ser mayor que 10")
    private float personaje;
    @Basic
    @Min(value = 0, message = "La puntacion debe ser mayor a 0")
    @Max(value = 10, message = "La puntacion no puede ser mayor que 10")
    private float historia;
    @Column(name = "banda_sonora")
    @Min(value = 0, message = "La puntacion debe ser mayor a 0")
    @Max(value = 10, message = "La puntacion no puede ser mayor que 10")
    private float bandaSonora;
    @Basic
    @Min(value = 0, message = "La puntacion debe ser mayor a 0")
    @Max(value = 10, message = "La puntacion no puede ser mayor que 10")
    private float gameplay;
    @Min(value = 0, message = "La puntacion debe ser mayor a 0")
    @Max(value = 10, message = "La puntacion no puede ser mayor que 10")
    @Column(name = "apartado_grafico")
    private float apartadoGrafico;

}
