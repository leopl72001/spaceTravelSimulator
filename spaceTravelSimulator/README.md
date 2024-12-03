# Simulador de Viajes Espaciales 🚀

Este proyecto es un simulador interactivo de viajes espaciales escrito en Java. Permite a los jugadores seleccionar planetas, naves espaciales y enfrentarse a diversos eventos aleatorios durante su viaje. El simulador combina elementos educativos y de entretenimiento para ofrecer una experiencia única.

## Características del Proyecto
- **Selección de destino**: Escoge entre tres planetas con distancias variables.
- **Selección de nave**: Elige entre varias naves espaciales con diferentes velocidades y capacidades.
- **Simulación del viaje**: Experimenta el progreso de tu aventura espacial.
- **Eventos aleatorios**:
  - **Falla de motor**: Resuelve operaciones matemáticas para arreglar tu nave.
  - **Sobrecarga**: Memoriza y reproduce una secuencia para estabilizar el sistema.
  - **Lluvia de meteoritos**: Identifica la capital correcta de un país.
  - **Adivina el código**: Encuentra un número secreto para reparar el motor.

## Estructura del Código
1. **Clase principal (`App`)**:
   - Contiene el flujo principal del programa y el menú interactivo.
2. **Métodos principales**:
   - `showMenu()`: Muestra las opciones disponibles para el jugador.
   - `selectPlanet()`: Gestiona la selección de planetas.
   - `selectShip()`: Permite seleccionar una nave espacial.
   - `travelSimulator()`: Controla el progreso del viaje y los eventos aleatorios.
   - `randomEvents()`: Ejecuta eventos aleatorios con lógica específica.

## Requisitos del Sistema
- **Java Development Kit (JDK)** versión 11 o superior.
- Un entorno de desarrollo como IntelliJ IDEA, Eclipse o NetBeans (opcional pero recomendado).

## Cómo Ejecutar el Programa
1. Asegúrate de tener instalado Java en tu sistema.
2. Compila el archivo fuente utilizando el comando:
   ```bash
   javac App.java