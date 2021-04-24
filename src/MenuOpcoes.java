import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuOpcoes {
	
	private JMenuItem interessados = new JMenuItem("Gerar Relatório: interessados");
	private JMenuItem listaDeLivros = new JMenuItem("Gerar Relatório: lista de livros");
	
	public MenuOpcoes(JFrame janela) {
		
		OuvinteMenu ouvinte = new OuvinteMenu();
		
		JMenuBar barraMenu = new JMenuBar();
		janela.setJMenuBar(barraMenu);
		
		JMenu opcoes = new JMenu("Opções");
		barraMenu.add(opcoes);
		
		opcoes.add(interessados);
		opcoes.add(listaDeLivros);
		
		interessados.addActionListener(ouvinte);
		listaDeLivros.addActionListener(ouvinte);
	}
	
	private class OuvinteMenu implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			String precionado = e.getActionCommand();
			
			switch(precionado) {
			
			case "Gerar Relatório: interessados":
				
				
			case "Gerar Relatório: lista de livros":
				
				
			}
		}
		
	}
	
}
