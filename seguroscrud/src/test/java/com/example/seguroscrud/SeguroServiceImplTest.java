package com.example.seguroscrud;

import com.example.seguroscrud.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.seguroscrud.dtos.SeguroDTO;
import com.example.seguroscrud.entities.Seguro;
import com.example.seguroscrud.entities.SeguroAuto;
import com.example.seguroscrud.exceptions.GeneralException;
import com.example.seguroscrud.repositories.SeguroRepository;
import com.example.seguroscrud.services.Impl.SeguroServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SeguroServiceImplTest {

    @Mock
    private SeguroRepository seguroRepository;

    @InjectMocks
    private SeguroServiceImpl seguroService;

    private SeguroDTO seguroDTO;
    private SeguroAuto seguroAuto;


    @BeforeEach
    public void setup() {
        seguroDTO = new SeguroDTO();
        seguroDTO.setTipo("AUTO");
        seguroDTO.setFechaInicio(LocalDate.parse("2023-01-01"));
        seguroDTO.setFechaVencimiento(LocalDate.parse("2024-01-01"));
        seguroDTO.setMonto(1000.0);
        seguroDTO.setModeloAuto("Model X");
        seguroDTO.setMarcaAuto("Tesla");

        seguroAuto = new SeguroAuto();
        seguroAuto.setId(1L);
        seguroAuto.setFechaInicio(LocalDate.parse("2023-01-01"));
        seguroAuto.setFechaVencimiento(LocalDate.parse("2024-01-01"));
        seguroAuto.setMonto(1000.0);
        seguroAuto.setModeloAuto("Model X");
        seguroAuto.setMarcaAuto("Tesla");
    }

    // Test para createSeguro
    @Test
    public void createSeguro_ShouldReturnCreatedSeguroDTO() throws GeneralException {
        when(seguroRepository.save(any(Seguro.class))).thenReturn(seguroAuto);

        SeguroDTO createdSeguro = seguroService.createSeguro(seguroDTO);

        assertNotNull(createdSeguro);
        assertEquals("AUTO", createdSeguro.getTipo());
        assertEquals("Model X", createdSeguro.getModeloAuto());
        verify(seguroRepository, times(1)).save(any(Seguro.class));
    }

    // Tests para getSeguroById
    @Test
    public void getSeguroById_WhenIdExists_ShouldReturnSeguroDTO() throws GeneralException {
        when(seguroRepository.findById(1L)).thenReturn(Optional.of(seguroAuto));

        SeguroDTO foundSeguro = seguroService.getSeguroById(1L);

        assertNotNull(foundSeguro);
        assertEquals("AUTO", foundSeguro.getTipo());
        assertEquals("Tesla", foundSeguro.getMarcaAuto());
        verify(seguroRepository, times(1)).findById(1L);
    }

    @Test
    public void getSeguroById_WhenIdDoesNotExist_ShouldThrowNotFoundException() {
        when(seguroRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> seguroService.getSeguroById(1L));
        verify(seguroRepository, times(1)).findById(1L);
    }
}
