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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeguroServiceImpl implements SeguroService {

    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private SeguroRepository seguroRepository;

    @Override
    @Transactional
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

        return mapToSeguroDTO(getSeguroEntity(seguro.getId()));
    }

    @Override
    @Transactional
    public List<SeguroDTO> getAllSeguro() throws GeneralException {
        List<Seguro> allSeguros = seguroRepository.findAll();
        return allSeguros.stream().map(this::mapToSeguroDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SeguroDTO getSeguroById(Long id) throws GeneralException {
        return mapToSeguroDTO(getSeguroEntity(id));
    }

    @Override
    @Transactional
    public SeguroDTO updateSeguro(Long id, SeguroDTO seguroDTO) throws GeneralException {
        Seguro seguro = getSeguroEntity(id);

        seguro.setFechaInicio(seguroDTO.getFechaInicio() != null ? seguroDTO.getFechaInicio() : seguro.getFechaInicio());
        seguro.setFechaVencimiento(seguroDTO.getFechaVencimiento() != null ? seguroDTO.getFechaVencimiento() : seguro.getFechaVencimiento());
        seguro.setMonto(seguroDTO.getMonto() != null ? seguroDTO.getMonto() : seguro.getMonto());

        if (seguro instanceof SeguroAuto) {
            SeguroAuto seguroAuto = (SeguroAuto) seguro;
            if (seguroDTO.getModeloAuto() != null) {
                seguroAuto.setModeloAuto(seguroDTO.getModeloAuto());
            }
            if (seguroDTO.getMarcaAuto() != null) {
                seguroAuto.setMarcaAuto(seguroDTO.getMarcaAuto());
            }
        } else if (seguro instanceof SeguroInmueble) {
            SeguroInmueble seguroInmueble = (SeguroInmueble) seguro;
            if (seguroDTO.getDireccionInmueble() != null) {
                seguroInmueble.setDireccionInmueble(seguroDTO.getDireccionInmueble());
            }
            if (seguroDTO.getAreaInmueble() != null) {
                seguroInmueble.setAreaInmueble(seguroDTO.getAreaInmueble());
            }
        } else if (seguro instanceof SeguroCelular) {
            SeguroCelular seguroCelular = (SeguroCelular) seguro;
            if (seguroDTO.getMarcaCelular() != null) {
                seguroCelular.setMarcaCelular(seguroDTO.getMarcaCelular());
            }
            if (seguroDTO.getModeloCelular() != null) {
                seguroCelular.setModeloCelular(seguroDTO.getModeloCelular());
            }
        }

        try{
            seguro = seguroRepository.save(seguro);
        } catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }

        return mapToSeguroDTO(getSeguroEntity(seguro.getId()));
    }

    @Override
    @Transactional
    public void deleteSeguro(Long id) throws GeneralException {
        seguroRepository.deleteById(id);
    }

    private Seguro getSeguroEntity(Long id) throws GeneralException {
        return seguroRepository.findById(id).orElseThrow(() -> new NotFoundException("NOTFOUND-404", "SEGURO_NOTFOUND-404"));
    }

    private SeguroDTO mapToSeguroDTO(Seguro seguro) {

        SeguroDTO dto = modelMapper.map(seguro, SeguroDTO.class);

        if (seguro instanceof SeguroAuto) {
            dto.setTipo("AUTO");
        } else if (seguro instanceof SeguroInmueble) {
            dto.setTipo("INMUEBLE");
        } else if (seguro instanceof SeguroCelular) {
            dto.setTipo("CELULAR");
        }

        return dto;
    }
}
