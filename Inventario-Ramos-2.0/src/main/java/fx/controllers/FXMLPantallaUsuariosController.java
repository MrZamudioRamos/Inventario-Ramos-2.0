package fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.User;

public class FXMLPantallaUsuariosController implements Initializable {

    @FXML
    private Button fxRefresh;
    @FXML
    private Button fxDelete;
    @FXML
    private Button fxBack;

    @FXML
    private TableView<User> fxTableUser;

    @FXML
    private TableColumn<User, Integer> fxId;

    @FXML
    private TableColumn<User, String> fxUser;

    @FXML
    private TableColumn<User, Integer> fxTipo;

    private FXMLPrincipalController principal;

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    public void volver() {
        principal.cargarPantallaOpciones();
    }

    public void clickActualizar() {
        principal.cargarPantallaModificarUser();
    }

    public void mostrar() {
        fxTableUser.getItems().clear();
        fxTableUser.getItems().addAll(principal.getUsers());
        fxId.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getIdUsuario()));
        fxUser.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        fxTipo.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTipo()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
