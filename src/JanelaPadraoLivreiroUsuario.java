import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
public abstract class JanelaPadraoLivreiroUsuario extends JFrame{
	protected JLabel titulo;
	protected ImageIcon img;
	protected JButton botao;
	protected JTextField nome;
	protected JTextField email;
	protected JTextField senha;
	protected JTextField confirmeSenha;
	protected XStream xstream;
	protected File arquivo;
	protected String XML;
	protected Usuario usuario;
	
	
	public JanelaPadraoLivreiroUsuario() {
		setSize(700, 580);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
		Incremento1();
		Incremento2();
		Incremento3();
		Incremento4();
		Incremento5();
		Incremento6();
		Incremento7();
		Incremento8();
		Incremento9();
		Incremento10();
		Incremento11();
		Incremento12();
		Incremento13();
		
	}
		abstract void Incremento1();
		abstract void Incremento2();
		abstract void Incremento3();
		abstract void Incremento4();
		abstract void Incremento5();
		abstract void Incremento6();
		abstract void Incremento7();
		abstract void Incremento8();
		abstract void Incremento9();
		abstract void Incremento10();
		abstract void Incremento11();
		abstract void Incremento12();
		abstract void Incremento13();
		public JLabel getTitulo() {
			return titulo;
		}
		public void setTitulo(JLabel titulo) {
			this.titulo = titulo;
		}
		public ImageIcon getImg() {
			return img;
		}
		public void setImg(ImageIcon img) {
			this.img = img;
		}
		public JButton getBotao() {
			return botao;
		}
		public void setBotao(JButton botao) {
			this.botao = botao;
		}
		public JTextField getNome() {
			return nome;
		}
		public void setNome(JTextField nome) {
			this.nome = nome;
		}
		public JTextField getEmail() {
			return email;
		}
		public void setEmail(JTextField email) {
			this.email = email;
		}
		public JTextField getSenha() {
			return senha;
		}
		public void setSenha(JTextField senha) {
			this.senha = senha;
		}
		public JTextField getConfirmeSenha() {
			return confirmeSenha;
		}
		public void setConfirmeSenha(JTextField confirmeSenha) {
			this.confirmeSenha = confirmeSenha;
		}	
}