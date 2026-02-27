package org.example.proyectoaccesoadatos.Controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.proyectoaccesoadatos.Modelos.Actualizar.PropietarioUpdateDTO;
import org.example.proyectoaccesoadatos.Modelos.Crear.PropietarioCreateDTO;
import org.example.proyectoaccesoadatos.Modelos.DTOS.PropietarioDTO;
import org.example.proyectoaccesoadatos.Servicios.PropietarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propietarios")
public class PropietarioControlador {

    @Autowired
    private PropietarioServicio propietarioServicio;

    @Operation(summary = "Obtener todos los propietarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve la lista de propietarios.")
    })
    @GetMapping
    public List<PropietarioDTO> propietarios() {
        return propietarioServicio.obtenerTodos();
    }

    @Operation(summary = "Crear un nuevo propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Propietario creado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos.", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PropietarioDTO> crearPropietario(@RequestBody PropietarioCreateDTO Propietariocreate) {
        try {
            PropietarioDTO nuevoPropietario = propietarioServicio.crearPropietario(Propietariocreate);
            return new ResponseEntity<>(nuevoPropietario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Actualizar un propietario existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Propietario actualizado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o ID no encontrado.", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PropietarioDTO> ActualizarEstudiante(@PathVariable("id") long id, @RequestBody PropietarioUpdateDTO estudianteUpdateDTO) {
        try {
            PropietarioDTO EstudianteActualizado = propietarioServicio.actualizarPropietario(id, estudianteUpdateDTO);
            return new ResponseEntity<>(EstudianteActualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Eliminar un propietario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Propietario eliminado exitosamente (Sin contenido).")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        propietarioServicio.EliminarPropietario(id);
        return ResponseEntity.noContent().build();
    }
}