import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class VerificadorAlarme {
    
    private static final String CAMINHO_IMAGEM = "/logovidafacil.png"; 
    private static AlertaMedicamentoDAO dao = new AlertaMedicamentoDAO();
    private TrayIcon trayIcon; 

    public void iniciar() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (Session.idosoLogado != null) {
                    processarCicloDeAlertas(Session.idosoLogado.getId());
                }
            }
        }, 0, 60000); 
    }

    private void processarCicloDeAlertas(int idIdoso) {
        BD banco = new BD();
        String sqlRemediosDaHora = "SELECT * FROM Medicamento m WHERE m.ID_Idoso = ? " + 
                                      "AND DATE_ADD(m.Ultima_Dose, INTERVAL m.Intervalo_Aviso HOUR) <= NOW()";

        try (Connection conn = banco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlRemediosDaHora)) {
             
            stmt.setInt(1, idIdoso);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idMedicamento = rs.getInt("ID_Medicamento");
                String nome = rs.getString("Nome");
                String dosagem = rs.getString("Dosagem");
                String unidade = rs.getString("Unidade");
                Timestamp ultimaDose = rs.getTimestamp("Ultima_Dose");

                verificarEstadoDoAlerta(conn, idMedicamento, nome, dosagem, unidade, ultimaDose);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void verificarEstadoDoAlerta(Connection conn, int idMedicamento, String nome, String dose, String unidade, Timestamp ultimaDoseDoRemedio) throws SQLException {
        String sqlBuscaAlerta = "SELECT * FROM Alerta_Medicamento " +
                                "WHERE ID_Medicamento = ? AND Data_Hora > ? " +
                                "ORDER BY Data_Hora DESC LIMIT 1";

        try (PreparedStatement stmt = conn.prepareStatement(sqlBuscaAlerta)) {
            stmt.setInt(1, idMedicamento);
            stmt.setTimestamp(2, ultimaDoseDoRemedio);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
            	try {
            	dao.criarAlertaPendente(idMedicamento);
            	} catch(Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar alerta: " + erro.getMessage());
            	}
                abrirJanelaDeConfirmacao(idMedicamento, nome, dose, unidade, "Hora do Remédio!");            
            } else {
                String status = rs.getString("Status");
                Timestamp dataHoraAlerta = rs.getTimestamp("Data_Hora");
                
                if ("Pendente".equalsIgnoreCase(status)) {
                    analisarTempoDecorrido(conn, idMedicamento, dataHoraAlerta, nome, dose, unidade);
                }
            }
        }
    }

    private void analisarTempoDecorrido(Connection conn, int idMed, Timestamp dataAlerta, String nome, String dose, String unidade) {
        LocalDateTime dataCriacao = dataAlerta.toLocalDateTime();
        LocalDateTime agora = LocalDateTime.now();
        
        long minutosPassados = ChronoUnit.MINUTES.between(dataCriacao, agora);

        if (minutosPassados >= 5 && minutosPassados < 6) {
            abrirJanelaDeConfirmacao(idMed, nome, dose, unidade, "2º AVISO");
        }
        else if (minutosPassados >= 10 && minutosPassados < 11) {
            abrirJanelaDeConfirmacao(idMed, nome, dose, unidade, "ÚLTIMO AVISO");
        }
        else if (minutosPassados >= 15) {
        	try {
            dao.atualizarStatusFinalizado(idMed, "Perdido");
        	} catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar alerta do medicamento.");
        	}
            esconderIconeTray();
        }
    }
    
    private void abrirJanelaDeConfirmacao(int idMedicamento, String nome, String dose, String unidade, String titulo) {
        EventQueue.invokeLater(() -> {            
            if (SystemTray.isSupported() && trayIcon == null) {
                try {
                    SystemTray tray = SystemTray.getSystemTray();
                    URL urlImagem = getClass().getResource(CAMINHO_IMAGEM);
                    Image imagem = (urlImagem != null) ? 
                        Toolkit.getDefaultToolkit().getImage(urlImagem) : 
                        new java.awt.image.BufferedImage(16, 16, java.awt.image.BufferedImage.TYPE_INT_RGB);

                    trayIcon = new TrayIcon(imagem, "VidaFácil");
                    trayIcon.setImageAutoSize(true);
                    trayIcon.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainFrame.trazerJanelaPrincipalParaFrente();
                        }
                    });
                    tray.add(trayIcon);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (trayIcon != null) {
                trayIcon.displayMessage(titulo, 
                    "Clique aqui ou na janela para confirmar: " + nome, 
                    TrayIcon.MessageType.INFO);
            }

            Toolkit.getDefaultToolkit().beep(); 

            Object[] options = {"Sim, tomei", "Não vou tomar", "Cancelar"};
            
            int n = JOptionPane.showOptionDialog(null,
                titulo + "\n\nVocê tomou o medicamento " + nome + "?\n" +
                "Dose indicada: " + dose + " " + unidade,
                "VidaFácil - Alarme",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

            if (n == 0) { 
                try {
                	dao.atualizarStatusConfirmado(idMedicamento);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao confirmar alerta do medicamento.");
                }
                esconderIconeTray();
            } else if (n == 1) { 
            	try {
                dao.atualizarStatusFinalizado(idMedicamento, "Recusado");
                esconderIconeTray();
            	} catch(Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro ao recusar alerta do medicamento.");
            	}
            }
        });
    }


    
    private void esconderIconeTray() {
        if (trayIcon != null && SystemTray.isSupported()) {
            SystemTray.getSystemTray().remove(trayIcon);
            trayIcon = null; 
        }
    }
}
