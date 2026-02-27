package org.example.proyectoaccesoadatos.Controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.proyectoaccesoadatos.Modelos.DTOS.PolizaDeSeguroDTO;
import org.example.proyectoaccesoadatos.Servicios.PolizaDeSeguroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/polizasdeseguro")
public class PolizaDeSeguroControlador {

    @Autowired
    private PolizaDeSeguroServicio polizaDeSeguroServicio;

    @Operation(summary = "Obtener todas las pólizas de seguro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve la lista de pólizas de seguro.")
    })
    @GetMapping
    public List<PolizaDeSeguroDTO> polizaDeSeguro() {
        return polizaDeSeguroServicio.obtenerTodos();
    }
}