#!/bin/bash

echo "Iniciando Servidor de Descubrimiento Eureka (Puerto 8761)..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/eureka\" && ./mvnw spring-boot:run"'

echo "Esperando 12 segundos a que Eureka se estabilice..."
sleep 12

echo "Iniciando API Gateway..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/gateway\" && ./mvnw spring-boot:run"'

echo "Iniciando Microservicio Cliente..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/mscliente\" && ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev"'

echo "Iniciando Microservicio Productos..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/msproductos\" && ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev"'

echo "Iniciando Microservicio Ubicacion..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/msubicacion\" && ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev"'

echo "Iniciando Microservicio Venta..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/msventa\" && ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev"'

echo "Ecosistema lanzado. Dashboard disponible en http://localhost:8761"