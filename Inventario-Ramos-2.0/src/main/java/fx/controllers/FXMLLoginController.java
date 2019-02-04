/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import dao.implementaciones.DAOLoginImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carlu
 */
public class FXMLLoginController implements Initializable {
    
    public void setMyStage(Stage myStage) {
        this.myStage = myStage;
    }

    private Stage myStage;
    
    private String usuario;
    
    private FXMLPrincipalController principal;
    
    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }
    
    //MÉTODOS

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }  
    
    //variables Login
    @FXML
    private TextField fxUsuario;
    
    @FXML
    private PasswordField fxContrasenia;

  
    public void clickEntrar() {
        DAOLoginImpl li = new DAOLoginImpl();
        
        if (li.comprobarUser(fxUsuario.getText(), fxContrasenia.getText())) {
            principal.setUser(fxUsuario.getText());
            principal.cargarPantallaOpciones();
        } else {
            fxUsuario.clear();
            fxContrasenia.clear();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Usuario y/o contraseña incorrectos");

            alert.showAndWait();
        }
        //principal.cargarPantallaOpciones();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void check(){
        
    }
}
