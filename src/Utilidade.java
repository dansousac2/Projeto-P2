import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Utilidade {

	public static String[] principaisAtributosDosLivros(Livro liv) {
		
		String[] atributos = new String[6];
		
		Object[] objetosAtributos = {liv.getTipo(),liv.getTitulo(),liv.getResumo(),liv.getId(),
				liv.getNotaMedia(),liv.getQuantidade() };
		
		for(int i = 0; i < 6; i++) {
			atributos[i] = objetosAtributos[i].toString();
		}
		return atributos;
	}
	
	public static void adicionarLinhasAtualizadas(Livro liv,DefaultTableModel mod) {
		String[] atributos = principaisAtributosDosLivros(liv);
		String[] linha = new String[mod.getColumnCount()];
		
		for(int i = 0; i < mod.getColumnCount(); i++) {
			linha[i] = atributos[i];
		}
		mod.addRow(linha);
	}
	
	public static ArrayList<Livro> recuperarLivrosCabiveis(PadraoListarLivros janela,Usuario logado) {
		
		PersistenciaLivros perLivros = new PersistenciaLivros();
		
		try {
			
			CentralLivro centralInformacoes = perLivros.recuperarCentral("Dados_Livraria.xml");

			if(janela.getClass().getSimpleName().equals("ColecaoUsuario")) {
				
				ArrayList<Usuario> buscarUsuario = centralInformacoes.getUsuariosCadastrados();
				for(Usuario user : buscarUsuario) {
					if(user == logado) {
						return user.getColecaoDeLivros();
					}
				}
			}
			
			else {
				
				return centralInformacoes.getLivrosDisponiveis();
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ainda n�o h� livros cadastrados em sua cole��o.", "Cole��o", JOptionPane.WARNING_MESSAGE);
		}
		return new ArrayList<Livro>(); // !!!
	}
	
	public static String[] generosDoTipo(String tipo) {
		
		switch(tipo) {
		
		case "Literatura":
			return new String[] {"literatura brasileira", "literatura estrangeira", "infanto juvenil", "artes", "biografias ou poesia"}; 
		
		case "T�cnico":
			return new String[] {"paradid�tico ","forma��o profissional"};
		
		case "Peri�dico":
			return new String[] {"gibi ","revista de not�cias"};
		
		case "Desenvolvimento Pessoal":
			return new String[] {"autoajuda","religi�o","sa�de"};
			
		default:
			return new String[0];
		}
	}

	public static boolean ehNumero(String str) {
		try {
			Double.parseDouble(str);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
