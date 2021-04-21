import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PersistenciaLivros {
	
	private XStream xStream = new XStream(new DomDriver("ISO-8859-1"));
	
	public void salvarCentral(CentralLivro central,String nomeArquivo) throws Exception {
		
		File arquivo = new File(nomeArquivo);				
		arquivo.createNewFile();
		
		PrintWriter pw = new PrintWriter(arquivo);
		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
		xml += xStream.toXML(central);
		pw.print(xml);
		pw.close();
	}
	
	public CentralLivro recuperarCentral (String nomeArquivo) throws Exception{
		
		File arquivo = new File(nomeArquivo);
		
		if (arquivo.exists()) {
			FileInputStream fis = new FileInputStream(arquivo);
			return (CentralLivro) xStream.fromXML(fis);
		}
		else {
			JOptionPane.showMessageDialog(null, "Uma nova lista de livros foi criada.\n");
			return new CentralLivro();
		}
	}
}
