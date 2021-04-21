import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;

public class faseInicial {

	public static void main(String[] args){
		
		ArrayList<Usuario> BancoDeDadosUsuarios = new ArrayList<Usuario>();
		
		PersistenciaLivros persistencia = new PersistenciaLivros();
		
		try {
			
			CentralLivro central = persistencia.recuperarCentral("Dados_Livraria.xml");
			
			BancoDeDadosUsuarios = central.getUsuariosCadastrados();
			
		if(BancoDeDadosUsuarios.isEmpty()) {
			new JanelaCadastrarLivreiro(BancoDeDadosUsuarios);
			System.out.println("banco de dados vazio");
		}
		else {
			new JanelaLoguin(BancoDeDadosUsuarios);
			System.out.println("banco de dadods preenchido");
		}
				
		} catch(Exception ex) {
			System.out.println(ex.getStackTrace());
		}
		
	}
}
