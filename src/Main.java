import java.io.EOFException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Estudiante> listaEstudiantes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("MENU");
            System.out.println("1. Ver estudiantes");
            System.out.println("2. Agregar estudiante");
            System.out.println("3. Editar estudiante");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Salir");
            System.out.print("\nOPCION: ");
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                opcion = 100;
                scanner.next();
            }

            switch (opcion) {
                case 1:
                    verEstudiante();
                    break;
                case 2:
                    agregarEstudiante(scanner);
                    break;
                case 3:
                    editarEstudiante(scanner);
                    break;
                case 4:
                    eliminarEstudiante(scanner);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("ERR::Opcion invalida");
            }
        } while (opcion != 5);
    }

    private static void verEstudiante() {
        for (Estudiante estudiante : listaEstudiantes) {
            System.out.println("\n" + estudiante);
            System.out.println("Nombre: " + estudiante.getNombre());
            System.out.println("Edad: " + estudiante.getEdad());
        }
    }

    private static void agregarEstudiante(Scanner scanner) {
        System.out.print("Nombre del estudiante: ");
        String nombre = scanner.next();
        System.out.print("Edad del estudiante: ");
        int edad;
        try {
            edad = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERR::Entrada invalida. Edad debe ser numero.");
            scanner.next();
            return;
        }

        listaEstudiantes.add(new Estudiante(nombre, edad));
    }

    private static void editarEstudiante(Scanner scanner) {
        System.out.print("Nombre del estudiante a editar: ");
        String nombre = scanner.next();
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getNombre().equals(nombre)) {
                System.out.println("Introduce nueva edad: ");
                int edad;
                try {
                    edad = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("ERR::Entrada invalida. Edad debe ser numero.");
                    scanner.next();
                    return;
                }
                estudiante.setEdad(edad);
                return;
            }
        }
        System.out.println("Estudiante no encontrado.");
    }

    private static void eliminarEstudiante(Scanner scanner) {
        System.out.print("Nombre del estudiante a eliminar: ");
        String nombre = scanner.next();
        listaEstudiantes.removeIf(estudiante -> estudiante.getNombre().equals(nombre));
    }
}
