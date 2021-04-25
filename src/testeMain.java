
public class testeMain {
	
	public static void main(String[] args) {
		
		Usuario novoUsuario = new Usuario("Danilo S. Costa", "dansousac2@hotmail.com", "123qwe");
		novoUsuario.setEhLivreiro(true);
//		new ListarLivrosUsuario(novoUsuario);

		new ListarLivrosLivreiro(novoUsuario);
		
//		new ColecaoUsuario(novoUsuario);	

	}
}
