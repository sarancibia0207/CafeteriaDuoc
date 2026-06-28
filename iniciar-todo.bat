@echo off

echo Iniciando Servidor de Descubrimiento Eureka...
cd eureka
start cmd /k "mvnw spring-boot:run"

echo Esperando 12 segundos a que Eureka se estabilice...
timeout /t 12 /nobreak > nul

echo Iniciando API Gateway...
cd ../gateway
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Cliente...
cd ../mscliente
start cmd /k "mvnw spring-boot:run -Dspring-boot.run.profiles=dev"

echo Iniciando Microservicio Productos...
cd ../msproductos
start cmd /k "mvnw spring-boot:run -Dspring-boot.run.profiles=dev"

echo Iniciando Microservicio Ubicacion...
cd ../msubicacion
start cmd /k "mvnw spring-boot:run -Dspring-boot.run.profiles=dev"

echo Iniciando Microservicio Venta...
cd ../msventa
start cmd /k "mvnw spring-boot:run -Dspring-boot.run.profiles=dev"

echo Ecosistema lanzado. Dashboard disponible en http://localhost:8761