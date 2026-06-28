package vidafacil.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class MenuController {
    public void onLoginClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/login-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.getScene().setRoot(root);

        } catch (Exception e) {
            System.out.println("Erro ao carregar a tela de Cadastro:");
            e.printStackTrace();
        }
    }

    public void onCadastroClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/cadastro-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.getScene().setRoot(root);

        } catch (Exception e) {
            System.out.println("Erro ao carregar a tela de Cadastro:");
            e.printStackTrace();
        }
    }
}