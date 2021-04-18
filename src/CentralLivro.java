import java.util.ArrayList;

public class CentralLivro {
	
	private ArrayList<Livro> livrosDisponiveis = new ArrayList<Livro>();
	
	private ArrayList<Usuario> usuariosCadastrados = new ArrayList<Usuario>();

	public ArrayList<Livro> getLivrosDisponiveis() {
		return livrosDisponiveis;
	}

	public void setLivrosDisponiveis(ArrayList<Livro> livrosDisponiveis) {
		this.livrosDisponiveis = livrosDisponiveis;
	}

	public ArrayList<Usuario> getUsuariosCadastrados() {
		return usuariosCadastrados;
	}

	public void setUsuariosCadastrados(ArrayList<Usuario> usuariosCadastrados) {
		this.usuariosCadastrados = usuariosCadastrados;
	}
	
}
