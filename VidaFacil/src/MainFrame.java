import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.Dimension;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private CardLayout cardLayout; 
	private JPanel panelBemVindo;
	private JPanel panelLogin;
	private JPanel panelCadastro;
	private JPanel panelLogadoInicial;
	private JPanel panelCadastrarMedicamento;
	private JPanel panelListaMedicamentos;
	private JPanel panelVerPerfil;
	private JPanel panelHistoricoAlertas;
	private JPanel panelVisualizarMeta;
	private JPanel panelConfiguracaoMeta;
	private JPasswordField txtSenhaCadastro;
	private JTextField txtEmail;
	private JTextField txtCPF;
	private JTextField txtEmailLogin;
	private JPasswordField txtSenhaLogin;
	private JLabel lblBoasVindasUsuario;
	private JTextField txtNomeCadastroMedicamento;
	private JTextField txtDosagemCadastroMedicamento;
	private JTextField txtIntervalo_AvisoCadastroMedicamento;
	private JTable tableMedicamentos;
	private Integer idMedicamentoEmEdicao = null;
	private JFormattedTextField txtDataInicioCadastroMedicamento;
	private JFormattedTextField txtDataFimCadastroMedicamento;
	private JComboBox comboBoxUnidade;
	private JFormattedTextField txtHoraInicioCadastroMedicamento;
	private JButton btnCriarMedicamento;
	private JButton btnDeletarMedicamento;
	private JTable tableHistorico;
	private boolean oculto = true;
	private JTextField txtCodigoConexaoPerfil;
	private JTextField txtMetaConfiguracao;
	private JTextField txtHoraComecoConfiguracao;
	private JTextField txtHoraFimConfiguracao;
	private JTextField txtQuantidadeRegistroVisualizar;
	private JLabel metaAtingida;
	private JTextField txtNome;
	private JFormattedTextField txtDataDeNascimento;

	/**
	 * Launch the application.
	 */
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	 }

	 /**
	  * Create the frame.
	  */
	 public MainFrame() {
		 setBackground(new Color(255, 255, 255));
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setTitle("VidaFácil - Gerenciamento de Medicamentos");
		 setSize(700,500);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setLocationRelativeTo(null); 
		 try {
			 URL iconURL = getClass().getResource("/logovidafacil.png");
			 if (iconURL != null) {
				 ImageIcon icon = new ImageIcon(iconURL);
				 setIconImage(icon.getImage()); 
			 }
		 } catch (Exception e) {
			 System.err.println("Erro ao carregar ícone da janela: " + e.getMessage());
		 }

		 contentPane = new JPanel();
		 contentPane.setBackground(new Color(220, 220, 220));
		 contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 setContentPane(contentPane);

		 cardLayout = new CardLayout(0, 0); 
		 contentPane.setLayout(cardLayout); 
		 // -------------------------------------------------------
		 // TELA 1: BEM VINDO
		 // -------------------------------------------------------
		 panelBemVindo = new JPanel();
		 panelBemVindo.setBackground(new Color(245, 245, 245));
		 contentPane.add(panelBemVindo, "nome_bemvindo"); 
		 panelBemVindo.setLayout(null);

		 JLabel lblTitulo = new JLabel("Bem-vindo ao VidaFácil!");
		 lblTitulo.setForeground(new Color(60, 179, 113));
		 lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		 lblTitulo.setBounds(152, 109, 371, 23);
		 panelBemVindo.add(lblTitulo);

		 JButton btnIrLogin = new JButton("Fazer Login");
		 btnIrLogin.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 cardLayout.show(contentPane, "nome_login");
			 }
		 });
		 btnIrLogin.setForeground(new Color(255, 255, 255));
		 btnIrLogin.setBackground(new Color(60, 179, 113));
		 btnIrLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnIrLogin.setBounds(263, 196, 150, 49);
		 panelBemVindo.add(btnIrLogin);

		 JButton btnIrCadastro = new JButton("Cadastrar ");
		 btnIrCadastro.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 cardLayout.show(contentPane, "nome_cadastro");
			 }
		 });
		 btnIrCadastro.setForeground(new Color(255, 255, 255));
		 btnIrCadastro.setBackground(new Color(60, 179, 113));
		 btnIrCadastro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnIrCadastro.setBounds(263, 267, 150, 49);
		 panelBemVindo.add(btnIrCadastro);

		 JLabel lblTextoBemVindo = new JLabel("Não possui um cadastro?");
		 lblTextoBemVindo.setForeground(new Color(0, 0, 0));
		 lblTextoBemVindo.setFont(new Font("Tahoma", Font.BOLD, 15));
		 lblTextoBemVindo.setBounds(60, 275, 193, 37);
		 panelBemVindo.add(lblTextoBemVindo);

		 // -------------------------------------------------------
		 // TELA 3: CADASTRO
		 // -------------------------------------------------------
		 panelCadastro = new JPanel();
		 panelCadastro.setBackground(new Color(245, 245, 245));
		 contentPane.add(panelCadastro, "nome_cadastro");
		 panelCadastro.setLayout(null);

		 JLabel lblCadastro = new JLabel("Faça o seu cadastro");
		 lblCadastro.setForeground(new Color(60, 179, 113));
		 lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		 lblCadastro.setFont(new Font("Tahoma", Font.BOLD, 30));
		 lblCadastro.setBounds(182, 50, 312, 33);
		 panelCadastro.add(lblCadastro);

		 txtNome = new JTextField();
		 txtNome.setBackground(new Color(255, 255, 255));
		 txtNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtNome.setBounds(218, 119, 240, 25);
		 panelCadastro.add(txtNome);

		 JButton btnSalvar = new JButton("Cadastrar");
		 btnSalvar.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 salvarCadastro();
			 }
		 });
		 btnSalvar.setForeground(new Color(255, 255, 255));
		 btnSalvar.setBackground(new Color(60, 179, 113));
		 btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnSalvar.setBounds(263, 306, 150, 49);
		 panelCadastro.add(btnSalvar);

		 JButton btnVoltarCadastro = new JButton("←");
		 btnVoltarCadastro.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 cardLayout.show(contentPane, "nome_bemvindo");
			 }
		 });
		 btnVoltarCadastro.setForeground(new Color(255, 255, 255));
		 btnVoltarCadastro.setBackground(new Color(60, 179, 113));
		 btnVoltarCadastro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnVoltarCadastro.setBounds(0, 0, 87, 37);
		 panelCadastro.add(btnVoltarCadastro);

		 JLabel lblNome = new JLabel("Nome:");
		 lblNome.setForeground(new Color(0, 0, 0));
		 lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		 lblNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 lblNome.setLabelFor(txtNome);
		 lblNome.setBounds(137, 123, 62, 14);
		 panelCadastro.add(lblNome);

		 JLabel lblSenha = new JLabel("Senha:");
		 lblSenha.setForeground(new Color(0, 0, 0));
		 lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		 lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 lblSenha.setBounds(137, 158, 62, 14);
		 panelCadastro.add(lblSenha);

		 txtSenhaCadastro = new JPasswordField();
		 lblSenha.setLabelFor(txtSenhaCadastro);
		 txtSenhaCadastro.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtSenhaCadastro.setToolTipText("");
		 txtSenhaCadastro.setBounds(218, 155, 240, 25);
		 panelCadastro.add(txtSenhaCadastro);

		 txtEmail = new JTextField();
		 txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtEmail.addFocusListener(new FocusAdapter() {
			 @Override
			 public void focusGained(FocusEvent e) {
				 txtEmail.setBorder(UIManager.getBorder("TextField.border"));
			 }
		 });
		 txtEmail.setBounds(218, 191, 240, 25);
		 panelCadastro.add(txtEmail);
		 txtEmail.setColumns(10);


		 MaskFormatter mascaraDataNascimento = null;
		 try {
			 mascaraDataNascimento = new MaskFormatter("##/##/####");
			 mascaraDataNascimento.setPlaceholderCharacter('_'); // mostra _ nos espaços vazios
		 } catch (Exception e) {
			 e.printStackTrace();
		 }

		 txtDataDeNascimento = new JFormattedTextField(mascaraDataNascimento);
		 txtDataDeNascimento.setFont(new Font("Tahoma", Font.BOLD, 13));
		 txtDataDeNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		 txtDataDeNascimento.setBounds(363, 226, 95, 20);
		 panelCadastro.add(txtDataDeNascimento);


		 txtCPF = new JTextField();
		 txtCPF.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtCPF.addFocusListener(new FocusAdapter() {
			 @Override
			 public void focusGained(FocusEvent e) {
				 txtCPF.setBorder(UIManager.getBorder("TextField.border"));
			 }
		 });
		 txtCPF.setBounds(218, 257, 240, 25);
		 panelCadastro.add(txtCPF);
		 txtCPF.setColumns(10);

		 JLabel lblCPF = new JLabel("CPF:");
		 lblCPF.setForeground(new Color(0, 0, 0));
		 lblCPF.setHorizontalAlignment(SwingConstants.CENTER);
		 lblCPF.setLabelFor(txtCPF);
		 lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 lblCPF.setBounds(137, 259, 62, 18);
		 panelCadastro.add(lblCPF);

		 JLabel lblEmail = new JLabel("E-mail:");
		 lblEmail.setForeground(new Color(0, 0, 0));
		 lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		 lblEmail.setLabelFor(txtEmail);
		 lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 lblEmail.setBounds(137, 195, 62, 14);
		 panelCadastro.add(lblEmail);

		 JLabel lblData = new JLabel("Data de Nascimento: ");
		 lblData.setForeground(new Color(0, 0, 0));
		 lblData.setHorizontalAlignment(SwingConstants.CENTER);
		 lblData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 lblData.setLabelFor(txtDataDeNascimento);
		 lblData.setBounds(137, 226, 175, 14);
		 panelCadastro.add(lblData);

		 JButton btnVisualizarSenhaCadastro = new JButton("🙈");
		 btnVisualizarSenhaCadastro.setForeground(new Color(0, 0, 0));
		 btnVisualizarSenhaCadastro.setBackground(new Color(60, 179, 113));
		 btnVisualizarSenhaCadastro.setBounds(468, 159, 49, 18);
		 panelCadastro.add(btnVisualizarSenhaCadastro);

		 btnVisualizarSenhaCadastro.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 if(oculto == true) {
					 txtSenhaCadastro.setEchoChar((char)0);
					 btnVisualizarSenhaCadastro.setText("🙉");
					 oculto = false;
				 } else {
					 txtSenhaCadastro.setEchoChar('•');
					 btnVisualizarSenhaCadastro.setText("🙈");
					 oculto = true;
				 }
			 }
		 });

		 // -------------------------------------------------------
		 // TELA 2: LOGIN
		 // -------------------------------------------------------
		 panelLogin = new JPanel();
		 panelLogin.setBackground(new Color(245, 245, 245));
		 contentPane.add(panelLogin, "nome_login");
		 panelLogin.setLayout(null);

		 JLabel lblLogin = new JLabel("Login");
		 lblLogin.setForeground(new Color(60, 179, 113));
		 lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		 lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		 lblLogin.setBounds(292, 97, 91, 35);
		 panelLogin.add(lblLogin);

		 JButton btnVoltarLogin = new JButton("←");
		 btnVoltarLogin.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 cardLayout.show(contentPane, "nome_bemvindo");
			 }
		 });
		 btnVoltarLogin.setForeground(new Color(255, 255, 255));
		 btnVoltarLogin.setBackground(new Color(60, 179, 113));
		 btnVoltarLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnVoltarLogin.setBounds(0, 0, 87, 37);
		 panelLogin.add(btnVoltarLogin);

		 txtEmailLogin = new JTextField();
		 txtEmailLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtEmailLogin.setBounds(223, 190, 230, 25);
		 panelLogin.add(txtEmailLogin);
		 txtEmailLogin.setColumns(10);

		 JLabel lblNomeLogin = new JLabel("Email:");
		 lblNomeLogin.setForeground(new Color(0, 0, 0));
		 lblNomeLogin.setHorizontalAlignment(SwingConstants.CENTER);
		 lblNomeLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblNomeLogin.setLabelFor(txtEmailLogin);
		 lblNomeLogin.setBounds(149, 192, 65, 16);
		 panelLogin.add(lblNomeLogin);

		 JLabel lblSenhaLogin = new JLabel("Senha:");
		 lblSenhaLogin.setForeground(new Color(0, 0, 0));
		 lblSenhaLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblSenhaLogin.setBounds(149, 239, 65, 15);
		 panelLogin.add(lblSenhaLogin);

		 JButton btnLogin = new JButton("Entrar");
		 btnLogin.setForeground(new Color(255, 255, 255));
		 btnLogin.setBackground(new Color(60, 179, 113));
		 btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnLogin.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
				 try {
					 String email = txtEmailLogin.getText();
					 char[] senhaChar = txtSenhaLogin.getPassword();
					 String senha = new String(senhaChar);

					 IdosoDAO dao = new IdosoDAO();
					 boolean resultado = dao.verificarLogin(email, senha);

					 if (resultado) {
						 JOptionPane.showMessageDialog(null, "Idoso logado com sucesso!");
						 cardLayout.show(contentPane, "nome_LogadoInicial");   
						 new VerificadorAlarme().iniciar();
						 prepararTelaLogadoInicial();
					 } else {
						 JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
					 }

					 txtEmailLogin.setText("");
					 txtSenhaLogin.setText("");

				 } catch (Exception erro) {
					 JOptionPane.showMessageDialog(null, "Erro ao logar: " + erro.getMessage());
				 }
			 }
		 });
		 btnLogin.setBounds(263, 293, 150, 49);
		 panelLogin.add(btnLogin);

		 txtSenhaLogin = new JPasswordField();
		 txtSenhaLogin.setBackground(new Color(255, 255, 255));
		 lblSenhaLogin.setLabelFor(txtSenhaLogin);
		 txtSenhaLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtSenhaLogin.setBounds(223, 237, 230, 25);
		 panelLogin.add(txtSenhaLogin);

		 JButton btnVisualizarSenhaLogin = new JButton("🙈");
		 btnVisualizarSenhaLogin.setBackground(new Color(60, 179, 113));
		 btnVisualizarSenhaLogin.setForeground(new Color(0, 0, 0));
		 btnVisualizarSenhaLogin.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 if(oculto == true) {
					 txtSenhaLogin.setEchoChar((char)0);
					 btnVisualizarSenhaLogin.setText("🙉");
					 oculto = false;
				 } else {
					 txtSenhaLogin.setEchoChar('•');
					 btnVisualizarSenhaLogin.setText("🙈");
					 oculto = true;
				 }
			 }
		 });

		 btnVisualizarSenhaLogin.setBounds(458, 241, 49, 18);
		 panelLogin.add(btnVisualizarSenhaLogin);

		 // -------------------------------------------------------
		 // TELA 4: TELA INICIAL APÓS O LOGIN
		 // -------------------------------------------------------

		 panelLogadoInicial = new JPanel();
		 panelLogadoInicial.setBackground(new Color(245, 245, 245));
		 contentPane.add(panelLogadoInicial, "nome_LogadoInicial");
		 panelLogadoInicial.setLayout(null);

		 lblBoasVindasUsuario = new JLabel("Olá *nome*, seja bem-vindo(a)!");
		 lblBoasVindasUsuario.setForeground(new Color(60, 179, 113));
		 lblBoasVindasUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		 lblBoasVindasUsuario.setFont(new Font("Tahoma", Font.BOLD, 30));
		 lblBoasVindasUsuario.setBounds(88, 115, 500, 56);
		 panelLogadoInicial.add(lblBoasVindasUsuario);

		 JButton btnCadastrarMedicamento = new JButton("Cadastrar Medicamento");
		 btnCadastrarMedicamento.setForeground(new Color(255, 255, 255));
		 btnCadastrarMedicamento.setBackground(new Color(60, 179, 113));
		 btnCadastrarMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnCadastrarMedicamento.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 prepararTelaCadastroNovo();
				 cardLayout.show(contentPane, "nome_cadastrarMedicamento");	

			 }
		 });
		 btnCadastrarMedicamento.setBounds(218, 207, 240, 37);
		 panelLogadoInicial.add(btnCadastrarMedicamento);

		 JButton btnListaMedicamentos = new JButton("Meus Medicamentos");
		 btnListaMedicamentos.setForeground(new Color(255, 255, 255));
		 btnListaMedicamentos.setBackground(new Color(60, 179, 113));
		 btnListaMedicamentos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnListaMedicamentos.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 carregarListaMedicamentos();
				 cardLayout.show(contentPane, "nome_ListaMedicamentos");
			 }
		 });
		 btnListaMedicamentos.setBounds(218, 262, 240, 37);
		 panelLogadoInicial.add(btnListaMedicamentos);

		 JButton btnIrPerfil = new JButton("Meu Perfil");
		 btnIrPerfil.setForeground(new Color(255, 255, 255));
		 btnIrPerfil.setBackground(new Color(60, 179, 113));
		 btnIrPerfil.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 btnIrPerfil.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 prepararTelaVerPerfil();
				 cardLayout.show(contentPane, "nome_verPerfil");
			 }
		 });
		 btnIrPerfil.setBounds(560, 0, 116, 37);
		 panelLogadoInicial.add(btnIrPerfil);

		 JButton btnIrVisualizarMeta = new JButton("Visualizar Meta Diária");
		 btnIrVisualizarMeta.setForeground(new Color(255, 255, 255));
		 btnIrVisualizarMeta.setBackground(new Color(60, 179, 113));
		 btnIrVisualizarMeta.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 atualizarPainelHidratacao(metaAtingida);
				 cardLayout.show(contentPane, "nome_visualizarMeta");
			 }
		 });
		 btnIrVisualizarMeta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnIrVisualizarMeta.setBounds(218, 316, 240, 37);
		 panelLogadoInicial.add(btnIrVisualizarMeta);

		 // -------------------------------------------------------
		 // TELA 5: TELA CADASTRO MEDICAMENTOS
		 // -------------------------------------------------------

		 panelCadastrarMedicamento = new JPanel();
		 panelCadastrarMedicamento.setBackground(new Color(245, 245, 245));
		 contentPane.add(panelCadastrarMedicamento, "nome_cadastrarMedicamento");
		 panelCadastrarMedicamento.setLayout(null);

		 txtNomeCadastroMedicamento = new JTextField();
		 txtNomeCadastroMedicamento.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtNomeCadastroMedicamento.setBounds(279, 58, 117, 25);
		 panelCadastrarMedicamento.add(txtNomeCadastroMedicamento);
		 txtNomeCadastroMedicamento.setColumns(10);

		 JLabel lblNomeCadastroMedicamento = new JLabel("Nome: ");
		 lblNomeCadastroMedicamento.setForeground(new Color(0, 0, 0));
		 lblNomeCadastroMedicamento.setHorizontalAlignment(SwingConstants.CENTER);
		 lblNomeCadastroMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblNomeCadastroMedicamento.setBounds(178, 58, 100, 18);
		 panelCadastrarMedicamento.add(lblNomeCadastroMedicamento);

		 JLabel lblDosagemCadastroMedicamento = new JLabel("Dosagem:");
		 lblDosagemCadastroMedicamento.setForeground(new Color(0, 0, 0));
		 lblDosagemCadastroMedicamento.setHorizontalAlignment(SwingConstants.CENTER);
		 lblDosagemCadastroMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblDosagemCadastroMedicamento.setBounds(162, 91, 100, 23);
		 panelCadastrarMedicamento.add(lblDosagemCadastroMedicamento);

		 txtDosagemCadastroMedicamento = new JTextField();
		 txtDosagemCadastroMedicamento.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtDosagemCadastroMedicamento.setBounds(279, 93, 117, 25);
		 panelCadastrarMedicamento.add(txtDosagemCadastroMedicamento);
		 txtDosagemCadastroMedicamento.setColumns(10);

		 JLabel lblIntervalo_AvisoCadastroMedicamento = new JLabel("Frequência de Uso (Ex: 8 horas):");
		 lblIntervalo_AvisoCadastroMedicamento.setForeground(new Color(0, 0, 0));
		 lblIntervalo_AvisoCadastroMedicamento.setHorizontalAlignment(SwingConstants.CENTER);
		 lblIntervalo_AvisoCadastroMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 lblIntervalo_AvisoCadastroMedicamento.setBounds(-11, 163, 298, 20);
		 panelCadastrarMedicamento.add(lblIntervalo_AvisoCadastroMedicamento);

		 txtIntervalo_AvisoCadastroMedicamento = new JTextField();
		 txtIntervalo_AvisoCadastroMedicamento.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtIntervalo_AvisoCadastroMedicamento.setBounds(279, 163, 95, 25);
		 panelCadastrarMedicamento.add(txtIntervalo_AvisoCadastroMedicamento);
		 txtIntervalo_AvisoCadastroMedicamento.setColumns(10);


		 JLabel lblNewLabel_4 = new JLabel("Dia do Inicio:");
		 lblNewLabel_4.setForeground(new Color(0, 0, 0));
		 lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		 lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblNewLabel_4.setBounds(142, 197, 120, 20);
		 panelCadastrarMedicamento.add(lblNewLabel_4);

		 MaskFormatter mascaraDataInicioMedicamento = null;
		 try {
			 mascaraDataInicioMedicamento = new MaskFormatter("##/##/####");
			 mascaraDataInicioMedicamento.setPlaceholderCharacter('_'); // mostra _ nos espaços vazios
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 txtDataInicioCadastroMedicamento = new JFormattedTextField(mascaraDataInicioMedicamento);
		 txtDataInicioCadastroMedicamento.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtDataInicioCadastroMedicamento.setHorizontalAlignment(SwingConstants.CENTER);
		 txtDataInicioCadastroMedicamento.setBounds(279, 198, 95, 25);
		 panelCadastrarMedicamento.add(txtDataInicioCadastroMedicamento);


		 MaskFormatter mascaraDataFimoMedicamento = null;
		 try {
			 mascaraDataFimoMedicamento = new MaskFormatter("##/##/####");
			 mascaraDataFimoMedicamento.setPlaceholderCharacter('_'); // mostra _ nos espaços vazios
		 } catch (Exception e) {
			 e.printStackTrace();
		 }

		 txtDataFimCadastroMedicamento = new JFormattedTextField(mascaraDataFimoMedicamento);
		 txtDataFimCadastroMedicamento.setFont(new Font("Tahoma", Font.BOLD, 12));
		 txtDataFimCadastroMedicamento.setHorizontalAlignment(SwingConstants.CENTER);
		 txtDataFimCadastroMedicamento.setBounds(279, 233, 95, 25);
		 panelCadastrarMedicamento.add(txtDataFimCadastroMedicamento);

		 comboBoxUnidade = new JComboBox();
		 comboBoxUnidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		 comboBoxUnidade.setModel(new DefaultComboBoxModel<>(Medicamento.tipoUnidade.values()));
		 comboBoxUnidade.setBounds(279, 128, 86, 25);
		 panelCadastrarMedicamento.add(comboBoxUnidade);

		 JLabel lblUnidadeCadastroMedicamento = new JLabel("Unidade:");
		 lblUnidadeCadastroMedicamento.setForeground(new Color(0, 0, 0));
		 lblUnidadeCadastroMedicamento.setHorizontalAlignment(SwingConstants.CENTER);
		 lblUnidadeCadastroMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblUnidadeCadastroMedicamento.setBounds(172, 127, 90, 22);
		 panelCadastrarMedicamento.add(lblUnidadeCadastroMedicamento);

		 JLabel lblDataFimCadastroMedicamento = new JLabel("Dia do Fim:");
		 lblDataFimCadastroMedicamento.setForeground(new Color(0, 0, 0));
		 lblDataFimCadastroMedicamento.setHorizontalAlignment(SwingConstants.CENTER);
		 lblDataFimCadastroMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblDataFimCadastroMedicamento.setBounds(152, 232, 120, 20);
		 panelCadastrarMedicamento.add(lblDataFimCadastroMedicamento);

		 MaskFormatter mascaraHora = null;
		 try {
			 mascaraHora = new MaskFormatter("##:##");
			 mascaraHora.setPlaceholderCharacter('_'); // mostra _ nos espaços vazios
		 } catch (Exception e) {
			 e.printStackTrace();
		 }

		 txtHoraInicioCadastroMedicamento = new JFormattedTextField(mascaraHora);
		 txtHoraInicioCadastroMedicamento.setFont(new Font("Tahoma", Font.BOLD, 12));
		 txtHoraInicioCadastroMedicamento.setHorizontalAlignment(SwingConstants.CENTER);
		 txtHoraInicioCadastroMedicamento.setBounds(421, 200, 46, 20);
		 panelCadastrarMedicamento.add(txtHoraInicioCadastroMedicamento);

		 JLabel lblHoraInicioCadastroMedicamento = new JLabel("às:");
		 lblHoraInicioCadastroMedicamento.setForeground(new Color(0, 0, 0));
		 lblHoraInicioCadastroMedicamento.setHorizontalAlignment(SwingConstants.CENTER);
		 lblHoraInicioCadastroMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		 lblHoraInicioCadastroMedicamento.setBounds(384, 202, 27, 14);
		 panelCadastrarMedicamento.add(lblHoraInicioCadastroMedicamento);

		 btnCriarMedicamento = new JButton("Criar Medicamento");
		 btnCriarMedicamento.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 try {
					 String nome = txtNomeCadastroMedicamento.getText();
					 String dosagemTexto = txtDosagemCadastroMedicamento.getText();
					 String intervalo_AvisoTexto = txtIntervalo_AvisoCadastroMedicamento.getText();
					 String dataInicioTexto = txtDataInicioCadastroMedicamento.getText();
					 String horaDataInicioTexto = txtHoraInicioCadastroMedicamento.getText();
					 String dataFimTexto = txtDataFimCadastroMedicamento.getText();

					 if(nome.isEmpty() || dosagemTexto.isEmpty() || intervalo_AvisoTexto.isEmpty() || dataInicioTexto.isEmpty()) {
						 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
						 return;
					 }


					 if (horaDataInicioTexto.isEmpty() || horaDataInicioTexto.equals("__:__")) {
						 LocalTime agora = LocalTime.now();
						 horaDataInicioTexto = agora.format(DateTimeFormatter.ofPattern("HH:mm"));
						 txtHoraInicioCadastroMedicamento.setText(horaDataInicioTexto);
					 }            			

					 String dataHoraInicioString = dataInicioTexto + " " + horaDataInicioTexto;
					 try {
						 int dosagem = Integer.parseInt(dosagemTexto);
						 int intervalo_aviso = Integer.parseInt(intervalo_AvisoTexto);

						 if (dosagem <= 0 || intervalo_aviso <= 0) {
							 throw new IllegalArgumentException("A dosagem e o intervalo devem ser maiores que zero.");
						 }
						 DateTimeFormatter formatadorDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
						 LocalDateTime ultimaDose = LocalDateTime.parse(dataHoraInicioString + ":00", formatadorDataHora);

						 DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						 LocalDate dataInicio = LocalDate.parse(dataInicioTexto, formatadorData);
						 LocalDate dataFim = null; 
						 if (dataFimTexto != null && dataFimTexto.matches(".*\\d.*")) {
							 try {
								 dataFim = LocalDate.parse(dataFimTexto, formatadorData);
							 } catch (Exception e1) {
								 dataFim = null; 
							 }
						 }
						 if (dataFim != null && dataFim.isBefore(dataInicio)) {
							 throw new Exception("A data de fim não pode ser anterior à data de início.");
						 }

						 if (Session.idosoLogado != null) {
							 MedicamentoDAO dao = new MedicamentoDAO();
							 cardLayout.show(contentPane, "nome_LogadoInicial");

							 if(idMedicamentoEmEdicao == null) {
								 Medicamento med = new Medicamento(Session.idosoLogado.getId(), nome, dosagem, intervalo_aviso, dataInicio, dataFim, ultimaDose);
								 med.setUnidade((Medicamento.tipoUnidade) comboBoxUnidade.getSelectedItem());
								 dao.cadastrarMedicamento(med);
								 JOptionPane.showMessageDialog(null, "Medicamento cadastrado com sucesso");
								 cardLayout.show(contentPane, "nome_LogadoInicial");	
							 } else {
								 Medicamento med = new Medicamento(idMedicamentoEmEdicao, Session.idosoLogado.getId(), nome, dosagem, intervalo_aviso, dataInicio, dataFim, ultimaDose);
								 med.setUnidade((Medicamento.tipoUnidade) comboBoxUnidade.getSelectedItem());
								 dao.atualizarMedicamento(med);
								 JOptionPane.showMessageDialog(null, "Medicamento atualizado com sucesso!");
								 carregarListaMedicamentos();
								 cardLayout.show(contentPane, "nome_ListaMedicamentos");
							 }
						 }


					 }catch (java.time.format.DateTimeParseException ex) {
						 JOptionPane.showMessageDialog(null, "Data inválida! Use o formato dd/mm/aaaa");
					 } catch (Exception erro) {
						 JOptionPane.showMessageDialog(null, "Erro ao salvar: " + erro.getMessage());
					 } } catch (NumberFormatException erro) {
						 JOptionPane.showMessageDialog(null, "Por favor, digite apenas números nos campos de Dosagem e Frequência.", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
						 return;
					 } catch(IllegalArgumentException erro) {
						 JOptionPane.showMessageDialog(null, erro.getMessage(), "Valor Inválido", JOptionPane.WARNING_MESSAGE);
					 }
			 }
		 });

		 btnCriarMedicamento.setBackground(new Color(60, 179, 113));
		 btnCriarMedicamento.setForeground(new Color(255, 255, 255));
		 btnCriarMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnCriarMedicamento.setBounds(228, 288, 220, 37);
		 panelCadastrarMedicamento.add(btnCriarMedicamento);


		 JButton btnVoltarCadastroMedicamento = new JButton("←");
		 btnVoltarCadastroMedicamento.setForeground(new Color(255, 255, 255));
		 btnVoltarCadastroMedicamento.setBackground(new Color(60, 179, 113));
		 btnVoltarCadastroMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnVoltarCadastroMedicamento.setBounds(0, 0, 87, 37);
		 panelCadastrarMedicamento.add(btnVoltarCadastroMedicamento);

		 btnDeletarMedicamento = new JButton("Deletar Medicamento");
		 btnDeletarMedicamento.setForeground(new Color(255, 255, 255));
		 btnDeletarMedicamento.setBackground(new Color(60, 179, 113));
		 btnDeletarMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnDeletarMedicamento.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este medicamento?", "Excluir Medicamento", JOptionPane.YES_NO_OPTION);
				 if(confirmacao == JOptionPane.YES_OPTION) {
					 try {
						 MedicamentoDAO dao = new MedicamentoDAO();
						 dao.deletarMedicamento(idMedicamentoEmEdicao);
						 JOptionPane.showMessageDialog(null, "Medicamento excluído!");
						 carregarListaMedicamentos();
						 cardLayout.show(contentPane, "nome_ListaMedicamentos");
					 } catch (Exception erro) {
						 JOptionPane.showMessageDialog(null, "Erro ao excluir: " + erro.getMessage());
					 }
				 }
			 }
		 });
		 btnDeletarMedicamento.setBounds(228, 335, 220, 37);
		 panelCadastrarMedicamento.add(btnDeletarMedicamento);

		 btnVoltarCadastroMedicamento.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 txtNomeCadastroMedicamento.setText("");
				 txtDosagemCadastroMedicamento.setText("");
				 txtIntervalo_AvisoCadastroMedicamento.setText("");
				 txtDataInicioCadastroMedicamento.setText(null);
				 txtHoraInicioCadastroMedicamento.setText(null);
				 txtDataFimCadastroMedicamento.setText(null);

				 if(idMedicamentoEmEdicao != null) {
					 cardLayout.show(contentPane, "nome_ListaMedicamentos");
				 } else {
					 cardLayout.show(contentPane, "nome_LogadoInicial");
				 }
			 }
		 });


		 // -------------------------------------------------------
		 // TELA 6: TELA LISTA MEDICAMENTOS
		 // -------------------------------------------------------
		 panelListaMedicamentos = 	new JPanel();
		 panelListaMedicamentos.setBackground(new Color(245, 245, 245));
		 contentPane.add(panelListaMedicamentos, "nome_ListaMedicamentos");

		 tableMedicamentos = new JTable();
		 tableMedicamentos.setPreferredScrollableViewportSize(new Dimension(544, 430));
		 tableMedicamentos.setBounds(10,11,446,329);
		 tableMedicamentos.setModel(new DefaultTableModel( new Object[][] {}, new String[] {"ID", "Nome", "Dosagem", "Unidade", "Frequência", "Início", "Fim"}));

		 tableMedicamentos.addMouseListener(new MouseAdapter() { 
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 2) { 
					 int linha = tableMedicamentos.getSelectedRow();
					 if (linha != -1) {
						 Object valorId = tableMedicamentos.getValueAt(linha, 0);
						 int id = Integer.parseInt(valorId.toString());
						 abrirTelaEdicao(id);
					 }
				 }
			 }

		 });
		 tableMedicamentos.setDefaultEditor(Object.class, null);
		 tableMedicamentos.getTableHeader().setReorderingAllowed(false);
		 panelListaMedicamentos.setLayout(null);
		 JScrollPane scrollPanelListaMedicamentos = new JScrollPane(tableMedicamentos);
		 scrollPanelListaMedicamentos.setBounds(78, 10, 520, 430);
		 panelListaMedicamentos.add(scrollPanelListaMedicamentos);

		 JButton btnVoltarLista = new JButton("←");
		 btnVoltarLista.setBackground(new Color(60, 179, 113));
		 btnVoltarLista.setForeground(new Color(255, 255, 255));
		 btnVoltarLista.setBounds(0, 0, 54, 35);
		 btnVoltarLista.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnVoltarLista.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 cardLayout.show(contentPane, "nome_LogadoInicial");
			 }
		 });
		 panelListaMedicamentos.add(btnVoltarLista);

		 // -------------------------------------------------------
		 // TELA 7: TELA VER PERFIL
		 // -------------------------------------------------------
		 panelVerPerfil = new JPanel();
		 panelVerPerfil.setBackground(new Color(245, 245, 245));
		 contentPane.add(panelVerPerfil, "nome_verPerfil");
		 panelVerPerfil.setLayout(null);

		 JButton btnIrHistorico = new JButton("Ver Histórico");
		 btnIrHistorico.setForeground(new Color(255, 255, 255));
		 btnIrHistorico.setBackground(new Color(60, 179, 113));
		 btnIrHistorico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnIrHistorico.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 cardLayout.show(contentPane, "nome_historicoAlertas");
				 carregarHistoricoAlertas();
			 }
		 });
		 btnIrHistorico.setBounds(263, 298, 150, 37);
		 panelVerPerfil.add(btnIrHistorico);

		 JButton btnVoltarPerfil = new JButton("←");
		 btnVoltarPerfil.setBackground(new Color(60, 179, 113));
		 btnVoltarPerfil.setForeground(new Color(255, 255, 255));
		 btnVoltarPerfil.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 cardLayout.show(contentPane, "nome_LogadoInicial");
			 }
		 });
		 btnVoltarPerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnVoltarPerfil.setBounds(0, 0, 87, 37);
		 panelVerPerfil.add(btnVoltarPerfil);

		 txtCodigoConexaoPerfil = new JTextField();
		 txtCodigoConexaoPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		 txtCodigoConexaoPerfil.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtCodigoConexaoPerfil.setEditable(false);
		 txtCodigoConexaoPerfil.setBounds(273, 228, 129, 37);
		 panelVerPerfil.add(txtCodigoConexaoPerfil);
		 txtCodigoConexaoPerfil.setColumns(10);

		 JLabel lblCodigoConexao = new JLabel("Seu código:");
		 lblCodigoConexao.setForeground(new Color(0, 0, 0));
		 lblCodigoConexao.setHorizontalAlignment(SwingConstants.CENTER);
		 lblCodigoConexao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblCodigoConexao.setBounds(148, 229, 115, 30);
		 panelVerPerfil.add(lblCodigoConexao);

		 JLabel lblAvatar = new JLabel("*imagem*");
		 lblAvatar.setFont(new Font("Tahoma", Font.BOLD, 10));
		 lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		 lblAvatar.setBounds(258, 49, 160, 160);

		 URL urlAvatar = getClass().getResource("/avataricon.png");
		 if (urlAvatar != null) {
			 ImageIcon avatarIcon = new ImageIcon(urlAvatar);
			 Image imagemRedimensionada = avatarIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
			 avatarIcon = new ImageIcon(imagemRedimensionada);
			 lblAvatar = new JLabel(avatarIcon);
			 lblAvatar.setBounds(257, 11, 160, 160);
		 }
		 panelVerPerfil.add(lblAvatar);

		 // -------------------------------------------------------
		 // TELA 7: TELA LISTA HISTORICO ALERTAS
		 // -------------------------------------------------------        
		 panelHistoricoAlertas = new JPanel();
		 panelHistoricoAlertas.setBackground(new Color(245, 245, 245));
		 contentPane.add(panelHistoricoAlertas, "nome_historicoAlertas");
		 panelHistoricoAlertas.setLayout(null);

		 tableHistorico = new JTable();
		 tableHistorico.setBounds(10, 11, 446, 329);
		 tableHistorico.setModel(new DefaultTableModel(
				 new Object[][] {},
				 new String[] {
						 "ID", "Nome do Medicamento", "Data_Hora", "Status"
				 }
				 ));
		 tableHistorico.getTableHeader().setReorderingAllowed(false);
		 JScrollPane scrollPaneHistoricoAlertas = new JScrollPane(tableHistorico);
		 scrollPaneHistoricoAlertas.setSize(520, 430);
		 scrollPaneHistoricoAlertas.setLocation(78, 10);
		 panelHistoricoAlertas.add(scrollPaneHistoricoAlertas);

		 JButton btnVoltarHistorico = new JButton("←");
		 btnVoltarHistorico.setBackground(new Color(60, 179, 113));
		 btnVoltarHistorico.setForeground(new Color(255, 255, 255));
		 btnVoltarHistorico.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 cardLayout.show(contentPane, "nome_verPerfil");
				 carregarHistoricoAlertas();
			 }
		 });
		 btnVoltarHistorico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnVoltarHistorico.setBounds(0, 1, 54, 37);
		 panelHistoricoAlertas.add(btnVoltarHistorico);



		 // -------------------------------------------------------
		 // TELA 8: TELA VISUALIZAR META
		 // -------------------------------------------------------   

		 panelVisualizarMeta = new JPanel();
		 panelVisualizarMeta.setBackground(new Color(245, 245, 245));

		 contentPane.add(panelVisualizarMeta, "nome_visualizarMeta");
		 panelVisualizarMeta.setLayout(null);

		 JLabel lblMetaAtingidaVisualizarMeta = new JLabel("Meta atingida:");
		 lblMetaAtingidaVisualizarMeta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblMetaAtingidaVisualizarMeta.setForeground(new Color(0, 0, 0));
		 lblMetaAtingidaVisualizarMeta.setBounds(128, 144, 136, 28);
		 panelVisualizarMeta.add(lblMetaAtingidaVisualizarMeta);        
		 metaAtingida = new JLabel("Carregando...");
		 metaAtingida.setHorizontalAlignment(SwingConstants.LEFT);
		 metaAtingida.setFont(new Font("Tahoma", Font.BOLD, 20));
		 metaAtingida.setBounds(267, 144, 302, 28); 
		 panelVisualizarMeta.add(metaAtingida);      
		 JButton btnRegistrarConsumoVisualizar = new JButton("Registrar");
		 btnRegistrarConsumoVisualizar.setBackground(new Color(60, 179, 113));
		 btnRegistrarConsumoVisualizar.setForeground(new Color(255, 255, 255));
		 btnRegistrarConsumoVisualizar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnRegistrarConsumoVisualizar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 try {
					 int qtd = Integer.parseInt(txtQuantidadeRegistroVisualizar.getText());
					 if (qtd < 0) {
						 throw new Exception("A quantidade ingerida não pode ser negativa");
					 }
					 HidratacaoDAO dao = new HidratacaoDAO();
					 dao.registrarConsumo(Session.idosoLogado.getId(), qtd);
					 atualizarPainelHidratacao(metaAtingida);
					 txtQuantidadeRegistroVisualizar.setText("");
					 JOptionPane.showMessageDialog(null, "Hidratação registrada!");
				 } catch (NumberFormatException erroNum) {
					 JOptionPane.showMessageDialog(null, "Digite apenas números (em ml).");
				 } catch (Exception erro) {
					 JOptionPane.showMessageDialog(null, "Erro ao registrar: " + erro.getMessage());
				 }
			 }
		 });
		 btnRegistrarConsumoVisualizar.setBounds(277, 315, 120, 37);
		 panelVisualizarMeta.add(btnRegistrarConsumoVisualizar);

		 JButton btnIrConfiguracao = new JButton("Configurar Meta");
		 btnIrConfiguracao.setBackground(new Color(60, 179, 113));
		 btnIrConfiguracao.setForeground(new Color(255, 255, 255));
		 btnIrConfiguracao.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 btnIrConfiguracao.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 prepararTelaConfiguracaoMeta();
				 cardLayout.show(contentPane, "nome_configuracaoMeta");
			 }
		 });
		 btnIrConfiguracao.setBounds(523, 0, 153, 37);
		 panelVisualizarMeta.add(btnIrConfiguracao);

		 JButton btnVoltarVisualizar = new JButton("←");
		 btnVoltarVisualizar.setForeground(new Color(253, 245, 230));
		 btnVoltarVisualizar.setBackground(new Color(60, 179, 113));
		 btnVoltarVisualizar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnVoltarVisualizar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 cardLayout.show(contentPane, "nome_LogadoInicial"); 
			 }
		 });
		 btnVoltarVisualizar.setBounds(0, 0, 87, 37);
		 panelVisualizarMeta.add(btnVoltarVisualizar);

		 JLabel lblTextoBebeu = new JLabel("Se hidratou hoje? Registre!");
		 lblTextoBebeu.setForeground(new Color(0, 0, 0));
		 lblTextoBebeu.setHorizontalAlignment(SwingConstants.CENTER);
		 lblTextoBebeu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblTextoBebeu.setBounds(192, 205, 242, 43);
		 panelVisualizarMeta.add(lblTextoBebeu);

		 txtQuantidadeRegistroVisualizar = new JTextField();
		 txtQuantidadeRegistroVisualizar.setBackground(new Color(255, 255, 255));
		 txtQuantidadeRegistroVisualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtQuantidadeRegistroVisualizar.setBounds(348, 258, 86, 25);
		 panelVisualizarMeta.add(txtQuantidadeRegistroVisualizar);
		 txtQuantidadeRegistroVisualizar.setColumns(10);

		 JLabel lblQuantidade = new JLabel("Quantidade (ml):");
		 lblQuantidade.setForeground(new Color(0, 0, 0));
		 lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblQuantidade.setBounds(192, 258, 159, 21);
		 panelVisualizarMeta.add(lblQuantidade);

		 // -------------------------------------------------------
		 // TELA 9: TELA CONFIGURACAO_META
		 // -------------------------------------------------------   
		 panelConfiguracaoMeta = new JPanel();
		 panelConfiguracaoMeta.setBackground(new Color(245, 245, 245));
		 contentPane.add(panelConfiguracaoMeta, "nome_configuracaoMeta"); 
		 panelConfiguracaoMeta.setLayout(null);

		 JLabel lblMetaConfiguracao = new JLabel("Meta (litros):");
		 lblMetaConfiguracao.setForeground(new Color(0, 0, 0));
		 lblMetaConfiguracao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 lblMetaConfiguracao.setBounds(216, 141, 105, 25);
		 panelConfiguracaoMeta.add(lblMetaConfiguracao);

		 txtMetaConfiguracao = new JTextField();
		 txtMetaConfiguracao.setBackground(new Color(255, 255, 255));
		 txtMetaConfiguracao.setFont(new Font("Tahoma", Font.BOLD, 14));
		 txtMetaConfiguracao.setBounds(331, 142, 86, 25);
		 panelConfiguracaoMeta.add(txtMetaConfiguracao);

		 JLabel lblHoraComecoConfiguracao = new JLabel("Horário de Início do Alerta (HH:mm):");
		 lblHoraComecoConfiguracao.setForeground(new Color(0, 0, 0));
		 lblHoraComecoConfiguracao.setHorizontalAlignment(SwingConstants.CENTER);
		 lblHoraComecoConfiguracao.setFont(new Font("Tahoma", Font.BOLD, 18));
		 lblHoraComecoConfiguracao.setBounds(15, 179, 310, 14);
		 panelConfiguracaoMeta.add(lblHoraComecoConfiguracao);

		 txtHoraComecoConfiguracao = new JTextField();
		 txtHoraComecoConfiguracao.setBackground(new Color(255, 255, 255));
		 txtHoraComecoConfiguracao.setBounds(331, 177, 86, 25);
		 panelConfiguracaoMeta.add(txtHoraComecoConfiguracao);
		 txtHoraComecoConfiguracao.setColumns(10);

		 JLabel lblHoraFimConfiguracao = new JLabel("Horário de Parada do Alerta (HH:mm):");
		 lblHoraFimConfiguracao.setForeground(new Color(0, 0, 0));
		 lblHoraFimConfiguracao.setHorizontalAlignment(SwingConstants.CENTER);
		 lblHoraFimConfiguracao.setFont(new Font("Tahoma", Font.BOLD, 18));
		 lblHoraFimConfiguracao.setBounds(10, 214, 315, 15);
		 panelConfiguracaoMeta.add(lblHoraFimConfiguracao);

		 txtHoraFimConfiguracao = new JTextField();
		 txtHoraFimConfiguracao.setBackground(new Color(255, 255, 255));
		 txtHoraFimConfiguracao.setBounds(331, 212, 86, 25);
		 panelConfiguracaoMeta.add(txtHoraFimConfiguracao);
		 txtHoraFimConfiguracao.setColumns(10);

		 JButton btnVoltarConfiguracao = new JButton("←");
		 btnVoltarConfiguracao.setForeground(new Color(255, 255, 255));
		 btnVoltarConfiguracao.setBackground(new Color(60, 179, 113));
		 btnVoltarConfiguracao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnVoltarConfiguracao.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 cardLayout.show(contentPane, "nome_visualizarMeta"); 
			 }
		 });
		 btnVoltarConfiguracao.setBounds(0, 0, 87, 37);
		 panelConfiguracaoMeta.add(btnVoltarConfiguracao);

		 JButton btnSalvarConfiguracao = new JButton("Salvar");
		 btnSalvarConfiguracao.setForeground(new Color(255, 255, 255));
		 btnSalvarConfiguracao.setBackground(new Color(60, 179, 113));
		 btnSalvarConfiguracao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnSalvarConfiguracao.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 try {
					 salvarConfiguracao();
					 atualizarPainelHidratacao(metaAtingida);
					 cardLayout.show(contentPane, "nome_visualizarMeta"); 
				 } catch (Exception erroSalvar) {          	
					 JOptionPane.showMessageDialog(null, "Erro ao salvar: " + erroSalvar.getMessage());
				 }
			 }
		 });
		 btnSalvarConfiguracao.setBounds(263, 282, 154, 37);
		 panelConfiguracaoMeta.add(btnSalvarConfiguracao);
	 }
	 // -------------------------------------------------------
	 // ULTIMA. METODOS
	 // -------------------------------------------------------          


	 public void salvarCadastro() {
		 try {
			 String nome = txtNome.getText();
			 char[] senhaChar = txtSenhaCadastro.getPassword();
			 String senha = new String(senhaChar);
			 validarSenha(senha);
			 String dataTexto = txtDataDeNascimento.getText();
			 String email = txtEmail.getText();
			 String cpf = txtCPF.getText();

			 if(cpf.length() != 11) {
				 throw new Exception("O CPF deve conter exatemente 11 números, sem pontos ou traços");
			 }

			 if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
				 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
				 return;
			 }
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			 LocalDate dataNascimento = LocalDate.parse(dataTexto, formatter);


			 Idoso novoIdoso = new Idoso(nome, email, senha, dataNascimento, cpf);


			 IdosoDAO dao = new IdosoDAO();
			 dao.cadastrarIdoso(novoIdoso);
			 JOptionPane.showMessageDialog(null, "Idoso cadastrado com sucesso!");

			 txtNome.setText("");
			 txtSenhaCadastro.setText("");
			 txtEmail.setText("");
			 txtCPF.setText("");
			 txtDataDeNascimento.setValue(null);
			 cardLayout.show(contentPane, "nome_bemvindo");


		 } catch (java.time.format.DateTimeParseException ex) {
			 JOptionPane.showMessageDialog(null, "Data inválida! Use o formato dd/mm/aaaa");
		 } catch (Exception erro) {
			 JOptionPane.showMessageDialog(null, "Erro ao salvar: " + erro.getMessage());
			 if(erro.getMessage().contains("CPF")) {
				 txtCPF.setBorder(new LineBorder(Color.RED, 2));
			 } else if (erro.getMessage().contains("email") || erro.getMessage().contains("e-mail")) {
				 txtEmail.setBorder(new LineBorder(Color.RED, 2));
			 }
		 }
	 }




	 private void prepararTelaLogadoInicial() {
		 if (Session.idosoLogado != null) {
			 String nome = Session.idosoLogado.getNome();
			 lblBoasVindasUsuario.setText("Olá " + nome + ", seja bem-vindo(a)!");
		 }
	 }


	 private void prepararTelaVerPerfil() {
		 if(Session.idosoLogado != null) {
			 String codigo = Session.idosoLogado.getCodigoConexao();
			 txtCodigoConexaoPerfil.setText(codigo);
		 }
	 }

	 private void carregarListaMedicamentos() {
		 try {
			 DefaultTableModel modelo = (DefaultTableModel) tableMedicamentos.getModel();
			 modelo.setNumRows(0);

			 if(Session.idosoLogado != null) {
				 int id = Session.idosoLogado.getId();

				 MedicamentoDAO dao = new MedicamentoDAO();
				 List<Medicamento> lista = dao.listarPorIdoso(id);
				 DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				 
				 
				 for (Medicamento m : lista) {
					 String dataFimFormatada = "Contínuo";
	    				if (m.getDataFim() != null) {
	    					dataFimFormatada = m.getDataFim().format(fmt);
	    				}
					 modelo.addRow(new Object[]{							 
							 m.getId(),
							 m.getNome(),
							 m.getDosagem(),
							 m.getUnidade().name(),
							 m.getIntervalo_Aviso() + " horas",
							 m.getDataInicio().format(fmt),
							 dataFimFormatada
					 });
				 }
			 }

		 } catch(Exception e) {
			 JOptionPane.showMessageDialog(null, "Erro ao buscar medicamentos: " + e.getMessage());
		 }
	 }

	 private void carregarHistoricoAlertas() {
		 try {
			 DefaultTableModel modelo = (DefaultTableModel) tableHistorico.getModel();
			 modelo.setNumRows(0);
			 if(Session.idosoLogado != null) {
				 int id = Session.idosoLogado.getId();

				 AlertaMedicamentoDAO dao = new AlertaMedicamentoDAO();
				 List<AlertaMedicamento> historico = dao.buscarHistoricoAlertas(id);

				 for (AlertaMedicamento a : historico) {
					 modelo.addRow(new Object[]{
							 a.getId(),
							 a.getNomeMedicamento(), 
							 a.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), 
							 a.getStatus(),
					 });
				 }
			 }

		 } catch(Exception e) {
			 JOptionPane.showMessageDialog(null, "Erro ao buscar histórico: " + e.getMessage());
		 }
	 }

	 private void abrirTelaEdicao(int idMedicamento) {
		 try {
			 MedicamentoDAO dao = new MedicamentoDAO();
			 Medicamento med = dao.buscarPorId(idMedicamento);
			 if (med != null) {
				 this.idMedicamentoEmEdicao = med.getId();
				 txtNomeCadastroMedicamento.setText(med.getNome());
				 txtDosagemCadastroMedicamento.setText(String.valueOf(med.getDosagem()));
				 txtIntervalo_AvisoCadastroMedicamento.setText(String.valueOf(med.getIntervalo_Aviso()));

				 DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				 DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HH:mm");

				 if (med.getDataInicio() != null) {
					 txtDataInicioCadastroMedicamento.setText(med.getDataInicio().format(fmtData));
				 }

				 if(med.getUltima_Dose() != null) {
					 txtHoraInicioCadastroMedicamento.setText(med.getUltima_Dose().format(fmtHora));
				 } 

				 if (med.getDataFim() != null) {
					 txtDataFimCadastroMedicamento.setText(med.getDataFim().format(fmtData));
				 } else {
					 txtDataFimCadastroMedicamento.setText("");
				 }


				 comboBoxUnidade.setSelectedItem(med.getUnidade());
				 btnCriarMedicamento.setText("Salvar Alterações");
				 btnDeletarMedicamento.setVisible(true);
				 cardLayout.show(contentPane, "nome_cadastrarMedicamento");

			 }
		 } catch(Exception e) {
			 JOptionPane.showMessageDialog(null, "Erro ao carregar edição: " + e.getMessage());
		 }
	 }

	 private void prepararTelaCadastroNovo() {
		 this.idMedicamentoEmEdicao = null;

		 txtNomeCadastroMedicamento.setText("");
		 txtDosagemCadastroMedicamento.setText("");
		 txtIntervalo_AvisoCadastroMedicamento.setText("");
		 txtDataInicioCadastroMedicamento.setText(null); 
		 txtDataInicioCadastroMedicamento.setText(""); 
		 txtHoraInicioCadastroMedicamento.setText(null);
		 txtHoraInicioCadastroMedicamento.setText("");
		 txtDataFimCadastroMedicamento.setText(null);
		 txtDataFimCadastroMedicamento.setText("");
		 if(btnDeletarMedicamento != null) {
			 btnDeletarMedicamento.setVisible(false);
		 }

		 btnCriarMedicamento.setText("Criar Medicamento");

		 cardLayout.show(contentPane, "nome_cadastrarMedicamento");
	 }

	 private void validarSenha(String senha) throws Exception {
		 if(senha.length() < 8) {
			 throw new Exception("A senha deve ter pelo menos 8 caracteres");
		 }
		 if (!senha.matches(".*[A-Z].*") || !senha.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
			 throw new Exception("A senha deve conter pelo menos uma letra maiúscula e um caractere especial");
		 }


	 }

	 public static void trazerJanelaPrincipalParaFrente() {
		 for (Frame frame : Frame.getFrames()) {
			 if (frame instanceof JFrame && frame.getTitle().equals("VidaFácil")) {       
				 frame.setVisible(true);
				 frame.toFront();
				 frame.setState(Frame.NORMAL); 
				 break;
			 }
		 }
	 }

	 private void atualizarPainelHidratacao(JLabel lblTextoMeta) {
		 try {
			 if (Session.idosoLogado == null) return;

			 HidratacaoDAO dao = new HidratacaoDAO();
			 int idIdoso = Session.idosoLogado.getId();
			 MetaDiaria meta = dao.buscarMeta(idIdoso);
			 int totalBebeu = dao.calcularTotalConsumidoHoje(idIdoso);
			 int metaValor = meta.getMetaDiaria();
			 lblTextoMeta.setText(totalBebeu + "ml / " + metaValor + "ml");

			 if (totalBebeu >= metaValor) {
				 lblTextoMeta.setForeground(new Color(0, 128, 0)); // Verde
				 lblTextoMeta.setText("META ATINGIDA! (" + totalBebeu + "ml)");
			 } else {
				 lblTextoMeta.setForeground(Color.BLACK);
			 }

		 } catch (Exception e) {
			 System.out.print("Erro: " + e.getMessage());
		 }
	 }

	 private void salvarConfiguracao() throws Exception {
		 HidratacaoDAO dao = new HidratacaoDAO();
		 MetaDiaria meta = new MetaDiaria();               
		 meta.setId_idoso(Session.idosoLogado.getId());
		 double litros = Double.parseDouble(txtMetaConfiguracao.getText().replace(",", "."));
		 if (litros  < 0) {
			 throw new Exception("A meta não pode conter números negativos");
		 }
		 meta.setMetaDiaria((int)(litros * 1000));
		 meta.setIntervaloAviso(2); 
		 String horaInicioStr = txtHoraComecoConfiguracao.getText();
		 if(horaInicioStr.length() == 5) horaInicioStr += ":00"; 
		 meta.setHoraInicio(java.time.LocalTime.parse(horaInicioStr));

		 String horaFimStr = txtHoraFimConfiguracao.getText();
		 if(horaFimStr.length() == 5) {
			 horaFimStr += ":00";
		 }
		 meta.setHoraFim(java.time.LocalTime.parse(horaFimStr));

		 dao.atualizarrMeta(meta);
		 JOptionPane.showMessageDialog(null, "Configurações salvas com sucesso!");
		 cardLayout.show(contentPane, "nome_visualizarMeta");
	 } 


	 private void prepararTelaConfiguracaoMeta() {
		 if (Session.idosoLogado != null) {
			 try {
				 HidratacaoDAO dao = new HidratacaoDAO();
				 MetaDiaria meta = dao.buscarMeta(Session.idosoLogado.getId());
				 meta.setId_idoso(Session.idosoLogado.getId());
				 txtMetaConfiguracao.setText(String.valueOf(meta.getMetaDiaria() / 1000));
				 DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HH:mm");
				 txtHoraComecoConfiguracao.setText(meta.getHoraInicio().format(fmtHora));
				 txtHoraFimConfiguracao.setText(meta.getHoraFim().format(fmtHora));
			 } catch (Exception e) {
				 System.out.print("Erro: " + e.getMessage());
			 }
		 }

	 }
}
