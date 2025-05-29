package model;

import java.time.LocalDate;
public class ProductoPerecedero extends model.Producto {
    private LocalDate fechaVencimiento;

    public ProductoPerecedero(String nombre, int cantidad, LocalDate fechaVencimiento) {
        super(nombre, cantidad);
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String getTipo() {
        return "Perecedero";
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo: " + getTipo() + ", Vence: " + fechaVencimiento;
    }
}
