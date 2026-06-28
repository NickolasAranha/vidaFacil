package vidafacil.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import vidafacil.model.Idoso;
import vidafacil.utils.Session;

public class InicialController {
    @FXML private Label lblBoasVindas;

    @FXML
    public void initialize() {
        Idoso usuario = Session.getUsuario();

        if (usuario != null) {
            lblBoasVindas.setText("Bem-vindo(a), " + usuario.getNome() + "!");
        } else {
            System.out.println("Ninguém logado! Acesso negado.");
        }
    }

    public void onIrTelaCadastroMedClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/cadastromedicamento-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);

        } catch (Exception e) {
            System.out.println("Erro ao carregar a tela de Cadastro Medicamento:");
            e.printStackTrace();
        }
    }

    public void onIrListaMedClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/listamedicamentos-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);

        } catch (Exception e) {
            System.out.println("Erro ao carregar a tela de Lista Medicamento:");
            e.printStackTrace();
        }
    }
}
