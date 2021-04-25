import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BotaoHomePage extends JButton{
	
	private Usuario usuario;
	private PadraoListarLivros janela;
	
	public BotaoHomePage(int posiX,int posiY,Usuario user,PadraoListarLivros janela) {
		
		this.janela = janela;
		this.usuario = user;
		
		this.setIcon(Imagens.ICONE_HOMEPAGE);
		this.setBounds(posiX, posiY, 45, 45);
		
		OuvinteHomePage ouvinte = new OuvinteHomePage();
		addActionListener(ouvinte);
		
	}
	
	public class OuvinteHomePage implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(usuario.isEhLivreiro()) {
				
				new ListarLivrosLivreiro(usuario);
			}
			else {
				new ListarLivrosUsuario(usuario);
			}
			
			janela.dispose();
		}
		
	}
}
