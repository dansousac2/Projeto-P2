import javax.swing.JOptionPane;

public class MainParaTestes {

	public static void main(String[] args) {
		
		Usuario user01 = new Usuario("Danilo", "dansousac2@gmail.com",
				"0090");
		
		new ListarLivrosUsuario(user01);
//		new ColecaoUsuario(user01);
		
	}
}
