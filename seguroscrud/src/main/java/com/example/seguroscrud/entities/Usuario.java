package com.example.seguroscrud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(
        name = "usuario"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @SequenceGenerator(
            name = "usuario_sequence",
            sequenceName = "usuario_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usuario_sequence"
    )
    @Column(name = "usuario_id", updatable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, columnDefinition = "TEXT")
    private String nombre;

    @OneToMany(mappedBy = "usuario")
    private List<Seguro> seguros;
}
