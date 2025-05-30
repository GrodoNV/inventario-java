package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Producto;
import model.ProductoPerecedero;
import service.InventarioService;
import utils.ArchivoUtils;

import java.time.format.DateTimeFormatter;

public class InventarioController {

    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Integer> colCantidad;
    @FXML private TableColumn<Producto, String> colVencimiento;

    private final InventarioService servicio = new InventarioService();
    private final String RUTA = "src/main/resources/inventario.dat";

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colVencimiento.setCellValueFactory(cellData -> {
            Producto prod = cellData.getValue();
            if (prod instanceof ProductoPerecedero perecedero) {
                return new SimpleStringProperty(
                        perecedero.getFechaVencimiento().format(DateTimeFormatter.ISO_DATE)
                );
            } else {
                return new SimpleStringProperty("N/A");
            }
        });


        try {
            var productos = (java.util.List<Producto>) ArchivoUtils.cargarObjetos(RUTA);
            servicio.setProductos(productos);
            tablaProductos.getItems().setAll(productos);
        } catch (Exception e) {
            System.out.println("Inventario vacío o corrupto.");
        }
    }

    public void onAgregar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AgregarProducto.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Agregar Producto");

            // Pasar referencia al servicio y a la tabla
            AgregarProductoController controller = loader.getController();
            controller.setInventarioService(servicio);
            controller.setTablaProductos(tablaProductos);  // para refrescarla

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onEliminar() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            servicio.eliminarProducto(seleccionado.getNombre());
            tablaProductos.getItems().remove(seleccionado);
        }
    }

    public void onBuscar() {
        // Mostrar un input dialog para buscar por nombre
        System.out.println("Buscar producto");
    }

    public void onGuardar() {
        try {
            ArchivoUtils.guardarObjetos(servicio.getProductos(), RUTA);
            System.out.println("Datos guardados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}
