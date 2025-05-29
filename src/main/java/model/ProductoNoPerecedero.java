package model;

public class ProductoNoPerecedero extends model.Producto {

    public ProductoNoPerecedero(String nombre, int cantidad) {
        super(nombre, cantidad);
    }

    @Override
    public String getTipo() {
        return "No Perecedero";
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo: " + getTipo();
    }
}
