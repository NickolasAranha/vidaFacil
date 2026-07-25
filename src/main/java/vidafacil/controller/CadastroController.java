package vidafacil.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vidafacil.model.Idoso;
import vidafacil.services.IdosoDAO;
import org.mindrot.jbcrypt.BCrypt;
import javafx.event.ActionEvent;
import java.time.LocalDate;
import java.util.function.UnaryOperator;

public class CadastroController {

    public Button onVoltarClick;
    @FXML private DatePicker dpDataNascimento;
    @FXML private TextField txtCPF;
    @FXML private TextField txtEmail;
    @FXML private TextField txtNome;
    @FXML private PasswordField txtSenha;

     private IdosoDAO dao = new IdosoDAO();

    @FXML
    public void initialize() {
        UnaryOperator<TextFormatter.Change> filtroNumeros = mudanca -> {
            String novoTextoDigitado = mudanca.getControlNewText();

            if (novoTextoDigitado.matches("\\d{0,11}")) {
                return mudanca;
            }
            return null;
        };
        txtCPF.setTextFormatter(new TextFormatter<>(filtroNumeros));
    }

    @FXML
    protected void onSalvarClique(ActionEvent actionEvent) {
        try {
            String nome = txtNome.getText();
            String senha = txtSenha.getText();
            String email = txtEmail.getText();
            String cpf = txtCPF.getText();
            LocalDate dataNascimento = dpDataNascimento.getValue();

            if (nome.trim().isEmpty()) {
                throw new RuntimeException("O campo Nome está vazio!");
            }
            nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();

            if (senha.trim().isEmpty()) {
                throw new RuntimeException("O campo Senha está vazio!");
            }

            String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());

            if (email.trim().isEmpty()) {
                throw new RuntimeException("O campo Email está vazio!");
            } else if (!email.contains("@")) {
                throw new RuntimeException("Email inválido!");
            }

            if(cpf.trim().isEmpty()) {
                throw new RuntimeException("O campo CPF está vazio!");
            } else if (cpf.length() != 11) {
                throw new RuntimeException("CPF invalido!");
            }
            
            if (dataNascimento == null) {
                throw new RuntimeException("O campo Data de Nascimento está vazio!");
            } else if (dataNascimento.isAfter(LocalDate.now())) {
                throw new RuntimeException("Data de nascimento inválida!");
            }


            Idoso idoso = new Idoso(nome, senhaCriptografada, email, cpf, dataNascimento);
            dao.cadastrarIdoso(idoso);
            txtNome.clear();
            txtSenha.clear();
            txtEmail.clear();
            txtCPF.clear();
            dpDataNascimento.setValue(null);

            Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
            sucesso.setTitle("Cadastro Finalizado");
            sucesso.setHeaderText(null);
            sucesso.setContentText("Cadastrado com sucesso!");
            sucesso.showAndWait();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/login-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);

        } catch (RuntimeException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro de Validação");
            alerta.setHeaderText(null);
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro ao carregar a tela do Login:");
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
