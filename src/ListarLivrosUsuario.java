import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ListarLivrosUsuario extends PadraoListarLivros{
	
	public ListarLivrosUsuario(Usuario usuario) {
		super(usuario,false,Imagens.ICONE_LOGOMARCA,"Livraria Stile");
		
		super.addIconeUsuario();
		super.addNomeIconeUsuario();
		super.addIconeCentral();
		super.addNomeIconeCentral();

		addBotaoColecao();
		
		repaint();
	}
	
	private void addBotaoColecao() {
		
		JButton btColecao = new JButton(Imagens.ICONE_COLECAO_ACESSO);
		btColecao.setBounds(130, 10, 43, 43);
		
		OuvinteColecao ouvinte = new OuvinteColecao();
		btColecao.addActionListener(ouvinte);
		
		add(btColecao);
	}
	
	public class OuvinteColecao implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			dispose();
			new ColecaoUsuario(usuarioLogado);
		}
		
	}
}
