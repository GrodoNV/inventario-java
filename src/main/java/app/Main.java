package main.java.app;

import model.Producto;
import model.ProductoNoPerecedero;
import model.ProductoPerecedero;
import service.InventarioService;
import utils.ArchivoUtils;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InventarioService servicio = new InventarioService();
    private static final String RUTA_ARCHIVO = "src/main/resources/inventario.dat";

    public static void main(String[] args) {
        cargarDatos();

        int opcion;
        do {
            System.out.println("\n-- MENÚ DE INVENTARIO --");
            System.out.println("1. Adicionar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Listar productos");
            System.out.println("4. Buscar producto");
            System.out.println("5. Ordenar por nombre");
            System.out.println("6. Guardar y salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> adicionarProducto();
                case 2 -> eliminarProducto();
                case 3 -> servicio.listarProductos().forEach(System.out::println);
                case 4 -> buscarProducto();
                case 5 -> {
                    servicio.ordenarPorNombre();
                    System.out.println("Productos ordenados.");
                }
                case 6 -> guardarDatos();
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 6);
    }

    private static void adicionarProducto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("¿Es perecedero? (s/n): ");
        String tipo = scanner.nextLine();

        if (tipo.equalsIgnoreCase("s")) {
            System.out.print("Fecha de vencimiento (YYYY-MM-DD): ");
            LocalDate vencimiento = LocalDate.parse(scanner.nextLine());
            servicio.adicionarProducto(new ProductoPerecedero(nombre, cantidad, vencimiento));
        } else {
            servicio.adicionarProducto(new ProductoNoPerecedero(nombre, cantidad));
        }
        // Guardar inmediatamente después de agregar
        try {
            ArchivoUtils.guardarObjetos(servicio.getProductos(), RUTA_ARCHIVO);
            System.out.println("Producto guardado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    private static void eliminarProducto() {
        System.out.print("Nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();
        boolean eliminado = servicio.eliminarProducto(nombre);
        System.out.println(eliminado ? "Producto eliminado." : "Producto no encontrado.");
    }

    private static void buscarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        Producto p = servicio.buscarProducto(nombre);
        System.out.println(p != null ? p : "Producto no encontrado.");
    }

    private static void guardarDatos() {
        try {
            ArchivoUtils.guardarObjetos(servicio.getProductos(), RUTA_ARCHIVO);
            System.out.println("Datos guardados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void cargarDatos() {
        try {
            var productos = (java.util.List<Producto>) ArchivoUtils.cargarObjetos(RUTA_ARCHIVO);
            servicio.setProductos(productos);
        } catch (Exception e) {
            System.out.println("No se pudo cargar datos previos. Se iniciará un inventario nuevo.");
        }
    }
}
