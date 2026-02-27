package org.example.proyectoaccesoadatos.Servicios;

import org.example.proyectoaccesoadatos.Modelos.Crear.PropietarioCreateDTO;
import org.example.proyectoaccesoadatos.Repositorios.PropietarioRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PropietarioServicioTest {

    @Mock
    private PropietarioRepositorio propietarioRepositorio;

    @InjectMocks
    private PropietarioServicio propietarioServicio;

    @Test
    public void crearPropietario_LanzaExcepcion_SiNombreEsNulo() {
        PropietarioCreateDTO dtoInvalido = new PropietarioCreateDTO();
        dtoInvalido.setDni("12345678A");

        ResponseStatusException excepcion = assertThrows(
                ResponseStatusException.class,
                () -> propietarioServicio.crearPropietario(dtoInvalido)
        );

        assertEquals(HttpStatus.BAD_REQUEST, excepcion.getStatusCode());
        assertEquals("El nombre es obligatorio", excepcion.getReason());
    }
}