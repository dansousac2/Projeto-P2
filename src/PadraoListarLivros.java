import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public abstract class PadraoListarLivros extends JFrame {
	
	protected Usuario usuarioLogado;
	
	private JTable jtLivrosDisponiveis;
	private DefaultTableModel modelo;
	
	protected ArrayList<Livro> listarLivros;
	private JComboBox<String> cbTipo;
	private JComboBox<String> cbOrdem;
	private JTextField barraPesquisar;
	
	private ImageIcon iconeCentral;
	private String nomeIconeCentral;
	
	private ImageIcon iconeUsuario = Imagens.ICONE_USUARIO;
	
	private boolean exibirExtras;
	
	private OuvinteGeral ouvinteGeral = new OuvinteGeral();
	
	public PadraoListarLivros(Usuario user,boolean exibirExtras,ImageIcon iconeCentral,String nomeIconeCentral) {
		
		this.usuarioLogado = user;
		this.exibirExtras = exibirExtras;
		this.iconeCentral = iconeCentral;
		this.nomeIconeCentral = nomeIconeCentral;
		
		this.setTitle("Livraria Stile");
		this.setSize(700,580);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		addTabela();
		
		addBotaoVisualizar();
		addComboTipo();
		addComboOrdem();
		addTextoPesquisa();
		
		addIconeCentral();
		addNomeIconeCentral();
		
		this.setVisible(true);
	}
	
	private class OuvinteGeral implements ActionListener, KeyListener{

		public void actionPerformed(ActionEvent e) {
			
			String botaoPrecionado = e.getActionCommand();

			switch(botaoPrecionado) {
			
			case "visualizar":
				
				int linhaSelecionada = jtLivrosDisponiveis.getSelectedRow();
				
				if(linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(rootPane, "Selecione algum livro primeiro!");
				}
				else {
					long idSelecionado = (long)jtLivrosDisponiveis.getValueAt(linhaSelecionada, 3);
					
					for(Livro elemento : listarLivros) {
						if(elemento.getId() == idSelecionado) {
							
							for(Livro liv : listarLivros) {
								if(idSelecionado == liv.getId()) {
									// CRIAR UMA JANELA DE DETALHES PASSADO ESTE LIVRO COMO PARÂMETRO
								}
							}
							
							break;
						}
					}
				}
				break;
			
			case "comboTipo":
				
				int tipoCombo = cbTipo.getSelectedIndex();
	
				ArrayList<Livro> selecionadosPorTipo = new ArrayList<Livro>();
				
				if(tipoCombo > 0) {
										
					String[] tipos = {"literatura","tecnico","periodico","desenvolvimento pessoal"};	
					
					for(Livro liv : listarLivros){
						
						if(liv.getTipo().equals(tipos[tipoCombo - 1])){
							selecionadosPorTipo.add(liv);
						}
					}
				}
				
				else {
					
					for(Livro liv : listarLivros) {
						selecionadosPorTipo.add(liv);
					}
				}
					
				modelo.setRowCount(0);
									
				for(Livro liv : selecionadosPorTipo) {
					
					adicionarLinhasAtualizadas(liv);
				}
				repaint();
				break;
			
			case "comboOrdem":
				
				int ordemCombo = cbOrdem.getSelectedIndex();
				
				if(ordemCombo == 1) { // ALFABETO - TÍTULOS
					
					Collections.sort(listarLivros, new ComparadorLivros());
				}
				
				else if(ordemCombo == 2) {  // ORDENAR - MAIOR NOTA MÉDIA => MENOR NOTA MÉDIA
					
					Collections.sort(listarLivros, new ComparadorLivros() {
						public int compare(Livro o1,Livro o2) {
							if(o1.getNotaMedia() < o2.getNotaMedia()) {
								return 1;
							}
							else if(o1.getNotaMedia() > o2.getNotaMedia()) {
								return -1;
							}
							else {
								return 0;
							}
						}
					});
				}
				
				modelo.setRowCount(0);
				
				for(Livro liv : listarLivros) {
					
					adicionarLinhasAtualizadas(liv);
				}
				
				repaint();
				break;
				
			default:
				break;
			}
		}

		public void keyTyped(KeyEvent e) {			

		}

		public void keyPressed(KeyEvent e) {

		}

		public void keyReleased(KeyEvent e) {

			ArrayList<Livro> listaPorDigito = new ArrayList<Livro>();
			
			char digito = e.getKeyChar();
			
			if(barraPesquisar.getText().isEmpty()) {
				listaPorDigito.addAll(listarLivros);
			}
			else {
				
				String digitado = barraPesquisar.getText();
				for(Livro liv : listarLivros) {
					if(liv.getTitulo().toLowerCase().contains(digitado.toLowerCase())) {
						listaPorDigito.add(liv);
					}
				}
			}
			
			
			modelo.setRowCount(0);
			
			for(Livro liv : listaPorDigito) {
				adicionarLinhasAtualizadas(liv);
			}
			
			repaint();
		}
		
	}
	
	private void addTabela() {
			
			this.listarLivros = recuperarLivrosCabiveis();
		
			String[] tituloColunas = {"Tipo","Título","Resumo","ID","Nota Média","Quantidade"};
		
			int repetir = 5;
		
			if(exibirExtras) {
				repetir = 6;
			}
			
			modelo = new DefaultTableModel();
			
			for(int i = 0; i < repetir; i++) {
				
				modelo.addColumn(tituloColunas[i]);
			}
			
			for(Livro elemento : listarLivros) {
				
				String[] atributosLivros =  principaisAtributosDosLivros(elemento);
				
				String[] linha = new String[repetir];
				
				for(int i = 0; i < repetir; i++) {
					
					linha[i] = atributosLivros[i]; 
				}
				
				modelo.addRow(linha);				
			}
			
			this.jtLivrosDisponiveis = new JTable(modelo) {
				public boolean isCellEditable(int data,int columns) {
					return false;
				}
			};
			
			jtLivrosDisponiveis.getColumnModel().getColumn(1).setPreferredWidth(150);
			jtLivrosDisponiveis.getColumnModel().getColumn(2).setPreferredWidth(200);
			
			JScrollPane barraTabela = new JScrollPane(jtLivrosDisponiveis);
			
			barraTabela.setBounds(22, 125, 644, 339);
			
			add(barraTabela);
		
//		} catch(Exception e) {
//			e.getStackTrace();
//			JOptionPane.showMessageDialog(null, "Não foi possível criar a lista de livros", "Criar lista de livros",
//					JOptionPane.ERROR_MESSAGE);
//		}
	}
	
	private void addBotaoVisualizar() {
		
		JButton btVisualizar = new JButton("Visualizar");
		btVisualizar.setBounds(88, 494, 100, 25);
		
		btVisualizar.setActionCommand("visualizar");
		btVisualizar.addActionListener(ouvinteGeral);
		
		add(btVisualizar);
	}
	
	private void addComboTipo() {
		
		String[] tipoLivro = {"Todos os Tipos","Literatura","Técnico","Periódico","Desenvolvimento Pessoal"};
		cbTipo = new JComboBox<String>(tipoLivro);
		cbTipo.setBounds(22, 100, 150, 20);
		
		cbTipo.setActionCommand("comboTipo");
		cbTipo.addActionListener(ouvinteGeral);
		
		add(cbTipo);
	}
	
	private void addComboOrdem() {
		
		String[] ordenarLivro = {"Ordenar","Alfabeto","Nota"};
		cbOrdem = new JComboBox<String>(ordenarLivro);
		cbOrdem.setBounds(585, 100, 80, 20);
		
		cbOrdem.setActionCommand("comboOrdem");
		cbOrdem.addActionListener(ouvinteGeral);
		
		add(cbOrdem);
	}
	
	private void addTextoPesquisa() {
		
		JLabel lupaTextoPesquisa = new JLabel(Imagens.ICONE_PESQUISAR);
		lupaTextoPesquisa.setBounds(490, 20, 30, 30);
		
		add(lupaTextoPesquisa);
		
		barraPesquisar = new JTextField();
		barraPesquisar.setBounds(520, 20, 140, 30);
		
		barraPesquisar.addKeyListener(ouvinteGeral);
		
		add(barraPesquisar);
	}
	
	protected void addIconeCentral() {
		
		JLabel iconeCentral = new JLabel(this.iconeCentral);
		iconeCentral.setBounds(300, 5, 75, 75);
		
		add(iconeCentral);
	}
	
	protected void addNomeIconeCentral() {
		
		JLabel nomeIconeCentral = new JLabel(this.nomeIconeCentral);
		nomeIconeCentral.setFont(new Font("AR BERKLEY", Font.TYPE1_FONT, 24));
		nomeIconeCentral.setForeground(new Color(158, 14, 245));
		nomeIconeCentral.setHorizontalAlignment(JLabel.CENTER);
		nomeIconeCentral.setBounds(265, 90, 155, 20);
		
		add(nomeIconeCentral);
	}
	
	protected void addIconeUsuario() {

		JLabel iconeUsuario = new JLabel(this.iconeUsuario);
		iconeUsuario.setBounds(20, 10, 60, 60);
		
		add(iconeUsuario);
	}
	
	protected void addNomeIconeUsuario() {
	
		JLabel nomeIcone = new JLabel(usuarioLogado.getNome());
		nomeIcone.setFont(new Font("Times New Roman", Font.BOLD, 14));
		nomeIcone.setForeground(new Color(163, 73, 164));
		nomeIcone.setBounds(20, 73, 120, 15);
		
		add(nomeIcone);
	}
	
	private String[] principaisAtributosDosLivros(Livro liv) {
		
		String[] atributos = new String[6];
		
		Object[] objetosAtributos = {liv.getTipo(),liv.getTitulo(),liv.getResumo(),liv.getId(),
				liv.getNotaMedia(),liv.getQuantidade() };
		
		for(int i = 0; i < 6; i++) {
			atributos[i] = objetosAtributos[i].toString();
		}
		return atributos;
	}
	
	private void adicionarLinhasAtualizadas(Livro liv) {
		String[] atributos = principaisAtributosDosLivros(liv);
		String[] linha = new String[modelo.getColumnCount()];
		
		for(int i = 0; i < modelo.getColumnCount(); i++) {
			linha[i] = atributos[i];
		}
		modelo.addRow(linha);
	}
	
	public class ComparadorLivros implements Comparator<Livro>{

		public int compare(Livro o1, Livro o2) {
			return o1.getTitulo().compareToIgnoreCase(o2.getTitulo());
		}
	}
	
	private ArrayList<Livro> recuperarLivrosCabiveis() {
		
		PersistenciaLivros perLivros = new PersistenciaLivros();

		CentralLivro centralInformacoes;
		
		try {
			if(this.getClass().getSimpleName().equals("ColecaoUsuario")) {
			
				centralInformacoes = perLivros.recuperarCentral("Livros_Usuarios.xml");
				ArrayList<Usuario> buscarUsuario = centralInformacoes.getUsuariosCadastrados();
				for(Usuario user : buscarUsuario) {
					if(user == usuarioLogado) {
						return user.getColecaoDeLivros();
					}
				}
			}
			
			else {
				centralInformacoes = perLivros.recuperarCentral("Livros_Cadastrados.xml");
				return centralInformacoes.getLivrosDisponiveis();
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível listar os livros", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		return new ArrayList<Livro>();
	}
	
}
