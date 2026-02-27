package org.example.proyectoaccesoadatos.Controladores;

import org.example.proyectoaccesoadatos.Servicios.PropietarioServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
@WebMvcTest(PropietarioControlador.class)
public class PropietarioControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropietarioServicio propietarioServicio;

    @Test
    public void obtenerPropietarios_RetornaStatus200() throws Exception {
        when(propietarioServicio.obtenerTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/propietarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}