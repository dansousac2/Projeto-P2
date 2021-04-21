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
import javax.swing.JTextField;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class JanelaCadastrarUsuario extends JanelaPadraoLivreiroUsuario{
	ArrayList<Usuario> BancoDeDadosUsuarios;
	public class OuvinteBotaoCadastrar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(getNome().getText().equals("Nome de usuário") || getEmail().getText().equals("email") || getSenha().getText().equals("Senha")){
				JOptionPane.showMessageDialog(null, "Há algum campo vazio, tente novamente", "Campo vazio", JOptionPane.ERROR_MESSAGE);
			}
			else if(!getSenha().getText().equals(getConfirmeSenha().getText())) {
				JOptionPane.showMessageDialog(null, "As senhas digitadas são incompativeis, tente novamente.", "Senha Invalida", JOptionPane.ERROR_MESSAGE);
			}
			else {
				usuario = new Usuario(getNome().getText(), getEmail().getText(), getSenha().getText());
				BancoDeDadosUsuarios.add(usuario);
				xstream = new XStream(new DomDriver());
				XML = xstream.toXML(BancoDeDadosUsuarios);
				arquivo = new File("Cadastro de Usuários Interno");
				try {
					if(!arquivo.exists()) {
						arquivo.createNewFile();
					}
					PrintWriter gravar = new PrintWriter(arquivo);
					gravar.print(XML);
					gravar.close();
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Seja bem vindo!", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new ListarLivrosUsuario(usuario);
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	public class OuvinteBotaoVoltarCadastrarUsuario implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new JanelaLoguin(BancoDeDadosUsuarios);
		}

	}
	
	public JanelaCadastrarUsuario(ArrayList<Usuario> Dados) {
		super();
		BancoDeDadosUsuarios = Dados;
	}
	public void Incremento1() {
	titulo = new JLabel("Cadastrar Usuário");
	titulo.setFont(new Font("Arial", Font.BOLD, 28));
	titulo.setBounds(210, 30, 250, 50);
	add(titulo);
	}
	public void Incremento2() {
	String Nome = "Nome de usuário";
	nome = new JTextField();
	nome.setBounds(70, 230, 250, 25);
	nome.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
	nome.setText(Nome);
	OuvinteFocus OuvinteF = new OuvinteFocus(nome, Nome);
	nome.addFocusListener(OuvinteF);
	add(nome);
	}
	public void Incremento3() {
	String nome = "Senha";	
	senha = new JTextField();
	senha.setBounds(70, 320, 250, 25);
	senha.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
	senha.setText(nome);
	OuvinteFocus OuvinteF = new OuvinteFocus(senha, nome);
	senha.addFocusListener(OuvinteF);
	add(senha);
	}
	public void Incremento4() {
	String nome = "email";
	email = new JTextField();
	email.setBounds(390, 230, 250, 25);
	email.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
	email.setText(nome);
	OuvinteFocus OuvinteF = new OuvinteFocus(email, nome);
	OuvinteEmail Ouvinte = new OuvinteEmail(email);
	email.addKeyListener(Ouvinte);
	email.addFocusListener(OuvinteF);
	add(email);
	}
	public void Incremento5() {
	String nome = "Confirme a Senha";
	confirmeSenha = new JTextField();
	confirmeSenha.setBounds(390, 320, 250, 25);
	confirmeSenha.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
	confirmeSenha.setText(nome);
	OuvinteFocus OuvinteF = new OuvinteFocus(confirmeSenha, nome);
	confirmeSenha.addFocusListener(OuvinteF);
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
	
	}

}
