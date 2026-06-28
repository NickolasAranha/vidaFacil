package vidafacil.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vidafacil.utils.GerenciadorAlertas;

import java.io.IOException;

public class VidaFacil extends Application {
    private GerenciadorAlertas gerenciadorAlertas;

    @Override
    public void start(Stage stage) throws IOException {
        gerenciadorAlertas = new GerenciadorAlertas();
        gerenciadorAlertas.iniciarMonitoramento();

        FXMLLoader fxmlLoader = new FXMLLoader(VidaFacil.class.getResource("/views/menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("VidaFácil!");
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            if (gerenciadorAlertas != null) {
                gerenciadorAlertas.pararMonitoramento();
            }
        });

        stage.show();
    }

    // ADICIONE ESTE BLOCO AQUI!
    public static void main(String[] args) {
        launch(args);
    }
}