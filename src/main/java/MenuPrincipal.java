import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        boolean salir = false;

        while (!salir) {

            System.out.println("\n=== AGENDA DE CONTACTOS ===");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Listar contactos (orden alfabetico)");
            System.out.println("5. Listar historial (orden de insercion)");
            System.out.println("6. Agregar grupo");
            System.out.println("7. Agregar contacto a grupo");
            System.out.println("8. Listar grupos");
            System.out.println("9. Mostrar contactos por grupo");
            System.out.println("10. Salir");
            System.out.println("==========================");
            System.out.print("Elige una opcion: ");

            // Validar que la entrada sea un numero entero
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Error: debes ingresar un numero entero.");
                continue;
            }

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de linea

            switch (opcion) {

                case 1:
                    System.out.println("\n--- AGREGAR CONTACTO ---");

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Telefono: ");
                    String telefono = scanner.nextLine();

                    System.out.print("Correo electronico: ");
                    String email = scanner.nextLine();

                    Contacto nuevoContacto =
                            new Contacto(nombre, telefono, email);

                    agenda.agregarContacto(nuevoContacto);
                    break;

                case 2:
                    System.out.println("\n--- BUSCAR CONTACTO ---");

                    System.out.print("Nombre del contacto: ");
                    nombre = scanner.nextLine();

                    Contacto encontrado = agenda.buscarContacto(nombre);

                    if (encontrado != null) {
                        System.out.println(encontrado);
                    }
                    break;

                case 3:
                    System.out.println("\n--- ELIMINAR CONTACTO ---");

                    System.out.print("Nombre del contacto: ");
                    nombre = scanner.nextLine();

                    if (agenda.eliminarContacto(nombre)) {
                        System.out.println("Contacto eliminado correctamente.");
                    } else {
                        System.out.println("No se pudo eliminar el contacto.");
                    }
                    break;

                case 4:
                    agenda.listarContactos();
                    break;

                case 5:
                    agenda.listarHistorial();
                    break;

                case 6:
                    System.out.println("\n--- AGREGAR GRUPO ---");

                    System.out.print("Nombre del grupo: ");
                    String nombreGrupo = scanner.nextLine();

                    agenda.agregarGrupo(nombreGrupo);
                    break;

                case 7:
                    System.out.println("\n--- AGREGAR CONTACTO A GRUPO ---");

                    System.out.print("Nombre del contacto: ");
                    nombre = scanner.nextLine();

                    System.out.print("Nombre del grupo: ");
                    nombreGrupo = scanner.nextLine();

                    agenda.agregarContactoAGrupo(nombre, nombreGrupo);
                    break;

                case 8:
                    agenda.listarGrupos();
                    break;

                case 9:
                    System.out.println("\n--- CONTACTOS POR GRUPO ---");

                    System.out.print("Nombre del grupo: ");
                    nombreGrupo = scanner.nextLine();

                    agenda.mostrarContactosPorGrupo(nombreGrupo);
                    break;

                case 10:
                    salir = true;
                    System.out.println("Gracias por utilizar la agenda. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println(
                            "Opcion invalida. Elige un numero entre 1 y 10."
                    );
                    break;
            }
        }

        scanner.close();
    }
}