package com.example.seguroscrud.controllers;

import com.example.seguroscrud.dtos.SeguroDTO;
import com.example.seguroscrud.exceptions.GeneralException;
import com.example.seguroscrud.exceptions.NotFoundException;
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

    @CrossOrigin(origins = "*") // Permitir todos los orígenes
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/polizas/{id}")
    public GeneralResponse<SeguroDTO> getSeguroById(@PathVariable Long id) throws GeneralException {
        return new GeneralResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                seguroService.getSeguroById(id));
    }

    @CrossOrigin(origins = "*") // Permitir todos los orígenes
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/polizas")
    public GeneralResponse<List<SeguroDTO>> getAllSeguro() throws GeneralException {
        return new GeneralResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                seguroService.getAllSeguro());
    }

    @CrossOrigin(origins = "*") // Permitir todos los orígenes
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/polizas")
    public GeneralResponse<SeguroDTO> createSeguro(@RequestBody SeguroDTO seguroDTO) throws GeneralException {
        return new GeneralResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                seguroService.createSeguro(seguroDTO));
    }

    @CrossOrigin(origins = "*") // Permitir todos los orígenes
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/polizas/{id}")
    public GeneralResponse<SeguroDTO> updateSeguro(@PathVariable Long id, @RequestBody SeguroDTO seguroDTO) throws GeneralException {
        return new GeneralResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                seguroService.updateSeguro(id, seguroDTO));
    }

    @CrossOrigin(origins = "*") // Permitir todos los orígenes
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/polizas/{id}")
    public GeneralResponse<String> deleteSeguro(@PathVariable Long id) throws GeneralException {
        try {
            seguroService.deleteSeguro(id);
            return new GeneralResponse<>("Success", "200", "OK", "Delete OK");
        } catch (NotFoundException e) {
            return new GeneralResponse<>("Error", "404", "NOT FOUND", e.getMessage());
        } catch (GeneralException e) {
            return new GeneralResponse<>("Error", "500", "INTERNAL SERVER ERROR", "An error occurred");
        }
    }
}
