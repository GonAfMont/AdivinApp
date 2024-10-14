package dad.adivinapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {

    private int numeroAdivinar;
    private int intentos;

    @Override
    public void start(Stage primaryStage) {
        numeroAdivinar = (int) (Math.random() * 100 + 1);
        intentos = 0;

        Label label = new Label("Introduce un número entre 1 y 100:");
        TextField inputNumero = new TextField();
        Button btnComprobar = new Button("Comprobar");

        btnComprobar.setDefaultButton(true);

        VBox layout = new VBox(10, label, inputNumero, btnComprobar);
        layout.setAlignment(Pos.CENTER);

        btnComprobar.setOnAction(e -> {
            String input = inputNumero.getText();

            if (input.isEmpty()) {
                mostrarAlerta("Error", "El campo no puede estar vacío", AlertType.ERROR);
                return;
            }

            try {
                int numeroUsuario = Integer.parseInt(input);

                if (numeroUsuario < 1 || numeroUsuario > 100) {
                    mostrarAlerta("Error", "El número debe estar entre 1 y 100", AlertType.ERROR);
                    return;
                }

                intentos++;

                if (numeroUsuario == numeroAdivinar) {
                    mostrarAlerta("¡Has ganado!", "Has ganado, solo has necesitado " + intentos + " intentos. ¡Prueba otra vez!", AlertType.INFORMATION);
                    reiniciarJuego();
                } else if (numeroUsuario > numeroAdivinar) {
                    mostrarAlerta("Has fallado", "Has fallado, el número es menor", AlertType.WARNING);
                } else {
                    mostrarAlerta("Has fallado", "Has fallado, el número es mayor", AlertType.WARNING);
                }
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "Por favor, introduce un número válido", AlertType.ERROR);
            }
        });

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Adivina el número");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void reiniciarJuego() {
        numeroAdivinar = (int) (Math.random() * 100 + 1);
        intentos = 0;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
