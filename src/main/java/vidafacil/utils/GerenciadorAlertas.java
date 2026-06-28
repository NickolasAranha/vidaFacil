package vidafacil.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import vidafacil.model.Medicamento;
import vidafacil.services.MedicamentoDAO;
import vidafacil.utils.Session;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GerenciadorAlertas {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final MedicamentoDAO dao = new MedicamentoDAO();

    public void iniciarMonitoramento() {
        // Executa a função 'verificarHorarios' a cada 30 segundos, começando imediatamente
        scheduler.scheduleAtFixedRate(this::verificarHorarios, 0, 30, TimeUnit.SECONDS);
        System.out.println("Monitoramento de remédios iniciado com sucesso!");
    }

    private void verificarHorarios() {
        // Só verifica se houver um usuário logado no sistema
        if (Session.getUsuario() == null) return;

        int idLogado = Session.getUsuario().getId();
        List<Medicamento> medicamentos = dao.buscarMedicamentosAtivos(idLogado);
        LocalDateTime agora = LocalDateTime.now();

        for (Medicamento med : medicamentos) {
            // REGRA MATEMÁTICA: Última dose + Intervalo de horas = Próxima dose
            LocalDateTime proximaDose = med.getUltima_Dose().plusHours(med.getIntervalo_aviso());

            // Se o horário de agora já passou (ou é exatamente igual) ao horário da próxima dose: ALERTA!
            if (!agora.isBefore(proximaDose)) {

                // Platform.runLater garante que o alerta visual não vai quebrar o JavaFX
                Platform.runLater(() -> dispararAlertaVisual(med));
            }
        }
    }

    private void dispararAlertaVisual(Medicamento med) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Hora do Remédio! ⏰");
        alerta.setHeaderText("Atenção, hora de tomar seu medicamento!");
        alerta.setContentText("Medicamento: " + med.getNome() + "\n" +
                "Dosagem: " + med.getDosagem() + " " + med.getUnidade().name() + "\n\n" +
                "Por favor, confirme se você já tomou.");

        // Quando o usuário clicar em "OK", o sistema atualiza o banco de dados com o horário atual!
        alerta.showAndWait().ifPresent(response -> {
            // Atualiza o objeto na memória com a hora que ele tomou (agora!)
            med.setUltima_Dose(LocalDateTime.now());

            // Grava a nova data/hora no banco de dados para o ciclo recomeçar
            dao.atualizarUltimaDose(med);

            System.out.println("Medicamento " + med.getNome() + " marcado como tomado às " + med.getUltima_Dose());
        });
    }

    public void pararMonitoramento() {
        scheduler.shutdown();
    }
}