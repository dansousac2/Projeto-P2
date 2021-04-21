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
import javax.swing.JTextField;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class JanelaAlteraçãoDeSenha extends JanelaPadraoLivreiroUsuario{
	JTextField codigo;
	JButton botaoConfirmar;
	JButton botaoEnviarCodigo;
	String codigoRedefinirSenha;
	ArrayList<Usuario> BancoDeDadosUsuarios;
	String comparar;
	int max = 9999;
	int min = 0000;
	int numeroAleatorio;
	public class OuvinteEnviarCodigo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int cont = 0;
			for(Usuario U: BancoDeDadosUsuarios) {
				if(U.getEmail().equals(email.getText())){
					Random rand = new Random();
				    numeroAleatorio = rand.nextInt((max - min) + 1) + min;
				    codigoRedefinirSenha = ""+numeroAleatorio;
				    Properties props = new Properties();
					props.put("mail.smtp.user", "leonardo.lucena@academico.ifpb.edu.br"); 
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
			        		return new PasswordAuthentication("leonardo.lucena@academico.ifpb.edu.br", "Overlok00$");
			        	}
			        });
			        session.setDebug(true);
			        try{
			        	Message message = new MimeMessage(session);
			        	message.setFrom(new InternetAddress("leonardo.lucena@academico.ifpb.edu.br"));
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
					for(Usuario U: BancoDeDadosUsuarios) {
						if(U.getEmail().equals(email.getText())){
							U.setSenha(senha.getText());
							JOptionPane.showMessageDialog(null, "A sua senha foi redefinida!");
							new JanelaLoguin(BancoDeDadosUsuarios);
							dispose();
							break;
						}
					}
				}
			}
		}
	}
	public JanelaAlteraçãoDeSenha(ArrayList<Usuario> Dados) {
		super();
		BancoDeDadosUsuarios = Dados;
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
	}
	void Incremento4() {
		String nome = "email";
		email = new JTextField();
		email.setBounds(165, 230, 250, 25);
		email.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		email.setText(nome);
		OuvinteFocus OuvinteF = new OuvinteFocus(email, nome);
		OuvinteEmail Ouvinte = new OuvinteEmail(email);
		email.addKeyListener(Ouvinte);
		email.addFocusListener(OuvinteF);
		add(email);
	}
	void Incremento5() {
		img = new ImageIcon("24p/Slock-30.png");
		titulo = new JLabel(img);
		titulo.setBounds(140, 300, 25, 25);
		titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(titulo);
	}
	void Incremento6() {	
		String nome = "Código Recebido";	
		codigo = new JTextField();
		codigo.setBounds(165, 300, 250, 25);
		codigo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		codigo.setText(nome);
		codigo.enable(false);
		OuvinteFocus OuvinteF = new OuvinteFocus(codigo, nome);
		codigo.addFocusListener(OuvinteF);
		add(codigo);
	}
	void Incremento7() {
		String nome = "Senha";	
		senha = new JTextField();
		senha.setBounds(70, 390, 250, 25);
		senha.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		senha.setText(nome);
		senha.enable(false);
		OuvinteFocus OuvinteF = new OuvinteFocus(senha, nome);
		senha.addFocusListener(OuvinteF);
		add(senha);
	}
	void Incremento8() {
		String nome = "Confirme a Senha";
		confirmeSenha = new JTextField();
		confirmeSenha.setBounds(390, 390, 250, 25);
		confirmeSenha.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		confirmeSenha.setText(nome);
		confirmeSenha.enable(false);
		OuvinteFocus OuvinteF = new OuvinteFocus(confirmeSenha, nome);
		confirmeSenha.addFocusListener(OuvinteF);
		add(confirmeSenha);
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
