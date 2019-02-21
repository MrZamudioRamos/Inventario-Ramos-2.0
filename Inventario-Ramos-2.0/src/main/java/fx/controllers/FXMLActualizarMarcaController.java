/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import dao.implementaciones.DAOMarcaImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Marca;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLActualizarMarcaController implements Initializable {

    /**
     * Initializes the controller class.
     */
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        alertWarning = new Alert(Alert.AlertType.WARNING);
        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertError = new Alert(Alert.AlertType.ERROR);
    }

    void cargar() {
        fxNombre.setText(principal.getMarca().getNombre());
        fxDescripcion.setText(principal.getMarca().getDescripcion());
    }

    public void clickGuardar() {
        int lineas;
        if (fxNombre.getText().equals("") || fxDescripcion.getText().equals("")) {
            alertWarning.setContentText("No hay datos");
            alertWarning.showAndWait();
        } else {
            Marca marca = new Marca(principal.getMarca().getIdmarca(),fxNombre.getText(), fxDescripcion.getText());
            
            DAOMarcaImpl dao = new DAOMarcaImpl();
            
            lineas = dao.modificar(marca);

            switch (lineas) {
                case 1:
                    alertInfo.setContentText("Marca modificada.");
                    alertInfo.showAndWait();
                    break;
                case -2:
                    alertError.setContentText("Marca duplicada.");
                    alertError.showAndWait();
                    break;
                default:
                    alertError.setContentText("No se ha podido modificar la marca.");
                    alertError.showAndWait();
                    break;
            }

       }
    }

}
