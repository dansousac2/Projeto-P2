import java.util.ArrayList;

public class Usuario {
	
	private String nome;
	private String email;
	private String senha;
	private boolean ehLivreiro = false;
	
	private ArrayList<Livro> colecaoDeLivros = new ArrayList<Livro>();

	public Usuario(String nome,String email,String senha) {
		
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<Livro> getColecaoDeLivros() {
		return colecaoDeLivros;
	}

	public boolean isEhLivreiro() {
		return ehLivreiro;
	}

	public void setEhLivreiro(boolean ehLivreiro) {
		this.ehLivreiro = ehLivreiro;
	}
	
	
}
