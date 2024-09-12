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

    @Column(name = "tipo", nullable = false, columnDefinition = "TEXT")
    private String tipo;

    @Column(name = "fecha_inicio", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaInicio;
    @Column(name = "fecha_vencimiento", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaVencimiento;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "detalle1", nullable = false, columnDefinition = "TEXT")
    private String detalle1;
    @Column(name = "detalle2", columnDefinition = "TEXT")
    private String detalle2;
}
