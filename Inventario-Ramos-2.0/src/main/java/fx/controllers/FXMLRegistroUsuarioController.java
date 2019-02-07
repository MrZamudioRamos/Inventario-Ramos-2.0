package fx.controllers;

import dao.implementaciones.DAOUsuariosImpl;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.User;
import util.PasswordHash;

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
    private TextField fxUser;
    @FXML
    private TextField fxDni;
    

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
        
    }

    public void registrar() {

        User usuario;
        int lineas;

        if (!fxNombre.getText().equals("")
                && !fxApellidos.getText().equals("")
                && !fxTelefono.getText().equals("")
                && !fxMail.getText().equals("")
                && !fxContrasenia.getText().equals("")
                && !fxUser.getText().equals("")
                && !fxDni.getText().equals("")) {
            String passHash = null;
            try {
                PasswordHash ph = new PasswordHash();
                passHash = ph.createHash(fxContrasenia.getText());
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(FXMLRegistroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }

            usuario = new User(fxNombre.getText(),
                    fxApellidos.getText(),
                    fxTelefono.getText(),
                    fxMail.getText(),
                    fxUser.getText(),
                    passHash,
                    fxDni.getText()
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
        } else {
            alertWarning.setContentText("No deje espacios sin rellenar o sin seleccionar.");
            alertWarning.showAndWait();

        }

    }

    public void volver() {
        fxNombre.setText("");
        fxTelefono.setText("");
        fxMail.setText("");
        fxContrasenia.setText("");
        fxDni.setText("");
        principal.cargarPantallaOpciones();
    }

}
