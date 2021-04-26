import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class CadastrarNovoLivro extends JDialog{
	
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
	private JTextField tfPreço;
	
	private JButton btAdicionar;
	
	private OuvinteBotaoCadastrar ouvinte = new OuvinteBotaoCadastrar();
	
	private ListarLivrosLivreiro janela;
	public class OuvintePreço implements KeyListener{
		String caractere = "0123456789";
		public void keyTyped(KeyEvent e) {
			char C = e.getKeyChar();
			if(!caractere.contains(C+"") && C != '.'){
				e.consume();
			}
		}
		public void keyPressed(KeyEvent e) {		
		}
		public void keyReleased(KeyEvent e) {		
		}
	}
	public CadastrarNovoLivro(ListarLivrosLivreiro janela) { 
	
		this.setModal(true);
		
		this.janela = janela;
		this.listaDeLivros = janela.listarLivros;
		
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
		addPreço();
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
		resumo.setBounds(10, 260, 60, 15);
		add(resumo);
		
		taResumo = new JTextArea();
		taResumo.setBounds(	70, 260, 175, 65);
		taResumo.setLineWrap(true);
		taResumo.setWrapStyleWord(true);
		taResumo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		add(taResumo);
	}
	private void addPreço() {
		
		JLabel preço = new JLabel("Preço: ");
		preço.setBounds(10, 340, 50, 15);
		add(preço);
		
		tfPreço = new JTextField();
		tfPreço.setBounds(55, 337, 190, 25);
		tfPreço.setHorizontalAlignment(JTextField.CENTER);
		tfPreço.addKeyListener(new OuvintePreço());
		add(tfPreço);
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
						
						if((taResumo.isEnabled() && taResumo.getText().isBlank()) || (taAutores.isEnabled() && taAutores.getText().isBlank())) {
							cadastrar = false;
						}
						
						try {
						
						if(Utilidade.ehNumero(tfAno.getText()) || Utilidade.ehNumero(tfQuantidade.getText())){
							
							LocalDate dataHoje = LocalDate.now();
							
							int ano = dataHoje.getYear();
							
							if(Integer.parseInt(tfAno.getText()) > ano) {
								cadastrar = false;
							}
							
							if(!ftfLancamento.getText().equals("  ") && ftfLancamento.isEnabled()) {

								int mesLanc = Integer.parseInt(ftfLancamento.getText());
								
								int anoSelecionado = Integer.parseInt(tfAno.getText());
								
								try {
									
									LocalDate dataInformada = LocalDate.of(anoSelecionado, mesLanc, 1);
									if(dataInformada.isAfter(dataHoje)) {
										cadastrar = false;
									}
									
								}catch (Exception erro) {
									cadastrar = false;
								}
							}
							
						} else {
							cadastrar = false;
						}
						
						} catch (Exception ex) {
							cadastrar = false;
						}
						
						if(cadastrar) {
							
							int resposta = JOptionPane.YES_OPTION;
							
							for(Livro liv : listaDeLivros) {
	
								if(tfTitulo.getText().toLowerCase().equals(liv.getTitulo().toLowerCase())) {
									resposta = JOptionPane.showConfirmDialog(null, "Esta obra ja consta no banco de dados!\n"
											+ "Deseja continuar?", "Obra Duplicada?!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
								}
								break;
							}
							
							if(resposta == JOptionPane.YES_OPTION) {
								
								Livro novoLivro = new Livro((String)cbTipo.getSelectedItem(),(String)cbGenero.getSelectedItem(),tfTitulo.getText(),
										tfIdioma.getText(),tfEditora.getText(),taResumo.getText(),Integer.parseInt(tfAno.getText()),
										Integer.parseInt(tfQuantidade.getText()));
								float N = Float.parseFloat(tfPreço.getText());
								novoLivro.setPreco(N);
								if(taAutores.isEnabled()) {
									novoLivro.setAutores(taAutores.getText());
								}
								
								if(ftfLancamento.isEnabled()) {
									novoLivro.setMesLancamento(ftfLancamento.getText());
								}
								
								if(tfEdicao.isEnabled()) {
									novoLivro.setNumeroEdicao(Integer.parseInt(tfEdicao.getText()));
								}
								
								if(tfAssunto.isEnabled()) {
									novoLivro.setAssunto(tfAssunto.getText());
								}
								
								listaDeLivros.add(novoLivro);
								
								PersistenciaLivros persistencia = new PersistenciaLivros();
								
								try {
									
									CentralLivro central = persistencia.recuperarCentral("Dados_Livraria.xml");
									
									central.setLivrosDisponiveis(listaDeLivros);
									
									persistencia.salvarCentral(central, "Dados_Livraria.xml");
									
									JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "Cadastrar Novo Livro", JOptionPane.INFORMATION_MESSAGE);
									
									janela.modelo.setRowCount(0);
									
									for(Livro liv : janela.listarLivros) {
										Utilidade.adicionarLinhasAtualizadas(liv, janela.modelo);
									}
									
									janela.repaint();
									dispose();
									
								}catch (Exception erro) {
									JOptionPane.showMessageDialog(null, "Erro ao recuperar/salvar lista", "Erro", JOptionPane.ERROR_MESSAGE);
								}
							}
							
						} else {
							JOptionPane.showMessageDialog(null, "Verifique os campos habilitados", "Aviso", JOptionPane.ERROR_MESSAGE);
							repaint();
						}
						
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
