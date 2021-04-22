import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CadastrarNovoLivro extends JFrame{

	JTextField tfTitulo;
	JTextField tfIdioma;
	JTextField tfEditora;
	JTextField tfAno;
	JTextArea taResumo;
	JTextField tfQuantidade;
	JComboBox cbGenero;
	JComboBox cbTipo;
	JTextArea taAutores;
	JTextField tfLancamento;
	JTextField tfEdicao;
	JTextField tfAssunto;
	
	public CadastrarNovoLivro() {
	
		this.setTitle("Novo Livro");
		this.setSize(500,450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		
		addIconeLivraria();
		addTitulo();
		addIdioma();
		addEditora();
		addAno();
		addResumo();
		addQuantidade();
		addTipo();
		
		addAutores();
		addMes();
		
		this.setVisible(true);
	}

	private void addIconeLivraria() {
		
		JLabel icone = new JLabel(Imagens.ICONE_NOVO_LIVRO,JLabel.CENTER);
		icone.setBounds(0, 0, 500, 100);
		add(icone);
		
		JLabel textoCadastro = new JLabel("Cadastar novo livro",JLabel.CENTER);
		textoCadastro.setFont(new Font("AR BERKLEY", Font.TYPE1_FONT, 24));
		textoCadastro.setForeground(new Color(158, 14, 245));
		textoCadastro.setBounds(0, 90, 500, 24);
		add(textoCadastro);
	}

	private void addTitulo() {
	
		JLabel jlTitulo = new JLabel("Título: ");
		jlTitulo.setBounds(10, 134, 45, 15);
		add(jlTitulo);
		
		tfTitulo = new JTextField();
		tfTitulo.setBounds(60, 130, 185, 25);
		add(tfTitulo);
	}
	
	private void addIdioma() {
	
		JLabel jlIdioma = new JLabel("Idioma: ");
		jlIdioma.setBounds(10, 164, 50, 15);
		add(jlIdioma);
		
		tfIdioma = new JTextField();
		tfIdioma.setBounds(60, 160, 185, 25);
		add(tfIdioma);
	}
	
	private void addEditora() {
	
		JLabel editora = new JLabel("Editora: ");
		editora.setBounds(10, 194, 50, 15);
		add(editora);
		
		tfEditora = new JTextField();
		tfEditora.setBounds(60, 190, 185, 25);
		add(tfEditora);
	}
	
	private void addAno() {

		JLabel ano = new JLabel("Ano de Publicação: ");
		ano.setBounds(10, 224, 115, 15);
		add(ano);
		
		tfAno = new JTextField();
		tfAno.setBounds(125, 220, 120, 25);
		tfAno.setHorizontalAlignment(JLabel.CENTER);
		add(tfAno);
	}
	
	private void addResumo() {
		
		JLabel resumo = new JLabel("Resumo: ");
		resumo.setBounds(10, 300, 60, 15);
		add(resumo);
		
		taResumo = new JTextArea();
		taResumo.setBounds(	70, 260, 175, 100);
		taResumo.setLineWrap(true);
		taResumo.setWrapStyleWord(true);
		taResumo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		add(taResumo);
	}
	
	private void addQuantidade() {
	
		JLabel quantidade = new JLabel("Quantidade: ");
		quantidade.setBounds(10, 375, 75, 15);
		add(quantidade);
		
		tfQuantidade = new JTextField();
		tfQuantidade.setBounds(85, 371, 160, 25);
		tfQuantidade.setHorizontalAlignment(JTextField.CENTER);
		add(tfQuantidade);
	}
	
	private void addTipo() {	// SOBRA 26px À DIREITA !!!!
	
		String[] tipos = {"Literatura","Técnico","Periódico","Desenvolvimento Pessoal"};
		
		cbTipo = new JComboBox<>(tipos);
		cbTipo.setBounds(260, 130, 214, 23);
		add(cbTipo);
	}
	
	private void addAutores() { //ANTES DEVE VIR GÊNERO E TIPO
	
		JLabel autores = new JLabel("Autor(es): ");
		autores.setBounds(260, 304, 75, 15);
		add(autores);
		
		taAutores = new JTextArea();
		taAutores.setBounds(320, 300, 154, 50);
		taAutores.setLineWrap(true);
		taAutores.setWrapStyleWord(true);
		taAutores.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		add(taAutores);
	}
	
	private void addMes() {
	
		JLabel mes = new JLabel("Mês de lançamento: ");
		mes.setBounds(260, 364, 120, 15);
		add(mes);
		
		tfLancamento = new JTextField();
		tfLancamento.setBounds(380, 360, 94, 25);
		add(tfLancamento);
	}
}
