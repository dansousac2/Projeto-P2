import java.util.ArrayList;

import javax.swing.JOptionPane;

public class testeMain {
	
	public static void main(String[] args) {
		
		Usuario novoUsuario = new Usuario("Danilo S. Costa", "dansousac2@hotmail.com", "123qwe");

		new ListarLivrosUsuario(novoUsuario);
//	
//		new ListarLivrosLivreiro();
		
//		new ColecaoUsuario(novoUsuario);
	
		
		
		
//		CentralLivro centralTeste = new CentralLivro();
//		
//		ArrayList<Livro> arrayLivros = new ArrayList<Livro>();
//		
//		Livro livro01 = new Livro("literatura","gênero do tipo 1", "As peripércias de Leo", "mandarim", "Inova",
//				"qq coisa serve", 1991,3);
//		livro01.setNotaMedia(8);
//		Livro livro02 = new Livro("literatura","gênero do tipo 2", "Broncos e Troncos", "casteliano", "Inova",
//				"qq coisa serve", 1991,2);
//		livro02.setNotaMedia(9);
//		Livro livro03 = new Livro("tecnico","gênero do tipo 3", "As peripércias de Leo", "mandarim", "Inova",
//				"qq coisa serve", 1991,1);
//		livro03.setNotaMedia(10);
//		Livro livro04 = new Livro("desenvolvimento pessoal","gênero do tipo 4", "As peripércias de Leo", "mandarim", "Inova",
//				"qq coisa serve", 1991,8);
//		livro04.setNotaMedia(8);
//		
//		arrayLivros.add(livro01);
//		arrayLivros.add(livro02);
//		arrayLivros.add(livro03);
//		arrayLivros.add(livro04);
//		
//		centralTeste.setLivrosDisponiveis(arrayLivros);
//		
//		PersistenciaLivros persistencia = new PersistenciaLivros();
//		
//		try {
//			persistencia.salvarCentral(centralTeste, "Livros_Cadastrados.xml");
//			JOptionPane.showMessageDialog(null, "Salvo com sucesso");
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
		
		
	}
}
