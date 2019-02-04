package fx.controllers;

import dao.implementaciones.DAOMarcaImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Marca;

public class FXMLRegistrarMarcaController implements Initializable {
    
    private FXMLPrincipalController principal;
    
    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }
    @FXML
    private TextField fxNombre;
    @FXML
    private TextField fxDescripcion;
    @FXML
    private Button fxCrear;
    
    
   private Alert alertWarning;
   private Alert alertInfo;
   private Alert alertError;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertWarning = new Alert(Alert.AlertType.WARNING);
        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertError = new Alert(Alert.AlertType.ERROR);
    }    
    
    public void clickCrear(){
        Marca marca;
        int linea;
        if(fxNombre.getText().equals("") || fxDescripcion.getText().equals("") || fxCrear.getText().equals("")){
            alertWarning.setContentText("No hay datos");
            alertWarning.showAndWait();
        }else{
            marca = new Marca(fxNombre.getText(), fxDescripcion.getText());
            DAOMarcaImpl mi = new DAOMarcaImpl();
            linea = mi.insertar(marca);
            if(linea<0){
                alertInfo.setContentText("Marca creada");
                alertInfo.showAndWait();
                
             } else if (linea == -2) {
                alertError.setContentText("Marca duplicada");
                alertError.showAndWait();
                
            } else {
                alertError.setContentText("No se ha podido crear la marca");
                alertError.showAndWait();
            }
        }
    }
    
    public void volver(){
        principal.cargarPantallaOpciones();
    }
    
}
