package com.example.seguroscrud.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "seguro_celular"
)
@DiscriminatorValue("CELULAR")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeguroCelular extends Seguro {
    @Column(name = "marca_celular", nullable = false, columnDefinition = "TEXT")
    private String marcaCelular;

    @Column(name = "modelo_celular", nullable = false, columnDefinition = "TEXT")
    private String modeloCelular;
}
