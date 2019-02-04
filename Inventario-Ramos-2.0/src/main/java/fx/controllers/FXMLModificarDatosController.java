package fx.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.implementaciones.DAOMarcaImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Marca;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLModificarDatosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField fxNombre;
    @FXML
    private TextField fxDescripcion;

    private FXMLPrincipalController principal;

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    private FXMLDatosController datosController;

    public FXMLDatosController getDatosController() {
        return datosController;
    }

    public void setDatosController(FXMLDatosController datosController) {
        this.datosController = datosController;
    }

    public void volver() {
        principal.cargarPantallaDatos();
    }

    public void clickActualizar() {

        Marca marca = new Marca(fxNombre.getText(), fxDescripcion.getText());
        
        DAOMarcaImpl dao = new DAOMarcaImpl();

        dao.modificar(marca);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void mostrar() {

        fxNombre.setText(datosController.getMarca().getNombre());
        fxDescripcion.setText(datosController.getMarca().getDescripcion());
    }

}
