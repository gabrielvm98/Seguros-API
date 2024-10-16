package com.example.seguroscrud.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeguroDTO {
    private Long id;
    private String tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private Double monto;
    private Long usuarioId;


    private String marcaAuto;
    private String modeloAuto;


    private String direccionInmueble;
    private String areaInmueble;


    private String marcaCelular;
    private String modeloCelular;
}
