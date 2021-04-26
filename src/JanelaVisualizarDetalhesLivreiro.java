import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
public class JanelaVisualizarDetalhesLivreiro extends JanelaPadraoVisualizarDetalhes{
	private Usuario usuarioLocal;
	private Livro livroDetalhado;
	private JTextArea comentarios;
	private JTextField tfPreço;
	private JTextField tfQuantidade;
	PersistenciaLivros persistencia = new PersistenciaLivros();
	CentralLivro dados = new CentralLivro();
	public class OuvinteBotaoSalvar implements ActionListener{
		JTable tabela;
		public OuvinteBotaoSalvar(JTable T) {
			tabela = T;
		}
		public void actionPerformed(ActionEvent e) {
			try {
				livroDetalhado.setResumo(resumo.getText());
				float P = Float.parseFloat(tfPreço.getText());
				livroDetalhado.setPreco(P);
				int N = Integer.parseInt(tfQuantidade.getText());
				livroDetalhado.setQuantidade(N);
				tabela.getCellEditor().stopCellEditing();
				livroDetalhado.setTitulo(""+tabela.getValueAt(0, 1));
				livroDetalhado.setGenero(""+tabela.getValueAt(1, 1));
				livroDetalhado.setIdioma(""+tabela.getValueAt(2, 1));
				livroDetalhado.setAnoPublicacao(Integer.parseInt(""+tabela.getValueAt(3, 1)));
				livroDetalhado.setEditora(""+tabela.getValueAt(4, 1));
				livroDetalhado.setAutores(""+tabela.getValueAt(5, 1));
				livroDetalhado.setMesLancamento(""+tabela.getValueAt(6, 1));
				livroDetalhado.setNumeroEdicao(Integer.parseInt(""+tabela.getValueAt(7, 1)));
				livroDetalhado.setAssunto(""+tabela.getValueAt(8, 1));
				livroDetalhado.setQuantidade(Integer.parseInt(""+tabela.getValueAt(9, 1)));
				livroDetalhado.setNotaMedia(Integer.parseInt(""+tabela.getValueAt(10, 1)));
				dados = persistencia.recuperarCentral("Dados_Livraria.xml");
				for(int i = 0; i<dados.getLivrosDisponiveis().size();i++) {
					if(dados.getLivrosDisponiveis().get(i).getId() == livroDetalhado.getId()) {
						dados.getLivrosDisponiveis().remove(i);
						dados.getLivrosDisponiveis().add(livroDetalhado);
						JOptionPane.showMessageDialog(null, "As alterações foram salvas");
						persistencia.salvarCentral(dados, "Dados_Livraria.xml");
						break;
					}
				}
			} catch (Exception e1) {
				try {
					dados = persistencia.recuperarCentral("Dados_Livraria.xml");
					persistencia.salvarCentral(dados, "Dados_Livraria.xml");
					JOptionPane.showMessageDialog(null, "As alterações foram salvas");
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}			
	public class OuvinteBotaoAdicionarComentario implements ActionListener{
		String Com;
		public OuvinteBotaoAdicionarComentario(String S) {
			Com = S;
		}
		public void actionPerformed(ActionEvent e) {
			String T = JOptionPane.showInputDialog("Adicione um Comentário");
			if(!T.isBlank()) {
				try {
					int numero = livroDetalhado.getTodosOsComentarios().size()+1;
					String[] prepararComentario = {""+numero,usuarioLocal.getEmail(),T};
					dados = persistencia.recuperarCentral("Dados_Livraria.xml");
					livroDetalhado.getTodosOsComentarios().add(prepararComentario);
					Com = "COMENTÁRIOS\n";
					int N = 1;
					for(String[] coment: livroDetalhado.getTodosOsComentarios()) {
						coment[0] = ""+N++;
						Com += "\ncoment "+coment[0]+" / "+coment[1]+"\n"+coment[2];
					}
					comentarios.setText(Com);
					persistencia.salvarCentral(dados, "Dados_Livraria.xml");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Nenhum comentário será adicionado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
			}
	}
}
	public class OuvinteTable implements MouseListener{
		public void mouseClicked(java.awt.event.MouseEvent e) {
			if(e.getClickCount() == 2) {
				int E = JOptionPane.showConfirmDialog(null, "Deseja excluir a opção selecionada?", "Remover Linha", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION);
				if(E == JOptionPane.YES_OPTION) {
					modelo.removeRow(tabela.getSelectedRow());
				}
			}
		}
		public void mousePressed(java.awt.event.MouseEvent e) {	
		}
		public void mouseReleased(java.awt.event.MouseEvent e) {
		}
		public void mouseEntered(java.awt.event.MouseEvent e) {
		}
		public void mouseExited(java.awt.event.MouseEvent e) {
		}
	}
	public class OuvinteBotaoExcluirComentario implements ActionListener{
		String Com;
		public OuvinteBotaoExcluirComentario(String S) {
			Com= S;
		}
		public void actionPerformed(ActionEvent e) {
			try{
				int numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o numero do comentário a ser excluído"));
				dados = persistencia.recuperarCentral("Dados_Livraria.xml");
				livroDetalhado.getTodosOsComentarios().remove(numero-1);
				Com = "COMENTÁRIOS\n";
				int N = 1;
				for(String[] coment: livroDetalhado.getTodosOsComentarios()) {
					coment[0] = ""+N++;
					Com += "\ncoment "+coment[0]+" / "+coment[1]+"\n"+coment[2];
				}
				comentarios.setText(Com);
				persistencia.salvarCentral(dados, "Dados_Livraria.xml");
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Só é permitido números no campo de texto", "Compo Invalido", JOptionPane.ERROR_MESSAGE);
			}
	}
}
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
	public class OuvinteQuantidade implements KeyListener{
		String caractere = "0123456789";
		public void keyTyped(KeyEvent e) {
			char C = e.getKeyChar();
			if(!caractere.contains(C+"")){
				e.consume();
			}
		}
		public void keyPressed(KeyEvent e) {		
		}
		public void keyReleased(KeyEvent e) {		
		}
	}
	public class OuvinteBotaoAvisar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try{
				dados = persistencia.recuperarCentral("Dados_Livraria.xml");
				Avisar avisar;
				for(int i = 0;i<livroDetalhado.getInteressados().size();i++) {
					avisar = new Avisar(livroDetalhado.getInteressados().get(i), livroDetalhado.getTitulo());
					livroDetalhado.getInteressados().remove(i);
				}
				persistencia.salvarCentral(dados, "Dados_Livraria.xml");
				JOptionPane.showMessageDialog(null, "Email enviado!");
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Pode haver algum email invalido na lista!", "Erro", JOptionPane.ERROR_MESSAGE);
			}
	}
}
	public JanelaVisualizarDetalhesLivreiro(Usuario U,Livro L) {
		super();
		usuarioLocal = U;
		livroDetalhado = L;
		Barra();
		Titulo();
		DetalhesDoLivro();
		setVisible(true);
	}
	public void Titulo() {
		titulo = new JLabel("Visualizar Detalhes");
		titulo.setBounds(230, 20, 250, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 22));
		add(titulo);
	}
	public void DetalhesDoLivro() {
		String[] atributos = new String[12];
		atributos[0] = livroDetalhado.getTitulo();
		atributos[1] = livroDetalhado.getGenero();
		atributos[2] = livroDetalhado.getIdioma();
		atributos[3] = ""+livroDetalhado.getAnoPublicacao();
		atributos[4] = livroDetalhado.getEditora();
		atributos[5] = livroDetalhado.getAutores();
		atributos[6] = livroDetalhado.getMesLancamento();
		atributos[7] = ""+livroDetalhado.getNumeroEdicao();
		atributos[8] = livroDetalhado.getAssunto();
		atributos[9] = ""+livroDetalhado.getQuantidade();
		atributos[10] = ""+livroDetalhado.getNotaMedia();
		atributos[11] = livroDetalhado.getResumo();
		modelo = new DefaultTableModel();
		modelo.addColumn("Aspectos");
		modelo.addColumn("Detalhes");
		modelo.addRow(new Object[] {"Titulo",atributos[0]});
		modelo.addRow(new Object[] {"Genero",atributos[1]});
		modelo.addRow(new Object[] {"Idioma",atributos[2]});
		modelo.addRow(new Object[] {"Ano de Publicação",atributos[3]});
		modelo.addRow(new Object[] {"Editora",atributos[4]});
		modelo.addRow(new Object[] {"Autores",atributos[5]});
		modelo.addRow(new Object[] {"Mes de Lançamento",atributos[6]});
		modelo.addRow(new Object[] {"Numero de Edição",atributos[7]});
		modelo.addRow(new Object[] {"Assunto",atributos[8]});
		modelo.addRow(new Object[] {"Quantidade Disponivel",atributos[9]});
		modelo.addRow(new Object[] {"Nota Média",atributos[10]});
		tabela = new JTable(modelo);
		rolo = new JScrollPane(tabela);
		rolo.setBounds(30, 130, 400, 200);
		add(rolo);
		botao = new JButton("Salvar");
		botao.setBounds(190, 332, 80, 25);
		botao.addActionListener(new OuvinteBotaoSalvar(tabela));
		add(botao);
		resumo = new JTextArea();
		resumo.setLineWrap(true);
		resumo.setWrapStyleWord(true);
		resumo.setText(atributos[11]);
		rolo = new JScrollPane(resumo);
		rolo.setBounds(450, 130, 200, 200);
		add(rolo);
		String comentarioConcatenado = "COMENTÁRIOS: \n";
		int N = 1;
		for(String[] coment: livroDetalhado.getTodosOsComentarios()) {
			coment[0] = ""+N++;
			comentarioConcatenado += "\ncoment "+coment[0]+" / "+coment[1]+"\n"+coment[2];
		}
		try {
		dados = persistencia.recuperarCentral("Dados_Livraria.xml");
		persistencia.salvarCentral(dados, "Dados_Livraria.xml");
		}catch(Exception e) {
		}
		comentarios = new JTextArea(comentarioConcatenado);
		comentarios.setLineWrap(true);
		comentarios.setWrapStyleWord(true);
		rolo = new JScrollPane(comentarios);
		rolo.setBounds(30, 360, 400, 150);
		add(rolo);
		botao = new JButton("Adicionar comentário");
		botao.setBounds(30, 515, 180, 25);
		botao.addActionListener(new OuvinteBotaoAdicionarComentario(comentarioConcatenado));
		add(botao);
		botao = new JButton("Excluir");
		botao.setBounds(350, 515, 80, 25);
		botao.addActionListener(new OuvinteBotaoExcluirComentario(comentarioConcatenado));
		add(botao);
		botao = new JButton("Os livros chegaram!");
		botao.setBounds(475, 515, 150, 25);
		botao.addActionListener(new OuvinteBotaoAvisar());
		add(botao);
		modelo = new DefaultTableModel();
		modelo.addColumn("Lista de Interessados");
		for(int i = 0;i<livroDetalhado.getInteressados().size();i++) {
			modelo.addRow(new Object[] {livroDetalhado.getInteressados().get(i)});
		}
		tabela = new JTable(modelo){
			public boolean isCellEditable(int Linhas, int colunas)  {
				return false;
			}
		};
		tabela.addMouseListener(new OuvinteTable());
		rolo = new JScrollPane(tabela);
		rolo.setBounds(450, 360, 200, 150);
		add(rolo);
		JLabel preço = new JLabel("Preço: ");
		preço.setBounds(505, 10, 50, 15);
		add(preço);
		tfPreço = new JTextField(NumberFormat.getCurrencyInstance().format(livroDetalhado.getPreco()));
		tfPreço.setBounds(550, 10, 95, 25);
		tfPreço.setHorizontalAlignment(JTextField.CENTER);
		tfPreço.addKeyListener(new OuvintePreço());
		add(tfPreço);
		JLabel quantidade = new JLabel("Quantidade: ");
		quantidade.setBounds(475, 40, 75, 15);
		add(quantidade);
		tfQuantidade = new JTextField(""+livroDetalhado.getQuantidade());
		tfQuantidade.setBounds(550, 40, 95, 25);
		tfQuantidade.setHorizontalAlignment(JTextField.CENTER);
		tfQuantidade.addKeyListener(new OuvinteQuantidade());
		add(tfQuantidade);
	}
	public void Barra() {
		MenuOpcoes menu = new MenuOpcoes(this);
	}
	public Usuario getUsuarioLocal() {
		return usuarioLocal;
	}
	public void setUsuarioLocal(Usuario usuarioLocal) {
		this.usuarioLocal = usuarioLocal;
	}
	public Livro getLivroDetalhado() {
		return livroDetalhado;
	}
	public void setLivroDetalhado(Livro livroDetalhado) {
		this.livroDetalhado = livroDetalhado;
	}
	public JTextArea getComentarios() {
		return comentarios;
	}
	public void setComentarios(JTextArea comentarios) {
		this.comentarios = comentarios;
	}
}
