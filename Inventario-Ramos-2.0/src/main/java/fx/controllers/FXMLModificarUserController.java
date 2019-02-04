/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLModificarUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private FXMLPrincipalController principal;
    
    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }
    
    public void volver(){
        principal.cargarPantallaOpciones();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
