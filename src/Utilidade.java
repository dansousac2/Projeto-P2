import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
			
			CentralLivro central = perLivros.recuperarCentral("Dados_Livraria.xml");

			if(janela.getClass().getSimpleName().equals("ColecaoUsuario")) {
				
				for(Usuario user : central.getUsuariosCadastrados()) {
					if(user.getEmail().equals(logado.getEmail()) ) {
						return user.getColecaoDeLivros();
					}
				}
			}
			
			else {
				
				return central.getLivrosDisponiveis();
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Possíveis atributos foram atualizados. Delete a persistência!", "Coleção", JOptionPane.WARNING_MESSAGE);
		}
		return new ArrayList<Livro>(); // !!!
	}
	
	public static String[] generosDoTipo(String tipo) {
		
		switch(tipo) {
		
		case "Literatura":
			return new String[] {"literatura brasileira", "literatura estrangeira", "infanto juvenil", "artes", "biografias ou poesia"}; 
		
		case "Técnico":
			return new String[] {"paradidático ","formação profissional"};
		
		case "Periódico":
			return new String[] {"gibi ","revista de notícias"};
		
		case "Desenvolvimento Pessoal":
			return new String[] {"autoajuda","religião","saúde"};
			
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
