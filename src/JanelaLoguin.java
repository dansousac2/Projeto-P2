import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JanelaLoguin extends JanelaPadraoLivreiroUsuario{
	
	ArrayList<Usuario> BancoDeDadosUsuarios;
	
	public class OuvinteBotaoLogin implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(getEmail().getText().equals("email") || getSenha().getText().equals("Senha")) {
				JOptionPane.showMessageDialog(null, "Há algum campo vazio, tente novamente", "Campo vazio", JOptionPane.ERROR_MESSAGE);
			}
			else {
				for(int i = 0; i<BancoDeDadosUsuarios.size(); i++) {
					if(BancoDeDadosUsuarios.get(i).getEmail().equals(getEmail().getText()) 
						&& BancoDeDadosUsuarios.get(i).getSenha().equals(getSenha().getText())) {
						dispose();
						new ListarLivrosUsuario(BancoDeDadosUsuarios.get(i));
						break;
					}
					else {
						JOptionPane.showMessageDialog(null, "Email ou Senha Incorrento, tente novamente");
						break;
					}
				}
			}
		}
	}
	public class OuvinteBotaoRecuperarSenha implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new JanelaAlteraçãoDeSenha(BancoDeDadosUsuarios);
		}
	}
	public class OuvinteBotaoNovoUsuario implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new JanelaCadastrarUsuario(BancoDeDadosUsuarios);
		}
	}
	public JanelaLoguin(ArrayList<Usuario> Dados) {
		super();
		BancoDeDadosUsuarios = Dados;
	}
	public void Incremento1() {
		titulo = new JLabel("Login");
		titulo.setFont(new Font("Arial", Font.BOLD, 28));
		titulo.setBounds(297, 150, 150, 50);
		add(titulo);
	}
	public void Incremento2() {
		img = new ImageIcon("100p/user.png");
		titulo = new JLabel(img);
		titulo.setBounds(285, 30, 100, 100);
		add(titulo);
	}
	public void Incremento3() {
		String nome = "Senha";	
		senha = new JTextField();
		senha.setBounds(235, 300, 250, 25);
		senha.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		senha.setText(nome);
		OuvinteFocus OuvinteF = new OuvinteFocus(senha, nome);
		senha.addFocusListener(OuvinteF);
		add(senha);
	}
		public void Incremento4() {
		String nome = "email";
		email = new JTextField();
		email.setBounds(235, 230, 250, 25);
		email.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		email.setText(nome);
		OuvinteFocus OuvinteF = new OuvinteFocus(email, nome);
		OuvinteEmail Ouvinte = new OuvinteEmail(email);
		email.addKeyListener(Ouvinte);
		email.addFocusListener(OuvinteF);
		add(email);
	}
		public void Incremento5() {
		img = new ImageIcon("24p/key-24p.png");
		titulo = new JLabel(img);
		titulo.setBounds(210, 300, 25, 25);
		titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(titulo);
	}
	public void Incremento6() {
		img = new ImageIcon("24p/email-24.png");
		titulo = new JLabel(img);
		titulo.setBounds(210, 230, 25, 25);
		titulo.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(titulo);
	}
	void Incremento7() {
		botao = new JButton("Recuperar Senha");
		botao.setBounds(45, 480, 150, 25);
		OuvinteBotaoRecuperarSenha Ouvinte = new OuvinteBotaoRecuperarSenha();
		botao.addActionListener(Ouvinte);
		add(botao);
	}
	void Incremento8() {
		botao = new JButton("Novo Usuário");
		botao.setBounds(500, 480, 130, 25);
		OuvinteBotaoNovoUsuario Ouvinte = new OuvinteBotaoNovoUsuario();
		botao.addActionListener(Ouvinte);
		add(botao);
	}
	void Incremento9() {
		botao = new JButton("Fazer Login");
		botao.setBounds(280, 370, 130, 25);
		OuvinteBotaoLogin Ouvinte = new OuvinteBotaoLogin();
		botao.addActionListener(Ouvinte);
		add(botao);	
	}
	void Incremento10() {
	}
	void Incremento11() {
	}
	void Incremento12() {		
	}
	void Incremento13() {		
	}
}
