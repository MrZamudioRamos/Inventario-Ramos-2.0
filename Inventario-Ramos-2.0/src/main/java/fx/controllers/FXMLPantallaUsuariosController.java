package fx.controllers;

import dao.implementaciones.DAOUsuariosImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private TableColumn<User, Boolean> fxTipo;
    
    @FXML
    private TableColumn<User, Boolean> fxTipo2;

    private User usuarioAModificar;

    private FXMLPrincipalController principal;

    private Alert alertInfo;
    private Alert alertError;

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    public void volver() {
        principal.cargarPantallaOpciones();
    }

    public void clickActualizar() {
        if (fxTableUser.getSelectionModel().getSelectedItem() != null) {
            usuarioAModificar = fxTableUser.getSelectionModel().getSelectedItem();
            principal.setUsuario(usuarioAModificar);
            principal.cargarPantallaModificarUser();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un usuario para actualizar. Gracias.");
            alert.showAndWait();
        }

    }

    public void clickEliminar() {
        if (fxTableUser.getSelectionModel().getSelectedItem() != null) {
            usuarioAModificar = fxTableUser.getSelectionModel().getSelectedItem();
            DAOUsuariosImpl daou = new DAOUsuariosImpl();
            if (usuarioAModificar.getNombre().equals("root")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se puede borrar el usuario root. Gracias.");
                alert.showAndWait();
            } else {
                int lineas = daou.borrar(usuarioAModificar);
                switch (lineas) {
                    case 1:
                        alertInfo.setContentText("Usuario eliminado.");
                        alertInfo.showAndWait();
                        break;
                    case -2:
                        alertError.setContentText("Usuario no eliminado.");
                        alertError.showAndWait();
                        break;
                    default:
                        alertError.setContentText("No se ha podido eliminar el usuario.");
                        alertError.showAndWait();
                        break;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un usuario para eliminar. Gracias.");
            alert.showAndWait();
        }

        mostrar();

    }

    public void mostrar() {
        fxTableUser.getItems().clear();
        fxTableUser.getItems().addAll(principal.getUsers());
        fxId.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getIdUsuario()));
        fxUser.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUser()));
        fxTipo.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().isAdmin()));
        fxTipo2.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().isInvent()));
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertError = new Alert(Alert.AlertType.ERROR);
    }

}
