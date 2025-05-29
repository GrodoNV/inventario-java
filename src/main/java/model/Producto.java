package model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Producto implements Serializable {
    protected String nombre;
    protected int cantidad;
    protected LocalDate fechaRegistro;

    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaRegistro = LocalDate.now();
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public abstract String getTipo(); // Polimorfismo

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Cantidad: " + cantidad + ", Fecha Registro: " + fechaRegistro;
    }
}
