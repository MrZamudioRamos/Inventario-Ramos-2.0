/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import dao.implementaciones.DAOUbicacionImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Ubicacion;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLRegistroLugarController implements Initializable {

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
    private TextField fxDescripcion;

    @FXML
    private DatePicker fxFecha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOa
        alertWarning = new Alert(Alert.AlertType.WARNING);
        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertError = new Alert(Alert.AlertType.ERROR);
    }

    public void volver() {
        principal.cargarPantallaOpciones();
    }

    private Alert alertWarning;
    private Alert alertInfo;
    private Alert alertError;

    public void GuardarLugar() {

        Ubicacion ubicacion;
        int lineas;

        if (!fxNombre.getText().equals("") && !fxDescripcion.getText().equals("") && fxFecha.getValue() != null) {

            ubicacion = new Ubicacion(fxNombre.getText(), fxDescripcion.getText(), fxFecha.getValue());
            DAOUbicacionImpl dup = new DAOUbicacionImpl();
            lineas = dup.insertar(ubicacion);

            if (lineas < 0) {
                alertInfo.setContentText("Ubicación creada.");
                alertInfo.showAndWait();

            } else if (lineas == -2) {
                alertError.setContentText("Ubicación duplicada.");
                alertError.showAndWait();
            } else {
                alertError.setContentText("No se ha podido crear la ubicación.");
                alertError.showAndWait();
            }
        } else {
            alertWarning.setContentText("No hay datos");
            alertWarning.showAndWait();

        }

    }

}
