package vidafacil.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vidafacil.model.Medicamento;
import vidafacil.model.Unidade;
import vidafacil.services.MedicamentoDAO;
import vidafacil.utils.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.UnaryOperator;

public class CadastroMedicamentoController {

    @FXML private TextField txtNome;
    @FXML private TextField txtDosagem;
    @FXML private ComboBox<Unidade> cbMedida;
    @FXML private TextField txtFrequencia;

    @FXML private DatePicker dpDataInicio;
    @FXML private ComboBox cbHora;
    @FXML private ComboBox cbMinuto;

    @FXML private DatePicker dpDataFim;
    @FXML private CheckBox chkUsoContinuo;


    @FXML
    public void initialize() {
        cbMedida.setItems(FXCollections.observableArrayList(Unidade.values()));
        chkUsoContinuo.setOnAction(event -> {
            if (chkUsoContinuo.isSelected()) {
                dpDataFim.setValue(null);
                dpDataFim.setDisable(true);
            } else {
                dpDataFim.setDisable(false);
            }
        });

        for (int i = 0; i < 24; i++) {
            cbHora.getItems().add(String.format("%02d", i));
        }

        for (int i = 0; i < 60; i++) {
            cbMinuto.getItems().add(String.format("%02d", i));
        }

        LocalDateTime agora = LocalDateTime.now();
        cbHora.setValue(String.format("%02d", agora.getHour()));
        cbMinuto.setValue(String.format("%02d", agora.getMinute()));

        UnaryOperator<TextFormatter.Change> filtroNumeros = mudanca -> {
            String novoTextoDigitado = mudanca.getControlNewText();
            if (novoTextoDigitado.matches("\\d{0,4}")) {
                return mudanca;
            }
            return null;
        };
        txtDosagem.setTextFormatter(new TextFormatter<>(filtroNumeros));

        filtroNumeros = mudanca -> {
            String novoTextoDigitado = mudanca.getControlNewText();
            if (novoTextoDigitado.matches("\\d{0,2}")) {
                return mudanca;
            }
            return null;
        };
        txtFrequencia.setTextFormatter(new TextFormatter<>(filtroNumeros));
    }


    @FXML
    public void onCadastrarMedicamentoClick(ActionEvent actionEvent) {
        try {
            String nome = txtNome.getText();
            Integer dosagem = Integer.parseInt(txtDosagem.getText());
            Unidade unidade = cbMedida.getValue();
            Integer frequencia = Integer.parseInt(txtFrequencia.getText());

            LocalDate dataInicio = dpDataInicio.getValue();
            String horaSelecionada = cbHora.getValue().toString();
            String minutoSelecionado = cbMinuto.getValue().toString();
            boolean usoContinuo = chkUsoContinuo.isSelected();
            LocalDate dataFim = dpDataFim.getValue();


            if(nome == null || dosagem == null || unidade == null || dataInicio == null || horaSelecionada == null || minutoSelecionado == null) {
                throw new RuntimeException("Preencha todos os campos!");
            }

            if (!usoContinuo && dataFim == null) {
                throw new RuntimeException("Preencha a data de término ou marque 'Uso Contínuo'!");
            }

            if(!usoContinuo && dataFim.isBefore(dataInicio)) {
                throw new RuntimeException("A data de término não pode ser anterior à data de início!");
            }

            int hora = Integer.parseInt(horaSelecionada);
            int minuto = Integer.parseInt(minutoSelecionado);
            LocalDateTime ultimaDose = dataInicio.atTime(hora, minuto);

            Medicamento medicamento = new Medicamento(Session.getUsuario().getId(), nome, dosagem, unidade, frequencia, dataInicio, dataFim, ultimaDose);
            MedicamentoDAO dao = new MedicamentoDAO();
            dao.salvar(medicamento);

            Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
            sucesso.setTitle("Cadastro Finalizado");
            sucesso.setHeaderText(null);
            sucesso.setContentText("Cadastrado com sucesso!");
            sucesso.showAndWait();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/inicial-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);

            txtNome.clear();
            txtDosagem.clear();
            cbMedida.setValue(null);
            txtFrequencia.clear();

            dpDataInicio.setValue(null);
            cbHora.setValue(null);
            cbMinuto.setValue(null);
            dpDataFim.setValue(null);
            chkUsoContinuo.setSelected(false);

        } catch (RuntimeException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no Cadastro");
            alerta.setHeaderText(null);
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro ao mudar para tela inicial!");
            e.printStackTrace();
        }
    }

    @FXML
    public void onVoltarClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/inicial-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println("Erro ao mudar para tela inicial!");
            e.printStackTrace();
        }
    }
}
