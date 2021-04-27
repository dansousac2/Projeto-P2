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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JanelaLoguin extends JanelaPadraoLivreiroUsuario{
	private CentralLivro BancoDeDados;
	public class OuvinteBotaoLogin implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(getEmail().getText().equals("") || getSenha().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Há algum campo vazio, tente novamente", "Campo vazio", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int cont = 0;
				for(int i = 0; i<BancoDeDados.getUsuariosCadastrados().size(); i++) {
					if(BancoDeDados.getUsuariosCadastrados().get(i).getEmail().equals(getEmail().getText()) 
							&& BancoDeDados.getUsuariosCadastrados().get(i).getSenha().equals(getSenha().getText()) 
							&& BancoDeDados.getUsuariosCadastrados().get(i).isEhLivreiro()==true) {
						cont++;
						dispose();
						new ListarLivrosLivreiro(BancoDeDados.getUsuariosCadastrados().get(i));
						break;
						
					}
				else if(BancoDeDados.getUsuariosCadastrados().get(i).getEmail().equals(getEmail().getText()) 
						&& BancoDeDados.getUsuariosCadastrados().get(i).getSenha().equals(getSenha().getText())) {
						cont++;
						dispose();
						new ListarLivrosUsuario(BancoDeDados.getUsuariosCadastrados().get(i));
						break;
					}
				}
				if(cont == 0) {
					JOptionPane.showMessageDialog(null, "Email ou senha invalido", "Tente novamente", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	public class OuvinteBotaoRecuperarSenha implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new JanelaAlteraçãoDeSenha(BancoDeDados);
		}
	}
	public class OuvinteBotaoNovoUsuario implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new JanelaCadastrarUsuario(BancoDeDados);
		}
	}
	public JanelaLoguin(CentralLivro Dados) {
		super();
		BancoDeDados = Dados;
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
		senha = new JPasswordField();
		senha.setBounds(235, 300, 250, 25);
		senha.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		add(senha);
	}
		public void Incremento4() {
		email = new JTextField();
		email.setBounds(235, 230, 250, 25);
		email.setBorder(BorderFactory.createLineBorder(Color.CYAN.darker()));
		OuvinteEmail Ouvinte = new OuvinteEmail(email);
		email.addKeyListener(Ouvinte);
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
		titulo = new JLabel("Email");
		titulo.setFont(new Font("ARBARKLEY", Font.TYPE1_FONT, 13));
		titulo.setBounds(210, 205, 80, 25);
		add(titulo);
		titulo = new JLabel("Senha");
		titulo.setFont(new Font("ARBARKLEY", Font.TYPE1_FONT, 13));
		titulo.setBounds(210, 275, 80, 25);
		add(titulo);
	}
	void Incremento11() {
	}
	void Incremento12() {		
	}
	void Incremento13() {		
	}
}
