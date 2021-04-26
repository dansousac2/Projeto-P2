import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
public class JanelaCadastrarLivreiro extends JanelaPadraoLivreiroUsuario{
	CentralLivro BancoDeDados;
	PersistenciaLivros Persistencia = new PersistenciaLivros();
	public class OuvinteBotaoCadastrar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int cont = 0;
			for(int i = 0; i<senha.getText().length();i++) {
				cont++;
			}
			if(cont<5) {
				JOptionPane.showMessageDialog(null, "A senha deve ter no minimo 5 caracteres", "Tente novamente", JOptionPane.ERROR_MESSAGE);
			}
			else {
			int Cont = 0;
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
	        	message.setSubject("Livraria Stile");
	        	message.setText("Seja Bem Vindo!");
	        	Transport.send(message);
	        	JOptionPane.showMessageDialog(null, "Codigo Enviado");
	        	Cont++;
	        }catch(MessagingException E) {
	        	System.out.println("Ocorreu um erro");
	        }
			if(Cont == 0) {
				JOptionPane.showMessageDialog(null, "email inválido", "Tente novamente", JOptionPane.ERROR_MESSAGE);
			}
			else if(getNome().getText().equals("") || getEmail().getText().equals("") || getSenha().getText().equals("")){
				JOptionPane.showMessageDialog(null, "Há algum campo vazio, tente novamente", "Campo vazio", JOptionPane.ERROR_MESSAGE);
			}
			else if(!getSenha().getText().equals(getConfirmeSenha().getText())) {
				JOptionPane.showMessageDialog(null, "As senhas digitadas são incompativeis, tente novamente.", "Senha Invalida", JOptionPane.ERROR_MESSAGE);
			}
			else {
				usuario = new Usuario(getNome().getText(), getEmail().getText(), getSenha().getText());
				usuario.setEhLivreiro(true);
				BancoDeDados.getUsuariosCadastrados().add(usuario);
				try {
					Persistencia.salvarCentral(BancoDeDados, "Dados_Livraria.xml");
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Seja bem vindo!", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new ListarLivrosLivreiro(usuario);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
	public JanelaCadastrarLivreiro(CentralLivro Dados) {
		super();
		BancoDeDados = Dados;
	}
	public void Incremento1() {
		titulo = new JLabel("Cadastrar Livreiro");
		titulo.setFont(new Font("Arial", Font.BOLD, 28));
		titulo.setBounds(210, 30, 250, 50);
		add(titulo);
		}
		public void Incremento2() {
		nome = new JTextField();
		nome.setBounds(70, 230, 250, 25);
		nome.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(nome);
		}
		public void Incremento3() {
		senha = new JPasswordField();
		senha.setBounds(70, 320, 250, 25);
		senha.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(senha);
		}
		public void Incremento4() {
		email = new JTextField();
		email.setBounds(390, 230, 250, 25);
		email.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		OuvinteEmail Ouvinte = new OuvinteEmail(email);
		email.addKeyListener(Ouvinte);
		add(email);
		}
		public void Incremento5() {
		confirmeSenha = new JPasswordField();
		confirmeSenha.setBounds(390, 320, 250, 25);
		confirmeSenha.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(confirmeSenha);
		}
		public void Incremento6() {
		botao = new JButton("Cadastrar");
		botao.setBounds(295, 410, 100, 25);
		OuvinteBotaoCadastrar Ouvinte = new OuvinteBotaoCadastrar();
		botao.addActionListener(Ouvinte);
		add(botao);
		}
		public void Incremento7() {
		img = new ImageIcon("24p/email-24.png");
		titulo = new JLabel(img);
		titulo.setBounds(365, 230, 25, 25);
		titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(titulo);
		}
		public void Incremento8() {
		img = new ImageIcon("100p/livro-logotipo.png");
		titulo = new JLabel(img);
		titulo.setBounds(285, 85, 100, 100);
		add(titulo);
		}
		public void Incremento9() {
		img = new ImageIcon("24p/key-24p.png");
		titulo = new JLabel(img);
		titulo.setBounds(45, 320, 25, 25);
		titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(titulo);
		}
		public void Incremento10() {
		img = new ImageIcon("24p/key-24p.png");
		titulo = new JLabel(img);
		titulo.setBounds(365, 320, 25, 25);
		titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(titulo);
		}
		public void Incremento11() {
		img = new ImageIcon("24p/usuario.png");
		titulo = new JLabel(img);
		titulo.setBounds(45, 230, 25, 25);
		titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(titulo);
		}
		public void Incremento12() {
		titulo = new JLabel("Nome de Usuário");
		titulo.setFont(new Font("ARBARKLEY", Font.TYPE1_FONT, 13));
		titulo.setBounds(45, 205, 120, 25);
		add(titulo);
		titulo = new JLabel("Email");
		titulo.setFont(new Font("ARBARKLEY", Font.TYPE1_FONT, 13));
		titulo.setBounds(365, 205, 80, 25);
		add(titulo);
		titulo = new JLabel("Senha");
		titulo.setFont(new Font("ARBARKLEY", Font.TYPE1_FONT, 13));
		titulo.setBounds(45, 295, 80, 25);
		add(titulo);
		titulo = new JLabel("Confirme a Senha");
		titulo.setFont(new Font("ARBARKLEY", Font.TYPE1_FONT, 13));
		titulo.setBounds(365, 295, 120, 25);
		add(titulo);
		}
		public void Incremento13() {
		}
}
