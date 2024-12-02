package simulator;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in); // Scanner global
    private static final Random random = new Random(); // Generador de números aleatorios

    public static void main(String[] args) {
        
    }
    
    private static boolean randomEvents() throws InterruptedException {
        int randomEvent = random.nextInt(4); // Selección aleatoria del evento
        switch (randomEvent) {
            case 0 -> {
                return motorOff(scanner, random);
            }
            case 1 -> {
                return overload(scanner, random);
            }
            case 2 -> {
                return motorFailure(scanner, random);
            }
            case 3 -> {
                return asteroidRain(scanner, random);
            }
            default -> {
                System.err.println("Error: evento aleatorio no válido.");
                return false;
            }
        }
    }

    private static boolean motorOff(Scanner scanner, Random random) throws InterruptedException {
        System.out.println("|-----------------------------------|");
        System.out.println("|             ALERTA!!!             |");
        System.out.println("|-----------------------------------|");
        System.out.println("|    Tu nave presenta fallas y      |");
        System.out.println("|    se apagara si no corriges      |");
        System.out.println("|             la falla              |");
        System.out.println("|-----------------------------------|");
        pressEnter(scanner);

        int seconds = 15;
        String[] operation = { "7 x 8", "8 x 8", "7 x 7", "77 x 88" };
        int[] result = { 7 * 8, 8 * 8, 7 * 7, 77 * 88 };
        int randomIndex = random.nextInt(operation.length); // Seleccionar operación aleatoria

        boolean solved = false; // Bandera para verificar si se resolvió correctamente
        String userResponse = ""; // Para guardar la respuesta del usuario

        // Iniciar el temporizador
        long startTime = System.currentTimeMillis(); // Capturar el tiempo de inicio

        while (seconds >= 0) {
            // Limpiar la consola solo al final del ciclo
            if (seconds != 15) {
                clearScreen();
            }

            // Mostrar el mensaje de operación y el tiempo restante
            System.out.println("|-----------------------------------|");
            System.out.println("|       para corregirlo resuelve    |");
            System.out.println("|       esta operacion tienes:      |");
            System.out.println("|            " + seconds + " segundos            |");
            System.out.println("|-----------------------------------|");
            System.out.println("|       ¿Cuánto es " + operation[randomIndex] + "?           |");
            System.out.println("|-----------------------------------|");
            System.out.print("   \nRespuesta: " + (userResponse.isEmpty() ? "" : userResponse)); // Mostrar la respuesta
                                                                                                 // o espacio vacío

            // Leer la respuesta del usuario
            if (scanner.hasNext()) {
                String scannerResponse = scanner.next(); // Captura lo que el usuario ingresa

                // Verificar si la entrada es un número entero
                try {
                    int answer = Integer.parseInt(scannerResponse); // Intenta convertir la respuesta a un número entero
                    userResponse = scannerResponse; // Actualiza la respuesta del usuario

                    // Comprobar si la respuesta es correcta
                    if (answer == result[randomIndex]) {
                        solved = true;
                        break;
                    } else {
                        System.err.println("Respuesta incorrecta. Intenta nuevamente...");
                        userResponse = ""; // Borrar la respuesta incorrecta
                    }
                } catch (NumberFormatException e) {
                    // Si ocurre un error de formato (el usuario no ingresa un número), muestra un
                    // mensaje y sigue pidiendo una respuesta válida
                    System.err.println("Entrada no válida. Por favor ingresa un número.");
                }
            }

            // Calcular el tiempo restante
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Tiempo transcurrido en segundos
            seconds = 15 - (int) elapsedTime; // Actualizar los segundos restantes

            // Si el tiempo aún no ha pasado, esperar 1 segundo antes de la siguiente
            // actualización
            if (seconds >= 0) {
                Thread.sleep(1000); // Pausa de 1 segundo
            }
        }

        // Mensaje final dependiendo de si la respuesta fue correcta
        if (solved) {
            System.out.println("|-----------------------------------|");
            System.out.println("| ¡Felicidades! Has corregido la    |");
            System.out.println("|        falla de la nave.          |");
            System.out.println("|-----------------------------------|");
            return true; // Éxito
        } else {
            System.out.println("|-----------------------------------|");
            System.out.println("|        ¡Se acabó el tiempo!       |");
            System.out.println("|      La nave ha sido apagada.     |");
            System.out.println("|-----------------------------------|");
            scanner.close();
            return false; // Fallo// Termina el programa si el tiempo se agotó
        }
    }

    private static boolean overload(Scanner scanner, Random random) throws InterruptedException {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 }; // Números disponibles
        int[] sequence = new int[3]; // Secuencia aleatoria de 3 números

        // Crear la secuencia aleatoria
        for (int i = 0; i < sequence.length; i++) {
            int randomIndex = random.nextInt(numbers.length - i); // índice aleatorio
            sequence[i] = numbers[randomIndex];

            // Desplazar el número seleccionado al final para evitar repetirlo
            numbers[randomIndex] = numbers[numbers.length - i - 1];
        }

        System.out.println("|-------------------------------|");
        System.out.println("|           Alerta!!!           |");
        System.out.println("|     ¡Sobrecarga detectada!    |");
        System.out.println("|-------------------------------|");
        pressEnter(scanner);

        System.out.println("\nApaga los sistemas en este orden:");
        for (int num : sequence) {
            System.out.print(num + " ");
        }
        System.out.println();

        int timeRemaining = 9; // Tiempo total
        long startTime = System.currentTimeMillis(); // Tiempo inicial

        // Leer los números en el orden correcto
        int[] userResponse = new int[3]; // Respuestas del usuario

        for (int i = 0; i < sequence.length; i++) {
            // Calcular el tiempo restante en cada iteración
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Tiempo transcurrido
            timeRemaining = 9 - (int) elapsedTime; // Actualizar el tiempo restante

            if (timeRemaining <= 0) {
                System.out.println("|---------------------------------|");
                System.out.println("|       ¡Se acabó el tiempo!      |");
                System.out.println("|       Sobrecarga mantenida      |");
                System.out.println("|       ¡La nave explotará!       |");
                System.out.println("|---------------------------------|");
                return false; // Se acabó el tiempo, falla
            }

            System.out.println("|-----------------------|");
            System.out.println("    Tienes " + timeRemaining + " segundos   ");
            System.out.println("|-----------------------|");

            // Limpiar el buffer antes de leer la entrada
            scanner.nextLine(); // Limpiar el buffer antes de tomar la nueva entrada

            // Leer la entrada del usuario para el número
            System.out.print("\nIntroduce el número " + (i + 1) + ": ");

            while (!scanner.hasNextInt()) { // Verificar que la entrada es un número
                System.out.println("¡Entrada no válida! Por favor ingresa un número.");
                scanner.next(); // Descartar la entrada incorrecta
            }

            userResponse[i] = scanner.nextInt(); // Guardar el número

            // Verificar si la secuencia es correcta
            if (userResponse[i] != sequence[i]) {
                System.out.println("|---------------------------------|");
                System.out.println("|      Error en la secuencia.     |");
                System.out.println("|       Sobrecarga mantenida      |");
                System.out.println("|       ¡La nave explotará!       |");
                System.out.println("|---------------------------------|");
                return false; // Secuencia incorrecta
            }

            Thread.sleep(1000); // Esperar 1 segundo antes de la siguiente entrada
        }

        // Si todos los números fueron correctos
        System.out.println("\n|----------------------------|");
        System.out.println("|    ¡Sistemas apagados      |");
        System.out.println("|       correctamente!       |");
        System.out.println("|----------------------------|");
        return true; // Éxito
    }

    private static boolean motorFailure(Scanner scanner, Random random) {
        int code = random.nextInt(15) + 1; // Genera un código aleatorio entre 1 y 30.

        System.out.println("|-----------------------------------|");
        System.out.println("|             Alerta!!!             |");
        System.out.println("|-----------------------------------|");
        System.out.println("|         ¡Fallo de motor!          |");
        System.out.println("|-----------------------------------|");
        pressEnter(scanner);

        System.out.println("\n|-------------------------------------------|");
        System.out.println("|              Para repararlo              |");
        System.out.println("|       Adivina el número entre 1 y 15.     | ");
        System.out.println("|            Tienes 20 segundos.            |");
        System.out.println("|-------------------------------------------|");

        long startTime = System.currentTimeMillis(); // Tiempo inicial
        int timeLimit = 20; // Tiempo límite en segundos

        while (true) {
            // Calcular el tiempo restante
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Tiempo transcurrido en segundos
            int timeRemaining = timeLimit - (int) elapsedTime;

            // Verificar si el tiempo se ha agotado
            if (timeRemaining <= 0) {
                System.out.println("\n|-----------------------------------|");
                System.out.println("|   ¡Se acabó el tiempo! El motor  |");
                System.out.println("|      murió, tal como te pasará.   |");
                System.out.println("|-----------------------------------|");
                return false; // Termina el método indicando fallo
            }

            System.out.println("\n|---------------------------------------------|");
            System.out.println("         Tiempo restante: " + timeRemaining + " segundos  ");
            System.out.println("|---------------------------------------------|");
            System.out.print("\nIntroduce tu intento: ");

            try {
                String input = scanner.nextLine().trim(); // Usamos nextLine para capturar la entrada completa
                int attempt = Integer.parseInt(input); // Intentamos convertirlo a un número entero

                if (attempt == code) {
                    System.out.println("\n|-----------------------------------|");
                    System.out.println("|     ¡Código correcto! Motor       |");
                    System.out.println("|         restaurado.               |");
                    System.out.println("|-----------------------------------|");
                    return true; // Devuelve true si el código es correcto
                } else {
                    int difference = Math.abs(code - attempt);

                    if (difference <= 5) {
                        System.out.println("¡Estás muy cerca!");
                    } else if (difference <= 15) {
                        System.out.println("Estás cerca.");
                    } else {
                        System.out.println("Estás lejos.");
                    }

                    if (attempt < code) {
                        System.out.println("Te falta por llegar. Intenta un número mayor.");
                    } else {
                        System.out.println("Te pasaste. Intenta un número menor.");
                    }
                }
            } catch (NumberFormatException e) {
                // Captura la excepción si no se ingresa un número válido
                System.err.println("|-----------------------------------------|");
                System.err.println("|   ¡Error! Entrada no válida. Ingresa    |");
                System.err.println("|      un número entre 1 y 30.            |");
                System.err.println("|-----------------------------------------|");
            }
        }
    }

    private static boolean asteroidRain(Scanner scanner, Random random) throws InterruptedException {
        System.out.println("|-----------------------------------|");
        System.out.println("|             ALERTA!!!             |");
        System.out.println("|-----------------------------------|");
        System.out.println("|    Presentamos una lluvia de      |");
        System.out.println("|            meteoritos             |");
        System.out.println("|-----------------------------------|");
        pressEnter(scanner);

        int seconds = 10;
        String[] country = { "E.E.U.U", "COLOMBIA", "CHIPRE", "ARGENTINA", "RUMANIA", "INDONESIA" };
        String[] result = { "washington", "bogota", "nicosia", "buenos aires", "bucarest", "yakarta" };
        int randomIndex = random.nextInt(country.length);

        boolean solved = false;
        String userResponse = "";
        long startTime = System.currentTimeMillis();

        while (seconds >= 0) {
            System.out.println("|-----------------------------------|");
            System.out.println("|      Para activar el escudo       |");
            System.out.println("| Di la capital de: " + country[randomIndex] + "         |");
            System.out.println("| Tienes " + seconds + " segundos            |");
            System.out.println("|-----------------------------------|");
            System.out.print("\nRespuesta: " + (userResponse.isEmpty() ? "" : userResponse) + "\n");

            if (scanner.hasNextLine()) {
                userResponse = scanner.nextLine().trim();
                if (userResponse.equalsIgnoreCase(result[randomIndex])) {
                    solved = true;
                    break;
                } else {
                    System.out.println("Respuesta incorrecta. Intenta de nuevo");
                    userResponse = "";
                }
            }

            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            seconds = 10 - (int) elapsedTime;

            if (seconds > 0) {
                Thread.sleep(1000);
            }
        }

        if (solved) {
            System.out.println("|-----------------------------------|");
            System.out.println("|     ¡Felicidades! Has podido      |");
            System.out.println("|        activar el escudo          |");
            System.out.println("|-----------------------------------|");
            return true; // Éxito
        } else {
            System.out.println("|-----------------------------------|");
            System.out.println("|        ¡Se acabó el tiempo!       |");
            System.out.println("|      Prepárate para morir!!!      |");
            System.out.println("|-----------------------------------|");
            return false; // Fallo
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pressEnter(Scanner scanner) {
        System.out.print("Presiona ENTER para continuar");
        if (scanner.hasNextLine()) { // Verifica si hay una línea en el buffer
            scanner.nextLine(); // Consume esa línea
        }
    }
}
