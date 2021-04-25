import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
public class JanelaVisualizarDetalhesLivreiro extends JanelaPadraoVisualizarDetalhes{
	private Usuario usuarioLocal;
	private Livro livroDetalhado;
	private JTextArea comentarios;
	private String concat;
	public class OuvinteBotaoSalvar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			livroDetalhado.setGenero(""+modelo.getValueAt(1, 1));
			livroDetalhado.setIdioma(""+modelo.getValueAt(2, 1));
			livroDetalhado.setAnoPublicacao(Integer.parseInt(""+modelo.getValueAt(3, 1)));
			livroDetalhado.setEditora(""+modelo.getValueAt(4, 1));
			livroDetalhado.setAutores(""+modelo.getValueAt(5, 1));
			livroDetalhado.setMesLancamento(""+modelo.getValueAt(6, 1));
			livroDetalhado.setNumeroEdicao(Integer.parseInt(""+modelo.getValueAt(7, 1)));
			livroDetalhado.setAssunto(""+modelo.getValueAt(8, 1));
			livroDetalhado.setQuantidade(Integer.parseInt(""+modelo.getValueAt(9, 1)));
			livroDetalhado.setNotaMedia(Integer.parseInt(""+modelo.getValueAt(10, 1)));
			livroDetalhado.setResumo(""+modelo.getValueAt(11, 1));
			try {
				PersistenciaLivros persistencia = new PersistenciaLivros();
				CentralLivro dados = persistencia.recuperarCentral("Dados_Livraria.xml");
				for(int i = 0; i<dados.getLivrosDisponiveis().size();i++) {
					if(dados.getLivrosDisponiveis().get(i).getId() == livroDetalhado.getId()) {
						dados.getLivrosDisponiveis().remove(i);
						dados.getLivrosDisponiveis().add(livroDetalhado);
						JOptionPane.showMessageDialog(null, "As alterações foram salvas");
						break;
					}
				}
				persistencia.salvarCentral(dados, "Dados_Livraria.xml");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public class OuvinteBotaoAdicionarComentario implements ActionListener{
		JanelaVisualizarDetalhesLivreiro janela;
		public OuvinteBotaoAdicionarComentario(JanelaVisualizarDetalhesLivreiro J) {
			janela = J;
		}
		public void actionPerformed(ActionEvent e) {
			String T = JOptionPane.showInputDialog("Adicione um Comentário");
			if(!T.isBlank()) {
				try {
					int numero = livroDetalhado.getTodosOsComentarios().size()+1;
					String[] prepararComentario = {""+numero,usuarioLocal.getEmail(),T};
					PersistenciaLivros persistencia = new PersistenciaLivros();
					CentralLivro dados = persistencia.recuperarCentral("Dados_Livraria.xml");
					for(int i = 0;i<dados.getLivrosDisponiveis().size();i++) {
						if(dados.getLivrosDisponiveis().get(i).getId() == livroDetalhado.getId()) {
							livroDetalhado.getTodosOsComentarios().add(prepararComentario);
							comentarios.setText(concat);
							janela.repaint();
							break;
						}
					}
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
		botao.addActionListener(new OuvinteBotaoSalvar());
		add(botao);
		resumo = new JTextArea();
		resumo.setLineWrap(true);
		resumo.setWrapStyleWord(true);
		resumo.setText(atributos[11]);
		rolo = new JScrollPane(resumo);
		rolo.setBounds(450, 130, 200, 200);
		add(rolo);
		String comentarioConcatenado = "COMENTÁRIOS: \n";
		for(String[] coment: livroDetalhado.getTodosOsComentarios()) {
			int n = 1;
			comentarioConcatenado += "\ncoment "+coment[0]+" / "+coment[1]+"\n"+coment[2];
		}
		concat = comentarioConcatenado;
		comentarios = new JTextArea(comentarioConcatenado);
		comentarios.setLineWrap(true);
		comentarios.setWrapStyleWord(true);
		rolo = new JScrollPane(comentarios);
		rolo.setBounds(30, 360, 400, 150);
		add(rolo);
		botao = new JButton("Adicionar comentário");
		botao.setBounds(140, 515, 180, 25);
		botao.addActionListener(new OuvinteBotaoAdicionarComentario(this));
		add(botao);
		botao = new JButton("Home");
		botao.setBounds(590, 20, 70, 70);
		add(botao);
		modelo = new DefaultTableModel();
		modelo.addColumn("Lista de Interessados");
		for(int i = 0;i<livroDetalhado.getInteressados().size();i++) {
			modelo.addRow(new Object[] {livroDetalhado.getInteressados().get(i)});
		}
		tabela = new JTable(modelo);
		rolo = new JScrollPane(tabela);
		rolo.setBounds(450, 360, 200, 150);
		add(rolo);
	}
	public void Barra() {
		menu = new JMenu("Menu");
		barra = new JMenuBar();
		barra.setBounds(0, 0, 700, 20);
		barra.add(menu);
		add(barra);
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
