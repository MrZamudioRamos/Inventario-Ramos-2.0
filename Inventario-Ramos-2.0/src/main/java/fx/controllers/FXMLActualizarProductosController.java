/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import dao.implementaciones.DAOProductoImpl;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Marca;
import model.Producto;
import model.TipoEstado;
import model.Ubicacion;
import model.User;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLActualizarProductosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField fxNombre;

    @FXML
    private TextField fxCategoria;

    @FXML
    private ComboBox<Marca> fxMarca;

    @FXML
    private TextField fxModelo;

    @FXML
    private TextField fxPrecio;

    @FXML
    private TextField fxDescripcion;

    @FXML
    private ComboBox<Ubicacion> fxUbicacion;

    @FXML
    private ComboBox<User> fxResponsable;

    @FXML
    private ComboBox<TipoEstado> fxEstado;

    @FXML
    private DatePicker fxFechaEntrada;

    @FXML
    private DatePicker fxFechaSalida;

    private FXMLPrincipalController principal;

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    public void cargar() {
        fxMarca.setItems(principal.getMarcas());
        fxResponsable.setItems(principal.getUsers());
        fxUbicacion.setItems(principal.getUbicaciones());
        fxEstado.setItems(principal.getEstados());
        fxNombre.setText(principal.getProducto().getNombre());
        fxCategoria.setText(principal.getProducto().getCategoria());
        fxModelo.setText(principal.getProducto().getModelo());
        String precio = String.valueOf(principal.getProducto().getPrecio());
        fxPrecio.setText(precio);
        fxDescripcion.setText(principal.getProducto().getDescripcion());
        fxFechaEntrada.setValue(principal.getProducto().getFecha_entrada().toLocalDate());
        fxFechaSalida.setValue(principal.getProducto().getFecha_salida().toLocalDate());
        fxMarca.getSelectionModel().select(principal.getProducto().getMarca());
        fxUbicacion.getSelectionModel().select(principal.getProducto().getUbicacion());
        fxResponsable.getSelectionModel().select(principal.getProducto().getResponsable());
        fxEstado.getSelectionModel().select(principal.getProducto().getEstado());

    }

    public void clickGuardar() {
        int lineas;

        if (!fxNombre.getText().equals("") && !fxPrecio.getText().equals("") && !fxCategoria.getText().equals("")
                && !fxModelo.getText().equals("") && !fxDescripcion.getText().equals("")
                && fxMarca.getSelectionModel().getSelectedItem() != null
                && fxUbicacion.getSelectionModel().getSelectedItem() != null
                && fxResponsable.getSelectionModel().getSelectedItem() != null
                && fxEstado.getSelectionModel().getSelectedItem() != null && fxFechaEntrada.getValue() != null
                && fxFechaSalida.getValue() != null) {
            Date dateE = Date.valueOf(fxFechaEntrada.getValue());
            Date dateS = Date.valueOf(fxFechaSalida.getValue());
            Producto pro = new Producto(fxNombre.getText(), fxCategoria.getText(), fxMarca.getSelectionModel().getSelectedItem().getIdmarca(),
                    fxModelo.getText(), fxDescripcion.getText(),
                    fxUbicacion.getSelectionModel().getSelectedItem().getIdubicacion(), fxResponsable.getSelectionModel().getSelectedItem().getIdUsuario(),
                    Double.parseDouble(fxPrecio.getText()),
                    dateE, dateS, fxEstado.getSelectionModel().getSelectedItem().getIdtipoestado());

            DAOProductoImpl dao = new DAOProductoImpl();
            lineas = dao.modificar(pro);

            if (lineas < 0) {
                alertInfo.setContentText("Producto creado.");
                alertInfo.showAndWait();

            } else if (lineas == -2) {
                alertError.setContentText("Producto duplicado.");
                alertError.showAndWait();
            } else {
                alertError.setContentText("No se ha podido crear el producto.");
                alertError.showAndWait();
            }
        } else {
            alertWarning.setContentText("No deje espacios sin rellenar o sin seleccionar.");
            alertWarning.showAndWait();

        }
    }

    private Alert alertWarning;
    private Alert alertInfo;
    private Alert alertError;

    public void clickVolver() {
        principal.cargarPantallaDatos();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        alertWarning = new Alert(Alert.AlertType.WARNING);
        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertError = new Alert(Alert.AlertType.ERROR);
    }

}
