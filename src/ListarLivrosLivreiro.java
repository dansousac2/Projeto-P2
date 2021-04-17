import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ListarLivrosLivreiro extends PadraoListarLivros{

	public ListarLivrosLivreiro() {
		super(null,true,Imagens.ICONE_LOGOMARCA,"Livraria Stile");
		
		addIconeLivreiro();
		addBotaoNovo();
		addNomeADM();
		
		repaint();
	}

	private void addIconeLivreiro() {

		JLabel iconeLivreiro = new JLabel(Imagens.ICONE_LIVREIRO);
		iconeLivreiro.setBounds(20, 10, 75, 75);
		
		add(iconeLivreiro);
	}
	
	private void addNomeADM() {
		
		JLabel nomeADM = new JLabel("ADM");
		nomeADM.setFont(new Font("Times New Roman", Font.BOLD, 14));
		nomeADM.setForeground(new Color(163, 73, 164));
		nomeADM.setHorizontalAlignment(JLabel.CENTER);
		nomeADM.setBounds(20, 75, 75, 20);
		
		add(nomeADM);
	}
	
	private void addBotaoNovo() {
		
		JButton botaoNovo = new JButton("Novo +");
		botaoNovo.setBounds(520, 494, 80, 25);
		
		OuvinteBotaoNovo ouvinte = new OuvinteBotaoNovo();
		botaoNovo.addActionListener(ouvinte);
		
		add(botaoNovo);
	}
	
	public class OuvinteBotaoNovo implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			// ABRE UMA JANELA SOBREPOSTA (SE POSS�VEL) PARA ADICIONAR UM LIVRO � COLE��O
		}
		
	}
	
}
