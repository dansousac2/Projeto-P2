import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

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
	private Usuario usuarioLocal;
	private Livro livroDetalhado;
	private JTextArea comentarios;
	private String concat;
	private PersistenciaLivros persistencia = new PersistenciaLivros();
	private CentralLivro dados = new CentralLivro();
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
					for(int i = 0;i<dados.getLivrosDisponiveis().size();i++) {
						if(dados.getLivrosDisponiveis().get(i).getId() == livroDetalhado.getId()) {
							livroDetalhado.getTodosOsComentarios().add(prepararComentario);
							break;
						}
					}
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
	public class OuvinteBotaoNotificar implements ActionListener{
		public OuvinteBotaoNotificar() {
		}
		public void actionPerformed(ActionEvent e) {
			try {
				Boolean existe = false;
				for(int n = 0;n<livroDetalhado.getInteressados().size();n++) {
					if(usuarioLocal.getEmail().equals(livroDetalhado.getInteressados().get(n))) {
						existe = true;
						break;
					}
				}
				if(existe == true) {
					JOptionPane.showMessageDialog(null, "Você já foi adicionado a lista de interessados!");
				}
				else {
					livroDetalhado.getInteressados().add(usuarioLocal.getEmail());
					JOptionPane.showMessageDialog(null, "Você será notificado assim que haver estoque deste livro!");
					for(int i = 0; i<dados.getLivrosDisponiveis().size();i++) {
						if(dados.getLivrosDisponiveis().get(i).getId() == livroDetalhado.getId()) {
							dados.getLivrosDisponiveis().remove(i);
							dados.getLivrosDisponiveis().add(livroDetalhado);
							JOptionPane.showMessageDialog(null, "Você será notificado assim que haver estoque deste livro!");
							persistencia.salvarCentral(dados, "Dados_Livraria.xml");
							break;
							}
						}
				}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
	}
	public class OuvinteBotaoAddColeçao implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				Boolean existe = false;
				for(int n = 0;n<usuarioLocal.getColecaoDeLivros().size();n++) {
					if(usuarioLocal.getColecaoDeLivros().get(n).getId() == livroDetalhado.getId()) {
						existe = true;
						break;
					}
				}
				if(existe == true) {
					JOptionPane.showMessageDialog(null, "Você já possue este livro em sua coleção!");
				}
				else {
					usuarioLocal.getColecaoDeLivros().add(livroDetalhado);
					JOptionPane.showMessageDialog(null, "O livro foi adicionado a sua coleção!");
				}
				persistencia.salvarCentral(dados, "Dados_Livraria.xml");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
				if(livroDetalhado.getTodosOsComentarios().get(numero-1)[1].equals(usuarioLocal.getEmail())) {
					PersistenciaLivros persistencia = new PersistenciaLivros();
					CentralLivro dados = persistencia.recuperarCentral("Dados_Livraria.xml");
					livroDetalhado.getTodosOsComentarios().remove(numero-1);
					Com = "COMENTÁRIOS\n";
					int N = 1;
					for(String[] coment: livroDetalhado.getTodosOsComentarios()) {
						coment[0] = ""+N++;
						Com += "\ncoment "+coment[0]+" / "+coment[1]+"\n"+coment[2];
					}
					comentarios.setText(Com);
					persistencia.salvarCentral(dados, "Dados_Livraria.xml");
				}
				else {
					JOptionPane.showMessageDialog(null, "O comentário selecionado não lhe pertençe", "Tente novamente", JOptionPane.ERROR_MESSAGE);
				}
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Só é permitido números no campo de texto", "Campo Invalido", JOptionPane.ERROR_MESSAGE);
			}
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
		modelo.addRow(new Object[] {"Ano de Publicação",atributos[3]});
		modelo.addRow(new Object[] {"Editora",atributos[4]});
		modelo.addRow(new Object[] {"Autores",atributos[5]});
		modelo.addRow(new Object[] {"Mes de Lançamento",atributos[6]});
		modelo.addRow(new Object[] {"Numero de Edição",atributos[7]});
		modelo.addRow(new Object[] {"Assunto",atributos[8]});
		modelo.addRow(new Object[] {"Quantidade Disponivel",atributos[9]});
		modelo.addRow(new Object[] {"Nota Média",atributos[10]});
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
		String comentarioConcatenado = "COMENTÁRIOS: \n";
		int N = 1;
		for(String[] coment: livroDetalhado.getTodosOsComentarios()) {
			coment[0] = ""+N++;
			comentarioConcatenado += "\ncoment "+coment[0]+" / "+coment[1]+"\n"+coment[2];
		}
		concat = comentarioConcatenado;
		comentarios = new JTextArea(comentarioConcatenado);
		comentarios.setLineWrap(true);
		comentarios.setWrapStyleWord(true);
		comentarios.setEditable(false);
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
		titulo = new JLabel("Unidades Restantes: "+livroDetalhado.getQuantidade());
		titulo.setBounds(438, 355, 240, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 14));
		add(titulo);
		titulo = new JLabel("Preço: "+NumberFormat.getCurrencyInstance().format(livroDetalhado.getPreco()));
		titulo.setBounds(438, 377, 150, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 14));
		add(titulo);
		botao = new JButton("Notificar-me quando chegar");
		botao.setBounds(438, 410, 240, 30);
		if(livroDetalhado.getQuantidade()>0) {
			botao.setEnabled(false);
		}
		botao.addActionListener(new OuvinteBotaoNotificar());
		add(botao);
		botao = new JButton("Adicionar a coleção");
		botao.setBounds(438, 460, 240, 30);
		botao.addActionListener(new OuvinteBotaoAddColeçao());
		add(botao);
		
		botao = new JButton("COMPRAR");
		if(livroDetalhado.getQuantidade()==0) {
			botao.setEnabled(false);
		}
		botao.setBounds(455, 510, 200, 30);
		botao.addActionListener(new OuvinteBotaoComprar());
		add(botao);
	}
	public void Barra() {
		menu = new JMenu("Menu");
		barra = new JMenuBar();
		barra.setBounds(0, 0, 700, 20);
		barra.add(menu);
		add(barra);
	}
	
	public class OuvinteBotaoComprar implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			int comp = JOptionPane.showConfirmDialog(null, "Confirmar compra deste produto?", "Comprar", JOptionPane.YES_NO_OPTION);
			
			if(comp == JOptionPane.YES_OPTION) {
				
				GeradorDeRelatorios relatorios = new GeradorDeRelatorios();
				relatorios.boletoDeCobranca(livroDetalhado);
				
				PersistenciaLivros persistencia = new PersistenciaLivros();
				
				try {
					
					CentralLivro central = persistencia.recuperarCentral("Dados_Livraria.xml"); 
					
					for(Usuario user : central.getUsuariosCadastrados()) {
						if(user.getEmail().equals(usuarioLocal.getEmail())){
							user.getColecaoDeLivros().add(livroDetalhado);
						}
					}
					
					persistencia.salvarCentral(central, "Dados_Livraria.xml");
					
					JOptionPane.showMessageDialog(null, "Processo concluído");
					
				}catch (Exception erro) {
					JOptionPane.showConfirmDialog(null, "Erro ao salva/recuperar", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
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
