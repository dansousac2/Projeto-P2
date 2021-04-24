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

public class JanelaVisualizarDetalhesUsuario extends JanelaPadraoVisualizarDetalhes{
	Usuario usuarioLocal;
	Livro livroDetalhado;
	JTextArea comentarios;
	public class OuvinteBotaoSalvar implements ActionListener{
		Livro livroDetalhado;
		String[] atributos;
		JTextArea comentarios;
		public OuvinteBotaoSalvar(String[] S,Livro L, JTextArea C) {
			atributos = S;
			livroDetalhado = L;
			comentarios = C;
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
			repaint();
			JOptionPane.showMessageDialog(null, "As altera��es foram salvas");
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
			String T = JOptionPane.showInputDialog("Adicione um Coment�rio");
			if(T!=null) {
				livroDetalhado.comentarios += "\n  "+usuarioLocal.getNome()+":  "+T;
				caixaDeComentarios.setText(livroDetalhado.comentarios);
				repaint();
			}
			else {
				JOptionPane.showMessageDialog(null, "Nenhum coment�rio ser� adicionado", "Aten��o", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	public class OuvinteBotaoNotificar implements ActionListener{
		Livro livroDetalhado;
		Usuario usuarioLocal;
		public OuvinteBotaoNotificar(Usuario U,Livro L) {
			livroDetalhado = L;
			usuarioLocal = U;
		}
		public void actionPerformed(ActionEvent e) {
			livroDetalhado.getInteressados().add(usuarioLocal.getNome());
		}
	}
	public class OuvinteBotaoAddCole�ao implements ActionListener{
		Livro livroDetalhado;
		Usuario usuarioLocal;
		public OuvinteBotaoAddCole�ao(Usuario U,Livro L) {
			livroDetalhado = L;
			usuarioLocal = U;
		}
		public void actionPerformed(ActionEvent e) {
			usuarioLocal.getColecaoDeLivros().add(livroDetalhado);
		}
	}
	public JanelaVisualizarDetalhesUsuario(Usuario U,Livro L) {
		super();
		usuarioLocal = U;
		livroDetalhado = L;
		DetalhesDoLivro();
		Barra();
		Titulo();
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
		modelo.addRow(new Object[] {"Ano de Publica��o",atributos[3]});
		modelo.addRow(new Object[] {"Editora",atributos[4]});
		modelo.addRow(new Object[] {"Autores",atributos[5]});
		modelo.addRow(new Object[] {"Mes de Lan�amento",atributos[6]});
		modelo.addRow(new Object[] {"Numero de Edi��o",atributos[7]});
		modelo.addRow(new Object[] {"Assunto",atributos[8]});
		modelo.addRow(new Object[] {"Quantidade Disponivel",atributos[9]});
		modelo.addRow(new Object[] {"Nota M�dia",atributos[10]});
		tabela = new JTable(modelo);
		tabela.setEnabled(false);
		rolo = new JScrollPane(tabela);
		rolo.setBounds(30, 130, 400, 200);
		add(rolo);
		resumo = new JTextArea();
		resumo.setLineWrap(true);
		resumo.setWrapStyleWord(true);
		resumo.setText(atributos[11]);
		resumo.setEditable(false);
		rolo = new JScrollPane(resumo);
		rolo.setBounds(450, 130, 200, 200);
		add(rolo);
		comentarios = new JTextArea();
		comentarios.setLineWrap(true);
		comentarios.setWrapStyleWord(true);
		comentarios.setEditable(false);
		rolo = new JScrollPane(comentarios);
		rolo.setBounds(30, 360, 400, 150);
		add(rolo);
		botao = new JButton("Adicionar coment�rio");
		botao.setBounds(140, 515, 180, 25);
		botao.addActionListener(new OuvinteBotaoAdicionarComentario(usuarioLocal, livroDetalhado, comentarios));
		add(botao);
		botao = new JButton("Home");
		botao.setBounds(590, 20, 70, 70);
		add(botao);
		titulo = new JLabel("Unidades Restantes: "+livroDetalhado.getQuantidade());
		titulo.setBounds(438, 360, 240, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 14));
		add(titulo);
		botao = new JButton("Notificar-me quando chegar");
		botao.setBounds(438, 410, 240, 30);
		botao.addActionListener(new OuvinteBotaoNotificar(usuarioLocal, livroDetalhado));
		add(botao);
		botao = new JButton("Adicionar a cole��o");
		botao.setBounds(438, 460, 240, 30);
		botao.addActionListener(new OuvinteBotaoAddCole�ao(usuarioLocal, livroDetalhado));
		add(botao);
		botao = new JButton("COMPRAR");
		if(livroDetalhado.getQuantidade()==0) {
			botao.setEnabled(false);
		}
		botao.setBounds(455, 510, 200, 30);
		add(botao);
	}
	public void Barra() {
		menu = new JMenu("Menu");
		barra = new JMenuBar();
		barra.setBounds(0, 0, 700, 20);
		barra.add(menu);
		add(barra);
	}
	public static void main(String[] args) {
		try {
		CentralLivro C = new PersistenciaLivros().recuperarCentral("Dados_Livraria.xml");
		Usuario U = new Usuario("Nome", "email", "senha");
		new JanelaVisualizarDetalhesUsuario(U,C.getLivrosDisponiveis().get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}