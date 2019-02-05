/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.TipoUsuario;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLModificarUserController implements Initializable {

      private FXMLPrincipalController principal;

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    @FXML
    private TextField fxNombre;
    @FXML
    private TextField fxApellidos;
    @FXML
    private TextField fxTelefono;
    @FXML
    private TextField fxMail;
    @FXML
    private TextField fxContrasenia;
    @FXML
    private TextField fxUser;
    @FXML
    private TextField fxDni;
    @FXML
    private ComboBox<TipoUsuario> fxComboTipos;

    private Alert alertWarning;
    private Alert alertInfo;
    private Alert alertError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertWarning = new Alert(Alert.AlertType.WARNING);
        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertError = new Alert(Alert.AlertType.ERROR);
    }
    public void volver(){
        principal.cargarPantallaOpciones();
    }
    public void guardar(){
        
    }
    
    
}
