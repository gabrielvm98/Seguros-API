package com.example.seguroscrud.services.Impl;

import com.example.seguroscrud.dtos.SeguroDTO;
import com.example.seguroscrud.entities.Seguro;
import com.example.seguroscrud.entities.SeguroAuto;
import com.example.seguroscrud.entities.SeguroCelular;
import com.example.seguroscrud.entities.SeguroInmueble;
import com.example.seguroscrud.exceptions.GeneralException;
import com.example.seguroscrud.exceptions.InternalServerErrorException;
import com.example.seguroscrud.exceptions.NotFoundException;
import com.example.seguroscrud.repositories.SeguroRepository;
import com.example.seguroscrud.services.SeguroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeguroServiceImpl implements SeguroService {

    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private SeguroRepository seguroRepository;

    @Override
    public SeguroDTO createSeguro(SeguroDTO seguroDTO) throws GeneralException {
        Seguro seguro = null;
        switch (seguroDTO.getTipo().toUpperCase()) {
            case "AUTO":
                SeguroAuto seguroAuto = new SeguroAuto();
                seguroAuto.setFechaInicio(seguroDTO.getFechaInicio());
                seguroAuto.setFechaVencimiento(seguroDTO.getFechaVencimiento());
                seguroAuto.setMonto(seguroDTO.getMonto());

                seguroAuto.setModeloAuto(seguroDTO.getModeloAuto());
                seguroAuto.setMarcaAuto(seguroDTO.getMarcaAuto());
                seguro = seguroAuto;
                break;

            case "INMUEBLE":
                SeguroInmueble seguroInmueble = new SeguroInmueble();
                seguroInmueble.setFechaInicio(seguroDTO.getFechaInicio());
                seguroInmueble.setFechaVencimiento(seguroDTO.getFechaVencimiento());
                seguroInmueble.setMonto(seguroDTO.getMonto());

                seguroInmueble.setDireccionInmueble(seguroDTO.getDireccionInmueble());
                seguroInmueble.setAreaInmueble(seguroDTO.getAreaInmueble());
                seguro = seguroInmueble;
                break;

            case "CELULAR":
                SeguroCelular seguroCelular = new SeguroCelular();
                seguroCelular.setFechaInicio(seguroDTO.getFechaInicio());
                seguroCelular.setFechaVencimiento(seguroDTO.getFechaVencimiento());
                seguroCelular.setMonto(seguroDTO.getMonto());

                seguroCelular.setMarcaCelular(seguroDTO.getMarcaCelular());
                seguroCelular.setModeloCelular(seguroDTO.getModeloCelular());
                seguro = seguroCelular;
                break;

            default:
                break;
        }

        try{
            seguro = seguroRepository.save(seguro);
        } catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }

        return modelMapper.map(getSeguroEntity(seguro.getId()),SeguroDTO.class);
    }

    @Override
    public List<SeguroDTO> getAllSeguro() throws GeneralException {
        List<Seguro> allSeguros = seguroRepository.findAll();
        return allSeguros.stream().map(seguro -> modelMapper.map(seguro, SeguroDTO.class)).collect(Collectors.toList());
    }

    @Override
    public SeguroDTO getSeguroById(Long id) throws GeneralException {
        return modelMapper.map(getSeguroEntity(id),SeguroDTO.class);
    }

    @Override
    public SeguroDTO updateSeguro(Long id, SeguroDTO seguroDTO) throws GeneralException {
        return null;
    }

    @Override
    public void deleteSeguro(Long id) throws GeneralException {

    }

    private Seguro getSeguroEntity(Long id) throws GeneralException {
        return seguroRepository.findById(id).orElseThrow(() -> new NotFoundException("NOTFOUND-404", "SEGURO_NOTFOUND-404"));
    }
}
