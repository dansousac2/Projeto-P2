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

		CentralLivro centralInformacoes;
		
		try {
			if(janela.getClass().getSimpleName().equals("ColecaoUsuario")) {
			
				centralInformacoes = perLivros.recuperarCentral("Lista_Usuarios.xml");
				ArrayList<Usuario> buscarUsuario = centralInformacoes.getUsuariosCadastrados();
				for(Usuario user : buscarUsuario) {
					if(user == logado) {
						return user.getColecaoDeLivros();
					}
				}
			}
			
			else {
				centralInformacoes = perLivros.recuperarCentral("Livros_Cadastrados.xml");
				return centralInformacoes.getLivrosDisponiveis();
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível listar os livros", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		return new ArrayList<Livro>(); // !!!
	}
	
}
