package com.example.seguroscrud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "seguro"
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seguro {
    @Id
    @SequenceGenerator(
            name = "seguro_sequence",
            sequenceName = "seguro_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seguro_sequence"
    )
    @Column(name = "seguro_id", updatable = false)
    private Long id;

    @Column(name = "fecha_inicio", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaInicio;
    @Column(name = "fecha_vencimiento", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaVencimiento;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

}
