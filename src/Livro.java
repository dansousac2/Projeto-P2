import java.util.ArrayList;
import java.util.HashMap;

public class Livro {
	private String comentarios = "COMENTÁRIOS";
	private String tipo;
	private String genero;
	private String titulo;
	private String idioma;
	private int anoPublicacao;
	private String editora;
	private String resumo;
	private ArrayList<String> interessados = new ArrayList<String>();
	private String autores;
	private String mesLancamento;
	private int numeroEdicao;
	private String assunto;
	
	private int quantidade;
	private long id;
	
	private int notaMedia;
	private HashMap<String,Integer> notasAtribuidas;
	
	public Livro(String tipo,String genero,String titulo,String idioma,
			String editora,String resumo,int ano,int quantidade) {
		
		this.tipo = tipo;
		this.genero = genero;
		this.titulo = titulo;
		this.idioma = idioma;
		this.editora = editora;
		this.resumo = resumo;
		this.anoPublicacao = ano;
		this.quantidade = quantidade;
		this.id = System.currentTimeMillis();
	}

	public String getTipo() {
		return tipo;
	}

	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public long getId() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public int getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(int nota) {
		this.notaMedia = nota;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getMesLancamento() {
		return mesLancamento;
	}

	public void setMesLancamento(String mesLancamento) {
		this.mesLancamento = mesLancamento;
	}

	public int getNumeroEdicao() {
		return numeroEdicao;
	}

	public void setNumeroEdicao(int numeroEdicao) {
		this.numeroEdicao = numeroEdicao;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public HashMap<String, Integer> getNotasAtribuidas() {
		return notasAtribuidas;
	}

	public void setNotasAtribuidas(HashMap<String, Integer> notasAtribuidas) {
		this.notasAtribuidas = notasAtribuidas;
	}

	public ArrayList<String> getInteressados() {
		return interessados;
	}

	public void setInteressados(ArrayList<String> interessados) {
		this.interessados = interessados;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
}
