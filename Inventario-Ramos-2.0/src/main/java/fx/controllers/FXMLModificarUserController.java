/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import dao.implementaciones.DAOUsuariosImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.User;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLModificarUserController implements Initializable {

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
    private TextField fxUser;
    @FXML
    private CheckBox fxInventariador;
    @FXML
    private CheckBox fxAdministrador;

    private Alert alertWarning;
    private Alert alertInfo;
    private Alert alertError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertWarning = new Alert(Alert.AlertType.WARNING);
        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertError = new Alert(Alert.AlertType.ERROR);
    }

    public void cargar() {
        fxNombre.setText(principal.getUsuario().getNombre());
        fxApellidos.setText(principal.getUsuario().getApellido());
        fxTelefono.setText(principal.getUsuario().getTelefono());
        fxMail.setText(principal.getUsuario().getMail());
        fxUser.setText(principal.getUsuario().getUser());
    }

    public void volver() {
        principal.cargarPantallaOpciones();
    }

    public void guardar() {

        int numero;
        boolean comprobar = false;
        DAOUsuariosImpl dao = new DAOUsuariosImpl();
        int idUser = principal.getUsuario().getIdUsuario();
        if (!fxNombre.getText().equals("")
                && !fxApellidos.getText().equals("")
                && !fxTelefono.getText().equals("")
                && !fxMail.getText().equals("")
                && !fxUser.getText().equals("")) {
            if (fxTelefono.getText().length() == 9) {
                if (comprobar == false) {
                    try {
                        numero = Integer.parseInt(fxTelefono.getText());
                        comprobar = true;
                    } catch (NumberFormatException e) {
                        alertWarning.setContentText("El teléfono sólo tiene números");
                        alertWarning.showAndWait();
                    }

                }

                if (comprobar == true) {
                    if (fxInventariador.isSelected() && fxAdministrador.isSelected()) {
                        
                        User user = new User(idUser,fxNombre.getText(), fxApellidos.getText(), fxTelefono.getText(), fxMail.getText(), fxUser.getText(), true, true);
                        int num = dao.modificar(user);

                        switch (num) {
                            case 1:
                                alertInfo.setContentText("Usuario modificado.");
                                alertInfo.showAndWait();
                                break;
                            case -2:
                                alertError.setContentText("Usuario duplicado.");
                                alertError.showAndWait();
                                break;
                            default:
                                alertError.setContentText("No se ha podido modificar el usuario.");
                                alertError.showAndWait();
                                break;
                        }
                    } else if (!fxInventariador.isSelected() && fxAdministrador.isSelected()) {
                        User user = new User(idUser,fxNombre.getText(), fxApellidos.getText(), fxTelefono.getText(), fxMail.getText(), fxUser.getText(), false, true);
                        int num = dao.modificar(user);

                        switch (num) {
                            case 1:
                                alertInfo.setContentText("Usuario modificado.");
                                alertInfo.showAndWait();
                                break;
                            case -2:
                                alertError.setContentText("Usuario duplicado.");
                                alertError.showAndWait();
                                break;
                            default:
                                alertError.setContentText("No se ha podido modificar el usuario.");
                                alertError.showAndWait();
                                break;
                        }
                    } else if (fxInventariador.isSelected() && !fxAdministrador.isSelected()) {
                        User user = new User(idUser,fxNombre.getText(), fxApellidos.getText(), fxTelefono.getText(), fxMail.getText(), fxUser.getText(), true, false);
                        int num = dao.modificar(user);

                        switch (num) {
                            case 1:
                                alertInfo.setContentText("Usuario modificado.");
                                alertInfo.showAndWait();
                                break;
                            case -2:
                                alertError.setContentText("Usuario duplicado.");
                                alertError.showAndWait();
                                break;
                            default:
                                alertError.setContentText("No se ha podido modificar el usuario.");
                                alertError.showAndWait();
                                break;
                        }
                    }
                }

            } else {
                alertWarning.setContentText("El numero de telefono debe tener 9 dígitos");
                alertWarning.showAndWait();
            }

        } else {
            alertWarning.setContentText("No deje espacios sin rellenar o sin seleccionar.");
            alertWarning.showAndWait();
        }

    }

}
