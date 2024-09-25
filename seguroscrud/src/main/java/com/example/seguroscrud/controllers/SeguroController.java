package com.example.seguroscrud.controllers;

import com.example.seguroscrud.dtos.SeguroDTO;
import com.example.seguroscrud.exceptions.GeneralException;
import com.example.seguroscrud.responses.GeneralResponse;
import com.example.seguroscrud.services.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/segurosAPI" + "/v1")
public class SeguroController {

    @Autowired
    private SeguroService seguroService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/seguro/{id}")
    public GeneralResponse<SeguroDTO> getSeguroById(@PathVariable Long id) throws GeneralException {
        return new GeneralResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                seguroService.getSeguroById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/seguro")
    public GeneralResponse<List<SeguroDTO>> getAllSeguro() throws GeneralException {
        return new GeneralResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                seguroService.getAllSeguro());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/seguro")
    public GeneralResponse<SeguroDTO> createSeguro(@RequestBody SeguroDTO seguroDTO) throws GeneralException {
        return new GeneralResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                seguroService.createSeguro(seguroDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/seguro/{id}")
    public GeneralResponse<SeguroDTO> updateSeguro(@PathVariable Long id, @RequestBody SeguroDTO seguroDTO) throws GeneralException {
        return new GeneralResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                seguroService.updateSeguro(id, seguroDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/seguro/{id}")
    public GeneralResponse<String> deleteSeguro(@PathVariable Long id) throws GeneralException {
        seguroService.deleteSeguro(id);
        return new GeneralResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                "Delete OK");
    }
}
