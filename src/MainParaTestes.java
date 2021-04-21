
public class MainParaTestes {

	public static void main(String[] args) {
		// Testando git
		Usuario user01 = new Usuario("Danilo", "dansousac2@gmail.com",
				"0090");
		user01.setEhLivreiro(false); // TESTANDO BOTÃO HOME PAGE
		
		new ListarLivrosUsuario(user01);
//		new ColecaoUsuario(user01);
		
	}
}
