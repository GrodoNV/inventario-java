package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Producto;
import model.ProductoPerecedero;
import service.InventarioService;

import java.time.LocalDate;

public class AgregarProductoController {

    @FXML private TextField txtNombre;
    @FXML private Spinner<Integer> spinnerCantidad;
    @FXML private DatePicker dateVencimiento;
    @FXML private Button btnGuardar;

    private InventarioService servicio;
    private TableView<Producto> tablaProductos;

    public void setInventarioService(InventarioService servicio) {
        this.servicio = servicio;
    }

    public void setTablaProductos(TableView<Producto> tablaProductos) {
        this.tablaProductos = tablaProductos;
    }

    @FXML
    public void initialize() {
        spinnerCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
    }

    @FXML
    public void guardarProducto() {
        String nombre = txtNombre.getText();
        int cantidad = spinnerCantidad.getValue();
        LocalDate vencimiento = dateVencimiento.getValue();

        if (nombre.isBlank()) {
            new Alert(Alert.AlertType.ERROR, "El nombre no puede estar vacío.").showAndWait();
            return;
        }

        Producto nuevo;
        if (vencimiento != null) {
            nuevo = new ProductoPerecedero(nombre, cantidad, vencimiento);
        } else {
            nuevo = new Producto(nombre, cantidad) {
                @Override public String getTipo() { return "No perecedero"; }
            };
        }

        servicio.adicionarProducto(nuevo);
        tablaProductos.getItems().add(nuevo);

        // Cerrar ventana
        ((Stage) btnGuardar.getScene().getWindow()).close();
    }
}
