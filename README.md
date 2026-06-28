CAFETERIADUOC
Es una aplicación de cafetería construida con un ecosistema de microservicios con Spring Boot, usando 
arquitectura cloud-native. El proyecto corresponde a una cafetería universitaria (DUOC), con registro de 
clientes, productos, ubicaciones y ventas. 

Utilizando las siguientes dependencias:
Java 21 con Spring Boot 4.0.6
Spring Cloud Netflix Eureka —>descubrimiento de servicios
Spring Cloud Gateway —>API Gateway centralizado
Spring Data JPA + MySQL —>persistencia
Flyway —>migraciones de base de datos
Lombok —>reducción de boilerplate
Spring HATEOAS —>respuestas con hipervínculos (JSON)
Springdoc OpenAPI / Swagger —>documentación de API
WebClient  —>comunicación reactiva entre microservicios
Data Fake —>generación de datos falsos en tests
Mockito —>pruebas unitarias

Microservicios,puertos y funciones:
eureka,         8761,		Registro de servicios 
gateway,        8080,		Punto de entrada único, enrutamiento y Swagger agregado 
mscliente,         0,		Gestión de clientes 
msproductos,       0,		Gestión de productos, ingredientes, recetas 
msubicacion,       0,		Gestión de regiones, comunas y cafeterías
msventa,           0,		Gestión de ventas y métodos de pago

Rutas del API Gateway:
mscliente
/api/v1/clientes/
/api/v1/regiones/
/api/v1/comunas/

msubicacion 
/api/v1/regiones/
/api/v1/comunas/
api/v1/cafeterias/

msproductos 
/api/v1/productos/
/api/v1/ingredientes/
/api/v1/tamanos/
/api/v1/tipos/
/api/v1/tipoingredientes/
/api/v1/pasosrecetas/

msventa 
/api/v1/ventas/
/api/v1/metodospago/
/api/v1/productoventa/

Modelo de datos:
mscliente
tabla: cliente
datos: cliente_id, nombre_cliente, rut_cliente, fecha_nacimiento, puntos_acumulables. 

msproductos
tablas: ingrediente, paso_receta, tamano, tipo, tipo_ingrediente, producto
tablas de relación: ingredientes, pasos, tamanos, tipos, tiposIngredientes

msubicacion
tablas: region, comuna y cafeteria con relaciones jerárquicas

msventa
tablas: metodopago, ventas, productoventa y metodospago 
tabla de relación: venta-métodopago

Comunicación entre microservicios:
EL microservicio msventa realiza llamadas reactivas a otros tres microservicios al construir el DTO de una 
venta:

Llama a msubicacion para obtener el nombre de la cafetería
Llama a mscliente para obtener el nombre del cliente
Llama a msproductos por cada producto vendido

Pruebas:
Cada microservicio tiene tests unitarios en la capa de servicio, usando Mockito para simular repositorios. 
msproductos es el más cubierto, con 11 clases de test (una por cada servicio). Los tests de msventa incluyen 
prueba del servicio de ventas con mocks de WebClient y repositorio.

Para iniciar el proyecto:
Hay un script iniciar-todo.bat que abre terminales para cada servicio en orden:
Eureka (espera 12 segundos para estabilizarse)
Gateway
mscliente, msproductos, msubicacion, msventa
El eureka queda disponible en: http://localhost:8761
El Swagger unificado queda disponible en: http://localhost:8080/swagger-ui/index.html
