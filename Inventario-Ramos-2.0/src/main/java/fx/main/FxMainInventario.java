package fx.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author dam2
 */
public class FxMainInventario extends Application {

    @Override
    public void start(Stage primaryStage)
            throws IOException {

        FXMLLoader loaderMenu = new FXMLLoader(
                getClass().getResource("/fxml/FXMLPrincipal.fxml"));
        BorderPane root = loaderMenu.load();
        Scene scene = new Scene(root);    
        primaryStage.getIcons().add(new Image("/images/inventario.png"));
        primaryStage.setTitle("Inventariador");
        primaryStage.setScene(scene);
        primaryStage.show();

        //para no poder maximizar pantalla y
        primaryStage.setResizable(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
