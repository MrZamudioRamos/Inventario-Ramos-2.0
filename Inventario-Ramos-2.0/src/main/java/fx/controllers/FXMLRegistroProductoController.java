/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import dao.implementaciones.DAOProductoImpl;
import java.net.URL;
import java.sql.Date;
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
public class FXMLRegistroProductoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private FXMLPrincipalController principal;

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }
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

    private Alert alertWarning;
    private Alert alertInfo;
    private Alert alertError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        alertWarning = new Alert(Alert.AlertType.WARNING);
        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertError = new Alert(Alert.AlertType.ERROR);

    }

    public void mostrar() {
        fxMarca.setItems(principal.getMarcas());
        fxResponsable.setItems(principal.getUsers());
        fxUbicacion.setItems(principal.getUbicaciones());
        fxEstado.setItems(principal.getEstados());
        
        fxNombre.setText("");
        fxCategoria.setText("");

        fxModelo.setText("");
        fxPrecio.setText("");
        fxDescripcion.setText("");

        fxNombre.setText("");

        fxFechaEntrada.setValue(null);
        fxFechaSalida.setValue(null);

    }

    public void clickGuardar() {
        Producto producto;
        int lineas;
        boolean comprobar = false;
        double precio;

        if (!fxNombre.getText().equals("") && !fxPrecio.getText().equals("") && !fxCategoria.getText().equals("")
                && !fxModelo.getText().equals("") && !fxDescripcion.getText().equals("")
                && fxMarca.getSelectionModel().getSelectedItem() != null
                && fxUbicacion.getSelectionModel().getSelectedItem() != null
                && fxResponsable.getSelectionModel().getSelectedItem() != null
                && fxEstado.getSelectionModel().getSelectedItem() != null && fxFechaEntrada.getValue() != null
                && fxFechaSalida.getValue() != null) {

            if (comprobar == false) {
                try {
                    precio = Double.parseDouble(fxPrecio.getText());
                    comprobar = true;
                } catch (NumberFormatException e) {
                    alertWarning.setContentText("Qué cojone hace metiendo cosas raras, mete números subnorgay");
                    alertWarning.showAndWait();
                }

            }
            
            if (comprobar == true) {
                Date dateSalida = Date.valueOf(fxFechaSalida.getValue());
                Date dateEntrada = Date.valueOf(fxFechaEntrada.getValue());

                producto = new Producto(fxNombre.getText(), fxCategoria.getText(), fxMarca.getSelectionModel().getSelectedItem().getIdmarca(), fxModelo.getText(), fxDescripcion.getText(), fxUbicacion.getSelectionModel().getSelectedItem().getIdubicacion(), fxResponsable.getSelectionModel().getSelectedItem().getIdUsuario(), Double.parseDouble(fxPrecio.getText()), dateEntrada, dateSalida, fxEstado.getSelectionModel().getSelectedItem().getIdtipoestado());
                DAOProductoImpl dao = new DAOProductoImpl();

                lineas = dao.insertar(producto);

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
            }

        } else {
            alertWarning.setContentText("No deje espacios sin rellenar o sin seleccionar.");
            alertWarning.showAndWait();

        }
        mostrar();
    }

    public void volver() {
        principal.cargarPantallaOpciones();
    }

}
