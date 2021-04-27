
public class testeMain {
	
	public static void main(String[] args) {
		
		Usuario novoUsuario = new Usuario("Danilo S. Costa", "dansousac2@hotmail.com", "123qwe");
		
		new ListarLivrosUsuario(novoUsuario);
		
//		novoUsuario.setEhLivreiro(true);
//		new ListarLivrosLivreiro(novoUsuario);

//		new ColecaoUsuario(novoUsuario);
		
//		try {	
//			new JanelaLoguin(new PersistenciaLivros().recuperarCentral("Dados_Livraria.xml"));
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
	}
}
