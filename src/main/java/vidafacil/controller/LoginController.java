package vidafacil.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import vidafacil.model.Idoso;
import vidafacil.services.IdosoDAO;
import vidafacil.utils.Session;

import java.io.IOException;

public class LoginController {
    @FXML private TextField txtEmail;
    @FXML private PasswordField pswSenha;
    IdosoDAO dao = new IdosoDAO();

    public void onLogarClick(ActionEvent actionEvent) {
        String emailDigitado = txtEmail.getText();
        try {
            Idoso idosoEncontrado = dao.buscarLogin(emailDigitado);
            if (idosoEncontrado == null) throw new RuntimeException("Login não existente!");
            boolean senhaCorreta = BCrypt.checkpw(pswSenha.getText(), idosoEncontrado.getSenha());
            if (senhaCorreta) {
                Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                sucesso.setTitle("Login");
                sucesso.setHeaderText(null);
                sucesso.setContentText("Login com sucesso!");
                sucesso.showAndWait();
                Session.login(idosoEncontrado);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/inicial-view.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
            }
        } catch(RuntimeException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro de Validação");
            alerta.setHeaderText(null);
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
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

    public void onVoltarClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menu-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.getScene().setRoot(root);

        } catch (Exception e) {
            System.out.println("Erro ao carregar a tela de Cadastro:");
            e.printStackTrace();
        }
    }
    }