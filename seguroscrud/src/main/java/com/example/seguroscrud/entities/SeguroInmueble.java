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
        name = "seguro_inmueble"
)
@DiscriminatorValue("INMUEBLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeguroInmueble extends Seguro {
    @Column(name = "direccion_inmueble", nullable = false, columnDefinition = "TEXT")
    private String direccionInmueble;

    @Column(name = "area_inmueble", nullable = false, columnDefinition = "TEXT")
    private String areaInmueble;
}
