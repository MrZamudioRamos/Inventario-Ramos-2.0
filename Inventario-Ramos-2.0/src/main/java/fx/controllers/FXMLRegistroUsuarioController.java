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

    boolean comprobarDni = false;

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
        int numero;
        boolean comprobar = false;

        if (!fxNombre.getText().equals("")
                && !fxApellidos.getText().equals("")
                && !fxTelefono.getText().equals("")
                && !fxMail.getText().equals("")
                && !fxContrasenia.getText().equals("")
                && !fxUser.getText().equals("")
                && !fxDni.getText().equals("")) {
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
                    validarDni();
                    if (comprobarDni == true) {
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
                            fxNombre.clear();
                            fxApellidos.clear();
                            fxTelefono.clear();
                            fxMail.clear();
                            fxContrasenia.clear();
                            fxUser.clear();
                            fxDni.clear();

                        } else if (lineas == -2) {
                            alertError.setContentText("Usuario duplicado.");
                            alertError.showAndWait();
                        } else {
                            alertError.setContentText("No se ha podido crear el usuario.");
                            alertError.showAndWait();
                        }
                    }else{
                        alertWarning.setContentText("Tipo DNI 00000000A");
                        alertWarning.showAndWait();
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

    public void volver() {
        fxNombre.setText("");
        fxTelefono.setText("");
        fxMail.setText("");
        fxContrasenia.setText("");
        fxDni.setText("");
        principal.cargarPantallaOpciones();
    }
    
    public boolean validarDni(){
        
        String letraMayuscula="";
        if (fxDni.getText().length()!=9 || Character.isLetter(this.fxDni.getText().charAt(8))==false) {
            return false;
        }  
        letraMayuscula = (this.fxDni.getText().substring(8).toUpperCase());
        
        if(soloNumeros() == true) {
            comprobarDni = true;
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean soloNumeros() {
 
            int i, j = 0;
            String numero = "";
            String miDNI = "";
            String[] unoNueve = {"0","1","2","3","4","5","6","7","8","9"};
 
            for(i = 0; i < this.fxDni.getText().length() - 1; i++) {
                numero = this.fxDni.getText().substring(i, i+1);
 
                for(j = 0; j < unoNueve.length; j++) {
                    if(numero.equals(unoNueve[j])) {
                        miDNI += unoNueve[j];
                    }
                }
            }
            
            if(miDNI.length() != 8) {
                return false;
            }
            else {
            return true;
        }
    }      

}
