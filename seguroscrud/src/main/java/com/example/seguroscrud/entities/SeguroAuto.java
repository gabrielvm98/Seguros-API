package com.example.seguroscrud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "seguro_auto"
)
@DiscriminatorValue("AUTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeguroAuto extends Seguro {
    @Column(name = "marca_auto", nullable = false, columnDefinition = "TEXT")
    private String marcaAuto;

    @Column(name = "modelo_auto", nullable = false, columnDefinition = "TEXT")
    private String modeloAuto;
}
