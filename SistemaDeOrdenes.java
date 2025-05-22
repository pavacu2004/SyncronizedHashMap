import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SistemaDeOrdenes {

    // Mapa sincronizado para registrar órdenes
    static Map<Integer, String> ordenes = Collections.synchronizedMap(new HashMap<>());
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> registrarOrden();
                case 2 -> verOrdenes();
                case 3 -> eliminarOrden();
                case 4 -> System.out.println("Saliendo del sistema. ¡Hasta luego!");
                default -> System.out.println("️ Opción inválida. Intenta de nuevo.");
            }

        } while (opcion != 4);
    }

    public static void mostrarMenu() {
        System.out.println("\n=== MENÚ DE ÓRDENES ===");
        System.out.println("1. Registrar nueva orden");
        System.out.println("2. Ver todas las órdenes");
        System.out.println("3. Eliminar una orden");
        System.out.println("4. Salir");
        System.out.print("Elige una opción: ");
    }

    public static void registrarOrden() {
        try {
            System.out.print("Ingrese el número de orden: ");
            int numero = leerEntero();

            if (ordenes.containsKey(numero)) {
                System.out.println("️ Ya existe una orden con ese número.");
                return;
            }

            System.out.print("Ingrese el nombre de la orden: ");
            String nombre = scanner.nextLine();

            ordenes.put(numero, nombre);
            System.out.println(" Orden registrada con éxito.");

        } catch (Exception e) {
            System.out.println("️ Error al registrar la orden.");
        }
    }

    public static void verOrdenes() {
        System.out.println("\n== VER ÓRDENES REGISTRADAS ==");

        synchronized (ordenes) {
            if (ordenes.isEmpty()) {
                System.out.println("️ No hay órdenes registradas.");
            } else {
                ordenes.forEach((numero, nombre) ->
                        System.out.println(" Orden N° " + numero + ": " + nombre)
                );
            }
        }

        System.out.println("=============================");
    }

    public static void eliminarOrden() {
        try {
            System.out.print("Ingrese el número de la orden a eliminar: ");
            int numero = leerEntero();

            if (ordenes.containsKey(numero)) {
                ordenes.remove(numero);
                System.out.println("️ Orden eliminada con éxito.");
            } else {
                System.out.println("️ No se encontró una orden con ese número.");
            }

        } catch (Exception e) {
            System.out.println("️ Número inválido.");
        }
    }

    public static int leerEntero() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("️ Ingresa un número válido: ");
            }
        }
    }
}
