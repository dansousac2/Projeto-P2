import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class JanelaCadastrarUsuario extends JanelaPadraoLivreiroUsuario{
	CentralLivro BancoDeDados;
	PersistenciaLivros Percistencia = new PersistenciaLivros();
	public class OuvinteBotaoCadastrar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int cont = 0;
			for(int i = 0; i<senha.getText().length();i++) {
				cont++;
			}
			if(cont<5) {
				JOptionPane.showMessageDialog(null, "A senha deve ter no minimo 5 caracteres", "Tente novamente", JOptionPane.ERROR_MESSAGE);
			}
			else if(getNome().getText().equals("Nome de usuário") || getEmail().getText().equals("email") || getSenha().getText().equals("Senha")){
				JOptionPane.showMessageDialog(null, "Há algum campo vazio, tente novamente", "Campo vazio", JOptionPane.ERROR_MESSAGE);
			}
			else if(!getSenha().getText().equals(getConfirmeSenha().getText())) {
				JOptionPane.showMessageDialog(null, "As senhas digitadas são incompativeis, tente novamente.", "Senha Invalida", JOptionPane.ERROR_MESSAGE);
			}
			else {
				usuario = new Usuario(getNome().getText(), getEmail().getText(), getSenha().getText());
				BancoDeDados.getUsuariosCadastrados().add(usuario);
				try {
					Percistencia.salvarCentral(BancoDeDados, "Dados_Livraria.xml");
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Seja bem vindo!", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new ListarLivrosUsuario(usuario);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	public class OuvinteBotaoVoltarCadastrarUsuario implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new JanelaLoguin(BancoDeDados);
		}

	}
	
	public JanelaCadastrarUsuario(CentralLivro Dados) {
		super();
		BancoDeDados = Dados;
	}
	public void Incremento1() {
	titulo = new JLabel("Cadastrar Usuário");
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
	img = new ImageIcon("100p/livro-logotipo.png");
	titulo = new JLabel(img);
	titulo.setBounds(285, 85, 100, 100);
	add(titulo);
	}
	public void Incremento8() {
	botao = new JButton("<< Voltar");
	botao.setBounds(45, 480, 100, 25);
	OuvinteBotaoVoltarCadastrarUsuario Ouvinte = new OuvinteBotaoVoltarCadastrarUsuario();
	botao.addActionListener(Ouvinte);
	add(botao);	
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
	img = new ImageIcon("24p/email-24.png");
	titulo = new JLabel(img);
	titulo.setBounds(365, 230, 25, 25);
	titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
	add(titulo);
	}
	public void Incremento13() {
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
}
