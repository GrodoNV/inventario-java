package service;

import model.Producto;
import model.ProductoPerecedero;

import java.util.ArrayList;
import java.util.List;

public class InventarioService {
    private List<Producto> productos = new ArrayList<>();

    public void adicionarProducto(Producto p) {
        productos.add(p);
    }

    public boolean eliminarProducto(String nombre) {
        return productos.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public Producto buscarProducto(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void ordenarPorNombre() {
        productos.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
