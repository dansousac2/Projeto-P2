import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	Livro livroDetalhado;
	Usuario usuarioLogado;
	String[] atributos = {"titulo","genero","idioma","ano","editora",
			"autores","mes","numero","assunto","quantidade","nota"};
	public class OuvinteBotaoSalvar implements ActionListener{
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
			repaint();
			JOptionPane.showMessageDialog(null, "As alterações foram salvas");
		}
		
	}
	public JanelaVisualizarDetalhesLivreiro(Livro L, Usuario U) {
		super();
		DetalhesDoLivro();
		TabelaDeComentarios();
		ListaDeInteressados();
		Resumo();
		Botoes();
		Barra();
		Titulo();
		livroDetalhado = L;
		usuarioLogado = U;
		setVisible(true);
	}
	public void Titulo() {
		titulo = new JLabel("Visualizar Detalhes");
		titulo.setBounds(230, 20, 250, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 22));
		add(titulo);
	}
	public void DetalhesDoLivro() {
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
	}
	public void TabelaDeComentarios() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Altor");
		modelo.addColumn("Comentários");
		tabela = new JTable(modelo);
		rolo = new JScrollPane(tabela);
		rolo.setBounds(30, 360, 400, 150);
		add(rolo);
	}
	public void ListaDeInteressados() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Lista de Interessados");
		tabela = new JTable(modelo);
		rolo = new JScrollPane(tabela);
		rolo.setBounds(450, 360, 200, 150);
		add(rolo);
	}
	public void Resumo() {
		resumo = new JTextArea();
		rolo = new JScrollPane(resumo);
		rolo.setBounds(450, 130, 200, 200);
		add(rolo);
	}
	public void Botoes() {
		botao = new JButton("Excluir comentário selecionado");
		botao.setBounds(30, 515, 215, 25);
		add(botao);
		botao = new JButton("Adicionar comentário");
		botao.setBounds(250, 515, 180, 25);
		add(botao);
		botao = new JButton("Salvar");
		botao.setBounds(190, 332, 80, 25);
		botao.addActionListener(new OuvinteBotaoSalvar());
		add(botao);
		botao = new JButton("Home");
		botao.setBounds(590, 20, 70, 70);
		add(botao);
	}
	public void Barra() {
		menu = new JMenu("Menu");
		barra = new JMenuBar();
		barra.setBounds(0, 0, 700, 20);
		barra.add(menu);
		add(barra);
	}
}
