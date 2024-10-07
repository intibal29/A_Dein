package org.example.a;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Clase principal de la aplicación JavaFX.
 * Esta clase extiende Application y es responsable de iniciar la aplicación y cargar la vista principal.
 */
public class HelloApplication extends Application {
    /**
     * Método principal que se llama al inicio de la aplicación.
     *
     * @param primaryStage La ventana principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Mi Aplicación JavaFX");
            primaryStage.show();
        } catch (IOException e) {
            // Manejo de excepciones: imprimir la traza de la excepción si ocurre un error al cargar el FXML
            e.printStackTrace();
        }
    }
    /**
     * Método principal de la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
