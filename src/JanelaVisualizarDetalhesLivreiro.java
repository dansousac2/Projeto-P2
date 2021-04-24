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
	Usuario usuarioLocal;
	Livro livroDetalhado;
	JTextArea comentarios;
	public class OuvinteBotaoSalvar implements ActionListener{
		String[] atributos;
		public OuvinteBotaoSalvar(String[] S,Livro L, JTextArea C) {
			atributos = S;
		}
		public void actionPerformed(ActionEvent e) {
			livroDetalhado.setTitulo(atributos[0]);
			livroDetalhado.setGenero(atributos[1]);
			livroDetalhado.setIdioma(atributos[2]);
			livroDetalhado.setAnoPublicacao(Integer.parseInt(atributos[3]));
			livroDetalhado.setEditora(atributos[4]);
			livroDetalhado.setAutores(atributos[5]);
			livroDetalhado.setMesLancamento(atributos[6]);
			livroDetalhado.setNumeroEdicao(Integer.parseInt(atributos[7]));
			livroDetalhado.setAssunto(atributos[8]);
			livroDetalhado.setQuantidade(Integer.parseInt(atributos[9]));
			livroDetalhado.setNotaMedia(Integer.parseInt(atributos[10]));
			livroDetalhado.setResumo(atributos[11]);
			livroDetalhado.comentarios = comentarios.getText();
			try {
				PersistenciaLivros persistencia = new PersistenciaLivros();
				CentralLivro dados = persistencia.recuperarCentral("Dados_Livraria.xml");
				for(int i = 0; i<dados.getLivrosDisponiveis().size();i++) {
					if(dados.getLivrosDisponiveis().get(i) == livroDetalhado) {
						dados.getLivrosDisponiveis().remove(i);
						dados.getLivrosDisponiveis().add(livroDetalhado);
						break;
					}
				}
				persistencia.salvarCentral(dados, "Dados_Livraria.xml");
				JOptionPane.showMessageDialog(null, "As alterações foram salvas");
				repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public class OuvinteBotaoAdicionarComentario implements ActionListener{
		Livro livroDetalhado;
		Usuario usuarioLocal;
		JTextArea caixaDeComentarios;
		public OuvinteBotaoAdicionarComentario(Usuario U,Livro L, JTextArea T) {
			livroDetalhado = L;
			usuarioLocal = U;
			caixaDeComentarios = T;
		}
		public void actionPerformed(ActionEvent e) {
			String T = JOptionPane.showInputDialog("Adicione um Comentário");
			if(T!=null) {
				livroDetalhado.comentarios += "\n  "+usuarioLocal.getNome()+":  "+T;
				caixaDeComentarios.setText(livroDetalhado.comentarios);
				repaint();
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
		String[] atributos = {livroDetalhado.getTitulo(),livroDetalhado.getGenero(),livroDetalhado.getIdioma(),
		""+livroDetalhado.getAnoPublicacao(),livroDetalhado.getEditora(),livroDetalhado.getAutores(),livroDetalhado.getMesLancamento(),
		""+livroDetalhado.getNumeroEdicao(),livroDetalhado.getAssunto(),""+livroDetalhado.getQuantidade(),""+livroDetalhado.getNotaMedia(),
		livroDetalhado.getResumo()};
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
		botao.addActionListener(new OuvinteBotaoSalvar(atributos, livroDetalhado, comentarios));
		add(botao);
		resumo = new JTextArea();
		resumo.setLineWrap(true);
		resumo.setWrapStyleWord(true);
		resumo.setText(atributos[11]);
		rolo = new JScrollPane(resumo);
		rolo.setBounds(450, 130, 200, 200);
		add(rolo);
		comentarios = new JTextArea();
		comentarios.setLineWrap(true);
		comentarios.setWrapStyleWord(true);
		rolo = new JScrollPane(comentarios);
		rolo.setBounds(30, 360, 400, 150);
		add(rolo);
		botao = new JButton("Adicionar comentário");
		botao.setBounds(140, 515, 180, 25);
		botao.addActionListener(new OuvinteBotaoAdicionarComentario(usuarioLocal, livroDetalhado, comentarios));
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
}
