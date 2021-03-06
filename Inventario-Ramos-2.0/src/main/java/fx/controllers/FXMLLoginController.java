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
        String usa = fxUsuario.getText();
        String contrasenia = fxContrasenia.getText();

        if (li.comprobarUser(usa, contrasenia)) {
            
            principal.setUser(fxUsuario.getText());
            
            if(li.Admin(usa, contrasenia) && li.Invent(usa, contrasenia)== false){
                principal.cargarPantallaOpcionesAdmin();
            }else if(li.Invent(usa, contrasenia) && li.Admin(usa, contrasenia)== false){
                principal.cargarPantallaOpcionesInvent();
            }else if(li.Admin(usa, contrasenia)== true && li.Invent(usa, contrasenia)== true){
                principal.cargarPantallaOpciones();
            }
            
            
        } else if (li.UsuarioNormal(fxUsuario.getText(), fxContrasenia.getText())) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Usuario sin permisos");
            alerta.setContentText("Usuario normal. No puedes acceder");
            alerta.showAndWait();
        } else {
            fxUsuario.clear();
            fxContrasenia.clear();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);

            alert.setContentText("Usuario y/o contraseña incorrectos");

            alert.showAndWait();
        }
        limpiar();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void check() {

    }

    private void limpiar() {
        fxUsuario.setText("");
        fxContrasenia.setText("");
    }
}
