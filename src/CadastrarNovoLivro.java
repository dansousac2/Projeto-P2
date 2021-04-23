import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class CadastrarNovoLivro extends JFrame{
	
	private ArrayList<Livro> listaDeLivros;

	private JTextField tfTitulo;
	private JTextField tfIdioma;
	private JTextField tfEditora;
	private JTextField tfAno;
	private JTextArea taResumo;
	private JTextField tfQuantidade;
	private JComboBox<String> cbGenero;
	private JComboBox<String> cbTipo;
	private JTextArea taAutores;
	private JFormattedTextField ftfLancamento;
	private JTextField tfEdicao;
	private JTextField tfAssunto;
	
	private JButton btAdicionar;
	
	private OuvinteBotaoCadastrar ouvinte = new OuvinteBotaoCadastrar();
	
	public CadastrarNovoLivro(ArrayList<Livro> listarLivros) { 
	
		this.listaDeLivros = listarLivros;
		
		this.setTitle("Novo Livro");
		this.setSize(500,450);
		this.setLocationRelativeTo(null);
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
		addGenero();
		addAutores();
		addMes();
		addEdicao();
		addAssunto();
		addBotaoAdicionar();
		
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
	
		String[] tipos = {"Selecione o Tipo","Literatura","Técnico","Periódico","Desenvolvimento Pessoal"};
		
		cbTipo = new JComboBox<>(tipos);
		cbTipo.setBounds(260, 130, 214, 23);
		
		cbTipo.addActionListener(ouvinte);
		
		cbTipo.addActionListener(ouvinte);
		
		add(cbTipo);
	}
	
	private void addGenero() {
		
		cbGenero = new JComboBox<>();
		cbGenero.setBounds(260, 158, 214, 23);
		cbGenero.setEnabled(false);
		add(cbGenero);
	}
	
	private void addAutores() { 
	
		JLabel autores = new JLabel("Autor(es): ");
		autores.setBounds(260, 200, 75, 15);
		add(autores);
		
		taAutores = new JTextArea();
		taAutores.setBounds(320, 196, 154, 50);
		taAutores.setLineWrap(true);
		taAutores.setWrapStyleWord(true);
		taAutores.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		taAutores.setEnabled(false);
		add(taAutores);
	}
	
	private void addMes() {
	
		JLabel mes = new JLabel("Mês de lançamento: ");
		mes.setBounds(260, 260, 120, 15);
		add(mes);
		
		MaskFormatter mascara;
		try {
			mascara = new MaskFormatter("##");
			
			ftfLancamento = new JFormattedTextField(mascara);
			ftfLancamento.setBounds(380, 256, 94, 25);
			ftfLancamento.setHorizontalAlignment(JLabel.CENTER);
			ftfLancamento.setEnabled(false);
			add(ftfLancamento);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addEdicao() {
	
		JLabel edicao = new JLabel("Número da edição: ");
		edicao.setBounds(260, 291, 110, 15);
		add(edicao);
		
		tfEdicao = new JTextField();
		tfEdicao.setBounds(380, 287, 94, 25);
		tfEdicao.setEnabled(false);
		add(tfEdicao);
	}
	
	private void addAssunto() {
	
		JLabel assunto = new JLabel("Assunto: ");
		assunto.setBounds(260, 322, 60, 15);
		add(assunto);
		
		tfAssunto = new JTextField();
		tfAssunto.setBounds(320, 318, 154, 25);
		tfAssunto.setEnabled(false);
		add(tfAssunto);
	}
	
	private void addBotaoAdicionar() {
	
		btAdicionar = new JButton("Adicionar");
		btAdicionar.setBounds(304, 355, 120, 50);
		
		btAdicionar.addActionListener(ouvinte);
		
		add(btAdicionar);
	}
	
	public class OuvinteBotaoCadastrar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			String botaoClicado = e.getActionCommand();
			
			if(botaoClicado.equals(btAdicionar.getActionCommand())) {
				
				if(cbTipo.getSelectedIndex() != 0) {
					
					if(cbGenero.getSelectedIndex() != 0) {
						
						boolean cadastrar = true;
						
						ArrayList<JTextField> textField = new ArrayList<JTextField>();
						textField.add(ftfLancamento);
						textField.add(tfEdicao);
						textField.add(tfEditora);
						textField.add(tfQuantidade);
						textField.add(tfAno);
						textField.add(tfAssunto);
						textField.add(tfIdioma);
						textField.add(tfTitulo);
						
						for(JTextField tf : textField) {
							if(tf.isEnabled()) {
								if(tf.getText().isBlank()) {
									cadastrar = false;
									break;
								}
							}
						}
						
						if(taResumo.isEnabled() && taResumo.getText().isBlank() && taAutores.isEnabled() && taAutores.getText().isBlank()) {
							cadastrar = false;
						}
						
						if(!ftfLancamento.getText().equals("  ") && ftfLancamento.isEnabled()) {
							
							int mesInt = Integer.parseInt(ftfLancamento.getText());
							
							if(mesInt <1 || mesInt > 12) {
								cadastrar = false;
								JOptionPane.showMessageDialog(null, "Mês inválido", "Aviso", JOptionPane.WARNING_MESSAGE);
							}
						}
						
						System.out.println(cadastrar); // COLOCAR AQUI O QUE TIVER QUE OCORRER QUANDO TUDO ESTÁ PRONTO.
					}
						
					else {
						JOptionPane.showMessageDialog(null, "Selecione um Gênero");
					}
						
				}
					
				else {
					JOptionPane.showMessageDialog(null, "Selecione um Tipo");
				}
			}

			if(botaoClicado.equals(cbTipo.getActionCommand())) {
				
				if(cbTipo.getSelectedIndex() != 0) {
					
					String tipo = (String)cbTipo.getSelectedItem();
					
					String[] generoDoTipoSelecionado = Utilidade.generosDoTipo(tipo);
					
					cbGenero.removeAllItems();
					cbGenero.addItem("Selecione um gênero");
					
					for(String gen : generoDoTipoSelecionado) {
						cbGenero.addItem(gen);
					}
					
					cbGenero.setEnabled(true);
					
					taAutores.setEnabled(false);
					ftfLancamento.setEnabled(false);
					tfEdicao.setEnabled(false);
					tfAssunto.setEnabled(false);
					
					switch(tipo) {
						
					case "Literatura","Desenvolvimento Pessoal":
						taAutores.setEnabled(true);
						break;
			
					case "Periódico":
						ftfLancamento.setEnabled(true);
						tfEdicao.setEnabled(true);
						break;
					
					case "Técnico":
						tfAssunto.setEnabled(true);
						break;
						
					default:
						break;
					}
					
				}
				
				else {
					cbGenero.setEnabled(false);
					taAutores.setEnabled(false);
					ftfLancamento.setEnabled(false);
					tfEdicao.setEnabled(false);
					tfAssunto.setEnabled(false);					
				}
				
				repaint();
			}
			
		}
		
	}
}
