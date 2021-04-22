import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;

public class faseInicial {

	public static void main(String[] args){
		
		PersistenciaLivros persistencia = new PersistenciaLivros();
		try {
			CentralLivro central = persistencia.recuperarCentral("Dados_Livraria.xml");
			
		if(central.getUsuariosCadastrados().isEmpty()) {
			new JanelaCadastrarLivreiro(central);
		}
		else {
			new JanelaLoguin(central);
		}
				
		} catch(Exception ex) {
			System.out.println(ex.getStackTrace());
		}
		
	}
}
