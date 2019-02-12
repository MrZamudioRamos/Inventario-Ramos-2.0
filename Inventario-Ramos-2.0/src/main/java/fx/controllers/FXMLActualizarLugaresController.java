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
import javafx.scene.control.TextField;
import model.Ubicacion;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLActualizarLugaresController implements Initializable {

    private FXMLPrincipalController principal;

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    public void clickVolver() {
        principal.cargarPantallaDatos();
    }

    @FXML
    private TextField fxNombre;

    @FXML
    private TextField fxDescripcion;

    private Alert alertWarning;
    private Alert alertInfo;
    private Alert alertError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        alertWarning = new Alert(Alert.AlertType.WARNING);
        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertError = new Alert(Alert.AlertType.ERROR);
    }

    void cargar() {
        fxNombre.setText(principal.getLugar().getNombre());
        fxDescripcion.setText(principal.getLugar().getDescripcion());
    }

    public void clickGuardar() {
        Ubicacion lugar = new Ubicacion(fxNombre.getText(), fxDescripcion.getText());
        DAOUbicacionImpl dao = new DAOUbicacionImpl();
        dao.modificar(lugar);
    }

}
