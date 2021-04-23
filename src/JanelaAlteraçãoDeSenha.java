import java.awt.Color;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class JanelaAlteraçãoDeSenha extends JanelaPadraoLivreiroUsuario{
	JTextField codigo;
	JButton botaoConfirmar;
	JButton botaoEnviarCodigo;
	String codigoRedefinirSenha;
	CentralLivro BancoDeDados;
	PersistenciaLivros persistencia = new PersistenciaLivros();
	String comparar;
	int numeroAleatorio;
	public class OuvinteEnviarCodigo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int cont = 0;
			for(Usuario U: BancoDeDados.getUsuariosCadastrados()) {
				if(U.getEmail().equals(email.getText())){
					Random rand = new Random();
				    numeroAleatorio = rand.nextInt((9999 - 0000) + 1) + 0000;
				    codigoRedefinirSenha = ""+numeroAleatorio;
				    Properties props = new Properties();
					props.put("mail.smtp.user", "estanteonlineifpb@gmail.com"); 
			        props.put("mail.smtp.host", "smtp.gmail.com"); 
			        props.put("mail.smtp.port", "25"); 
			        props.put("mail.debug", "true"); 
			        props.put("mail.smtp.auth", "true"); 
			        props.put("mail.smtp.starttls.enable","true"); 
			        props.put("mail.smtp.EnableSSL.enable","true");
			        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
			        props.setProperty("mail.smtp.socketFactory.fallback", "false");   
			        props.setProperty("mail.smtp.port", "465");   
			        props.setProperty("mail.smtp.socketFactory.port", "465");
			        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			        	protected PasswordAuthentication getPasswordAuthentication() 
			        	{
			        		return new PasswordAuthentication("estanteonlineifpb@gmail.com", "estante123");
			        	}
			        });
			        session.setDebug(true);
			        try{
			        	Message message = new MimeMessage(session);
			        	message.setFrom(new InternetAddress("estanteonlineifpb@gmail.com"));
			        	Address[] toUser = InternetAddress.parse(email.getText());
			        	message.setRecipients(Message.RecipientType.TO, toUser);
			        	message.setSubject("Codigo para Redefinição de Senha");
			        	message.setText(codigoRedefinirSenha);
			        	Transport.send(message);
			        	JOptionPane.showMessageDialog(null, "Codigo Enviado");
			        	codigo.setEnabled(true);
			        	botaoConfirmar.setEnabled(true);
			        	botaoEnviarCodigo.setEnabled(false);
			        	email.setEnabled(false);
			        	cont++;
			        	break;
			        }catch(MessagingException E) {
			        	System.out.println("Ocorreu um erro");
			        	
			        }
				}
			}
			if(cont == 0) {
			JOptionPane.showMessageDialog(null, "email inválido", "Tente novamente", JOptionPane.ERROR_MESSAGE);
			}
	}
}
	public class OuvinteBotaoConfirmar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String numero = ""+numeroAleatorio;
			if(codigo.getText().equals(numero)) {
				JOptionPane.showMessageDialog(null, "Codigo confirmado");
				senha.setEnabled(true);
				confirmeSenha.setEnabled(true);
				botao.setEnabled(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Codigo Invalido", "Tente novamente", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public class OuvinteBotaoRedefinirSenha implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!senha.getText().equals(confirmeSenha.getText())) {
				JOptionPane.showMessageDialog(null, "O campo confirme senha é incompativel", "Tente novamente", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int cont = 0;
				for(int i = 0; i<senha.getText().length();i++) {
					cont++;
				}
				if(cont<5) {
					JOptionPane.showMessageDialog(null, "A senha deve ter no minimo 5 caracteres", "Tente novamente", JOptionPane.ERROR_MESSAGE);
				}
				else {
					for(Usuario U: BancoDeDados.getUsuariosCadastrados()) {
						if(U.getEmail().equals(email.getText())){
							U.setSenha(senha.getText());
							try {
								persistencia.salvarCentral(BancoDeDados, "Dados_Livraria.xml");
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "A sua senha foi redefinida!");
							new JanelaLoguin(BancoDeDados);
							dispose();
							break;
						}
					}
				}
			}
		}
	}
	public JanelaAlteraçãoDeSenha(CentralLivro Dados) {
		super();
		BancoDeDados = Dados;
	}
	void Incremento1() {
		titulo = new JLabel("Alteração de Senha");
		titulo.setFont(new Font("Arial", Font.BOLD, 22));
		titulo.setBounds(245, 150, 200, 50);
		add(titulo);
	}
	void Incremento2() {
		img = new ImageIcon("100p/key.png");
		titulo = new JLabel(img);
		titulo.setBounds(285, 30, 100, 100);
		add(titulo);
	}
	void Incremento3() {
		img = new ImageIcon("24p/email-24.png");
		titulo = new JLabel(img);
		titulo.setBounds(140, 230, 25, 25);
		titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(titulo);
		titulo = new JLabel("Email");
		titulo.setFont(new Font("Arial", Font.BOLD, 13));
		titulo.setBounds(140, 205, 80, 25);
		add(titulo);
	}
	void Incremento4() {
		email = new JTextField();
		email.setBounds(165, 230, 250, 25);
		email.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		OuvinteEmail Ouvinte = new OuvinteEmail(email);
		email.addKeyListener(Ouvinte);
		add(email);
	}
	void Incremento5() {
		img = new ImageIcon("24p/Slock-30.png");
		titulo = new JLabel(img);
		titulo.setBounds(140, 300, 25, 25);
		titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(titulo);
		titulo = new JLabel("Código para redefinição de senha");
		titulo.setFont(new Font("Arial", Font.BOLD, 13));
		titulo.setBounds(140, 275, 160, 25);
		add(titulo);
	}
	void Incremento6() {	
		codigo = new JTextField();
		codigo.setBounds(165, 300, 250, 25);
		codigo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		codigo.enable(false);
		add(codigo);
	}
	void Incremento7() {
		senha = new JPasswordField();
		senha.setBounds(70, 390, 250, 25);
		senha.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		senha.enable(false);
		add(senha);
		titulo = new JLabel("Senha");
		titulo.setFont(new Font("Arial", Font.BOLD, 13));
		titulo.setBounds(45, 365, 80, 25);
		add(titulo);
	}
	void Incremento8() {
		confirmeSenha = new JPasswordField();
		confirmeSenha.setBounds(390, 390, 250, 25);
		confirmeSenha.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		confirmeSenha.enable(false);
		add(confirmeSenha);
		titulo = new JLabel("Confirme a Senha");
		titulo.setFont(new Font("Arial", Font.BOLD, 13));
		titulo.setBounds(365, 365, 120, 25);
		add(titulo);
	}
	void Incremento9() {
		img = new ImageIcon("24p/key-24p.png");
		titulo = new JLabel(img);
		titulo.setBounds(45, 390, 25, 25);
		titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(titulo);
	}
	void Incremento10() {
		img = new ImageIcon("24p/key-24p.png");
		titulo = new JLabel(img);
		titulo.setBounds(365, 390, 25, 25);
		titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(titulo);
	}
	void Incremento11() {
		botao = new JButton("Redefinir Senha");
		botao.setBounds(265, 450, 130, 25);
		botao.enable(false);
		botao.addActionListener(new OuvinteBotaoRedefinirSenha());
		add(botao);
	}
	void Incremento12() {
		botaoEnviarCodigo = new JButton("Enviar Código");
		botaoEnviarCodigo.setBounds(440, 230, 125, 25);
		OuvinteEnviarCodigo Ouvinte = new OuvinteEnviarCodigo();
		botaoEnviarCodigo.addActionListener(Ouvinte);
		add(botaoEnviarCodigo);
	}
	void Incremento13() {
		botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setBounds(440, 300, 115, 25);
		botaoConfirmar.enable(false);
		botaoConfirmar.addActionListener(new OuvinteBotaoConfirmar());
		add(botaoConfirmar);
	}
	public JTextField getCodigo() {
		return codigo;
	}
	public void setCodigo(JTextField codigo) {
		this.codigo = codigo;
	}
	public JButton getBotaoConfirmar() {
		return botaoConfirmar;
	}
	public void setBotaoConfirmar(JButton botaoConfirmar) {
		this.botaoConfirmar = botaoConfirmar;
	}
	public JButton getBotaoEnviarCodigo() {
		return botaoEnviarCodigo;
	}
	public void setBotaoEnviarCodigo(JButton botaoEnviarCodigo) {
		this.botaoEnviarCodigo = botaoEnviarCodigo;
	}
}
