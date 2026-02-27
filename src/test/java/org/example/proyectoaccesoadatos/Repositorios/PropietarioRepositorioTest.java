package org.example.proyectoaccesoadatos.Repositorios;

import org.example.proyectoaccesoadatos.Repositorios.PropietarioRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PropietarioRepositorioTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    // Sobrescribe las credenciales para usar las del contenedor temporal
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private PropietarioRepositorio propietarioRepositorio;

    @Test
    public void findAllCompleto_SeEjecutaCorrectamente() {
        // Ejecuta la consulta personalizada con JOIN FETCH que creamos anteriormente
        var resultados = propietarioRepositorio.findAllCompleto();

        // Valida que la consulta a la base de datos no falla y devuelve una lista (aunque esté vacía)
        assertNotNull(resultados);
    }
}