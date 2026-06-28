package com.example.cafeteriaduoc.msventa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cafeteriaduoc.msventa.DTO.VentasDTO;
import com.example.cafeteriaduoc.msventa.model.Ventas;
import com.example.cafeteriaduoc.msventa.repository.VentasRepository;
import com.example.cafeteriaduoc.msventa.service.VentasService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class MsventaApplicationTests {

	// @Test
	// void contextLoads() {
	// }

   @Mock
   private VentasRepository ventasRepository; // Simulamos el acceso a la base de datos
  
   @InjectMocks
   private VentasService ventasService; // Inyectamos el Mock anterior dentro del servicio real
   private Faker faker = new Faker(); // Nuestro generador de datos de Star Wars
   @BeforeEach
   void setUp() {
       // Inicializa los componentes de simulación antes de ejecutar cada prueba
       MockitoAnnotations.openMocks(this);
   }


   @Test
   void testBuscarPorId_Exitoso() {
       // GIVEN: Dado un escenario inicial en la galaxia
       Integer idSimulado = 42;
       Double totalAleatorio = faker.number().randomDouble(2, 2000, 15000); // Genera un total simulado para la venta
       
       Ventas ventasFalso = new Ventas();
       ventasFalso.setVentaId(40);
       ventasFalso.setTotalVenta(totalAleatorio);
       ventasFalso.setFechaVenta(new Date(System.currentTimeMillis()));
       ventasFalso.setProductoVenta(new ArrayList<>());
       ventasFalso.setMetodosPago(new ArrayList<>());
       ventasFalso.setIdCliente(faker.number().numberBetween(1, 100));
       ventasFalso.setIdCafeteria(faker.number().numberBetween(1, 10));
       
       // Entrenamos al Mock: Cuando el repositorio busque este ID, responderá con nuestro Ventasfalso
       when(ventasRepository.findById(idSimulado)).thenReturn(Optional.of(ventasFalso));
       // WHEN: Cuando ejecutamos la acción del servicio que queremos evaluar


       VentasDTO resultado = ventasService.buscarPorId(idSimulado);
       // THEN: Entonces validamos que las compuertas de datos funcionen de forma idónea
       assertNotNull(resultado, "El DTO resultante no debería ser nulo");
       assertEquals(totalAleatorio, resultado.getTotalVenta(), "El total transformado al DTO debe coincidir con el de la DB");
       // Verificamos que el servicio realmente haya consultado al repositorio exactamente 1 vez
       verify(ventasRepository, times(1)).findById(idSimulado);
   }

}
