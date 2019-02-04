package fx.controllers;

import dao.implementaciones.DAOUsuariosImpl;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.TipoUsuario;
import model.User;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLRegistroUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    private TextField fxDni;
    @FXML
    private ComboBox<TipoUsuario> fxComboTipos;

    //private List<TipoUsuario> usarios;
    private Alert alertWarning;
    private Alert alertInfo;
    private Alert alertError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertWarning = new Alert(Alert.AlertType.WARNING);
        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertError = new Alert(Alert.AlertType.ERROR);
    }

    public void mostrar() {
        fxComboTipos.setItems(principal.getTipos());
    }

    public void registrar() {

        User usuario;
        int lineas;

        if (!fxNombre.getText().equals("")
                && !fxApellidos.getText().equals("")
                && !fxTelefono.getText().equals("")
                && !fxMail.getText().equals("")
                && !fxContrasenia.getText().equals("")
                && !fxDni.getText().equals("")
                && fxComboTipos.getSelectionModel().getSelectedItem() != null) {

            usuario = new User(fxNombre.getText(),
                    fxApellidos.getText(),
                    fxTelefono.getText(),
                    fxMail.getText(),
                    fxContrasenia.getText(),
                    fxDni.getText(),
                    fxComboTipos.getSelectionModel().getSelectedItem().getIdTipoUsuario()
            );
            DAOUsuariosImpl dao = new DAOUsuariosImpl();

            lineas = dao.insertar(usuario);

            if (lineas < 0) {
                alertInfo.setContentText("Usuario creado.");
                alertInfo.showAndWait();

            } else if (lineas == -2) {
                alertError.setContentText("Usuario duplicado.");
                alertError.showAndWait();
            } else {
                alertError.setContentText("No se ha podido crear el usuario.");
                alertError.showAndWait();
            }
        }else {
            alertWarning.setContentText("No deje espacios sin rellenar o sin seleccionar.");
            alertWarning.showAndWait();
            
        }

    }

    public void volver() {
        principal.cargarPantallaOpciones();
    }

}
