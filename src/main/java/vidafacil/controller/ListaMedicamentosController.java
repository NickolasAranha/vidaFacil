package vidafacil.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import vidafacil.model.Medicamento;
import vidafacil.services.MedicamentoDAO;
import vidafacil.utils.Session;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.util.List;

public class ListaMedicamentosController {

    @FXML private VBox vboxLista;

    @FXML
    public void initialize() {
        carregarCartoes();
    }

    private void carregarCartoes() {
        vboxLista.getChildren().clear();

        MedicamentoDAO dao = new MedicamentoDAO();
        int idLogado = Session.getUsuario().getId();
        List<Medicamento> medicamentos = dao.buscarPorUsuario(idLogado);

        if (medicamentos.isEmpty()) {
            Label msgVazia = new Label("Nenhum medicamento cadastrado ainda.");
            msgVazia.setStyle("-fx-font-size: 16px; -fx-text-fill: gray;");
            vboxLista.getChildren().add(msgVazia);
            return;
        }

        for (Medicamento med : medicamentos) {
            VBox cartao = criarCartao(med);
            vboxLista.getChildren().add(cartao);
        }
    }

    private VBox criarCartao(Medicamento med) {
        VBox cartao = new VBox();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        cartao.setStyle("-fx-background-color: white; " +
                "-fx-background-radius: 10; " +
                "-fx-padding: 15; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");

        cartao.setSpacing(5);
        cartao.setCursor(Cursor.HAND);

        Label lblNome = new Label(med.getNome());
        lblNome.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label lblDosagem = new Label("Dosagem: " + med.getDosagem() + " " + med.getUnidade().name());
        lblDosagem.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");

        Label lblFrequencia = new Label("Frequência: A cada " + med.getIntervalo_aviso() + " horas");
        lblFrequencia.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");

        HBox hboxDatas = new HBox();
        hboxDatas.setSpacing(20);

        Label lblInicio = new Label("Início: " + med.getDataInicio().format(dtf));
        lblInicio.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        String textoFim = (med.getDataFim() != null) ? med.getDataFim().format(dtf) : "Uso Contínuo";
        Label lblFim = new Label("Fim: " + textoFim);
        lblFim.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        hboxDatas.getChildren().addAll(lblInicio, lblFim);
        cartao.getChildren().addAll(lblNome, lblDosagem, lblFrequencia, hboxDatas);
        cartao.setOnMouseClicked(event -> {
            System.out.println("Clicou em: " + med.getNome());
        });

        cartao.setOnMouseEntered(e -> cartao.setStyle("-fx-background-color: #f8f9fa; -fx-background-radius: 10; -fx-padding: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 10, 0, 0, 5);"));
        cartao.setOnMouseExited(e -> cartao.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-padding: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"));
        return cartao;
    }

    @FXML
    public void onVoltarClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/inicial-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}