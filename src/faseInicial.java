import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;

public class faseInicial {

	public static void main(String[] args){
		XStream xstream = new XStream();
		ArrayList<Usuario> BancoDeDadosUsuarios = new ArrayList<Usuario>();
		File arquivo = new File("Cadastro de Usuários Interno");
		if(arquivo.exists()) {
			try {
				FileInputStream Recuperar = new FileInputStream(arquivo);
				BancoDeDadosUsuarios = (ArrayList<Usuario>) xstream.fromXML(Recuperar);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		if(BancoDeDadosUsuarios.isEmpty()) {
			new JanelaCadastrarLivreiro(BancoDeDadosUsuarios);
		}
		else {
			new JanelaLoguin(BancoDeDadosUsuarios);
		}
	}
}
