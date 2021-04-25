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
	String C;
	public class OuvinteBotaoAdicionarComentario implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String T = JOptionPane.showInputDialog("Adicione um Coment�rio");
			if(!T.isBlank()) {
				try {
					PersistenciaLivros persistencia = new PersistenciaLivros();
					CentralLivro dados = persistencia.recuperarCentral("Dados_Livraria.xml");
					for(int i = 0;i<dados.getLivrosDisponiveis().size();i++) {
						if(dados.getLivrosDisponiveis().get(i).getId() == livroDetalhado.getId()) {
							C += "\n"+usuarioLocal.getNome()+":  "+T;
							livroDetalhado.setComentarios(C);
							dados.getLivrosDisponiveis().remove(i);
							dados.getLivrosDisponiveis().add(livroDetalhado);
							break;
						}
					}
					persistencia.salvarCentral(dados, "Dados_Livraria.xml");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			comentarios.setText(C);
			}
			else {
				JOptionPane.showMessageDialog(null, "Nenhum coment�rio ser� adicionado", "Aten��o", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	public class OuvinteBotaoNotificar implements ActionListener{
		public OuvinteBotaoNotificar() {
		}
		public void actionPerformed(ActionEvent e) {
			try {
				PersistenciaLivros persistencia = new PersistenciaLivros();
				CentralLivro dados = persistencia.recuperarCentral("Dados_Livraria.xml");
				Boolean existe = false;
				for(int n = 0;n<livroDetalhado.getInteressados().size();n++) {
					if(usuarioLocal.getEmail().equals(livroDetalhado.getInteressados().get(n))) {
						existe = true;
						break;
					}
				}
				if(existe == true) {
					JOptionPane.showMessageDialog(null, "Voc� j� foi adicionado a lista de interessados!");
				}
				else {
					for(int i = 0;i<dados.getLivrosDisponiveis().size();i++) {
						if(dados.getLivrosDisponiveis().get(i).getId() == livroDetalhado.getId()) {
							livroDetalhado.getInteressados().add(usuarioLocal.getEmail());
							dados.getLivrosDisponiveis().remove(i);
							dados.getLivrosDisponiveis().add(livroDetalhado);
							JOptionPane.showMessageDialog(null, "Voc� ser� notificado assim que haver estoque deste livro!");
							break;
						}
					}
				}
			persistencia.salvarCentral(dados, "Dados_Livraria.xml");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public class OuvinteBotaoAddCole�ao implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				PersistenciaLivros persistencia = new PersistenciaLivros();
				CentralLivro dados = persistencia.recuperarCentral("Dados_Livraria.xml");
				Boolean existe = null;
				for(int n = 0;n<usuarioLocal.getColecaoDeLivros().size();n++) {
					if(usuarioLocal.getColecaoDeLivros().get(n).getId() == livroDetalhado.getId()) {
						existe = true;
						break;
					}
				}
				if(existe == true) {
					JOptionPane.showMessageDialog(null, "Voc� j� possue este livro em sua cole��o!");
				}
				else {
					for(int i = 0;i<dados.getUsuariosCadastrados().size();i++) {
						if(dados.getUsuariosCadastrados().get(i).getEmail().equals(usuarioLocal.getEmail())) {
							usuarioLocal.getColecaoDeLivros().add(livroDetalhado);
							dados.getUsuariosCadastrados().remove(i);
							dados.getUsuariosCadastrados().add(usuarioLocal);
							JOptionPane.showMessageDialog(null, "O livro foi adicionado a sua cole��o!");
							break;
						}
					}
				}
				persistencia.salvarCentral(dados, "Dados_Livraria.xml");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public JanelaVisualizarDetalhesUsuario(Usuario U,Livro L) {
		super();
		usuarioLocal = U;
		livroDetalhado = L;
		C = livroDetalhado.getComentarios();
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
		comentarios = new JTextArea(C);
		comentarios.setLineWrap(true);
		comentarios.setWrapStyleWord(true);
		comentarios.setEditable(false);
		rolo = new JScrollPane(comentarios);
		rolo.setBounds(30, 360, 400, 150);
		add(rolo);
		botao = new JButton("Adicionar coment�rio");
		botao.setBounds(140, 515, 180, 25);
		botao.addActionListener(new OuvinteBotaoAdicionarComentario());
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
		if(livroDetalhado.getQuantidade()>0) {
			botao.setEnabled(false);
		}
		botao.addActionListener(new OuvinteBotaoNotificar());
		add(botao);
		botao = new JButton("Adicionar a cole��o");
		botao.setBounds(438, 460, 240, 30);
		botao.addActionListener(new OuvinteBotaoAddCole�ao());
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
}
