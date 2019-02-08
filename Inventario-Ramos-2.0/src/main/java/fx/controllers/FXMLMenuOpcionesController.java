package fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLMenuOpcionesController implements Initializable {

    public void setMyStage(Stage myStage) {
        this.myStage = myStage;
    }

    private FXMLLoginController login;

    private Stage myStage;

    @FXML
    private String log;

    @FXML
    private Button fxListUsers;
    @FXML
    private Button fxAddUsers;
    @FXML
    private Button fxAddProduct;
    @FXML
    private Button fxListProduct;
    @FXML
    private Button fxAddBrand;
    @FXML
    private Button fxAddPlace;
    @FXML
    private Button fxListPlace;

    private FXMLPrincipalController principal;

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    public void listarProduct() {
        principal.cargarPantallaDatos();
    }

    public void registrarProduct() {
        principal.cargarPantallaProducto();
    }

    public void modificarUser() {
        principal.cargarPantallaModificarUser();
    }

    public void listarUser() {
        principal.cargarPantallaUsuario();
    }

    public void registrarUser() {
        principal.cargarPantallaRegistroUsuario();
    }

    public void listarLugar() {
        principal.cargarPantallaDatos();
    }

    public void registrarLugar() {
        principal.cargarPantallaLugar();
    }

    public void registrarMarca() {
        principal.cargarPantallaMarca();
    }

    public void salir() {
        Platform.exit();
    }

    public void cerrarSesion() {
        fxAddProduct.setDisable(false);
        fxAddBrand.setDisable(false);
        fxAddUsers.setDisable(false);
        fxAddPlace.setDisable(false);
        principal.cargarPantallaLogin();
    }

    public void Invent() {
        fxAddUsers.setDisable(true);
        fxAddPlace.setDisable(true);
    }

    public void Admin() {
        fxAddProduct.setDisable(true);
        fxAddBrand.setDisable(true);
    }

    @FXML
    private Label fxNombreUser;

    public void setLogin(String login) {
        this.log = login;
        fxNombreUser.setText(log);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
