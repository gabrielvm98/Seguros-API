package com.example.seguroscrud.services;

import com.example.seguroscrud.dtos.SeguroDTO;
import com.example.seguroscrud.exceptions.GeneralException;

import java.util.List;

public interface SeguroService {
    
    SeguroDTO createSeguro(SeguroDTO seguroDTO) throws GeneralException;
    List<SeguroDTO> getAllSeguro() throws GeneralException;
    SeguroDTO getSeguroById(Long id) throws GeneralException;
    SeguroDTO updateSeguro(Long id, SeguroDTO seguroDTO) throws GeneralException;
    void deleteSeguro(Long id) throws GeneralException;
    
}
