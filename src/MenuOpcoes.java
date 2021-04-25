import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
			
			try {
				
				String precionado = e.getActionCommand();
				
				PersistenciaLivros persistencia = new PersistenciaLivros();
				
				CentralLivro central = persistencia.recuperarCentral("Dados_Livraria.xml");
				
				int resp = JOptionPane.showConfirmDialog(null, "Um arquivos PDF será gerado para " + precionado
						+ "\n. Prosseguir? ", "Relatório", JOptionPane.YES_NO_OPTION);
				
				switch(precionado) {
				
				case "Gerar Relatório: interessados":
					
					ArrayList<Livro> maisSolicitados = central.getLivrosDisponiveis();
					
					Collections.sort(maisSolicitados, new ComparadorLivros() {
						public int compare(Livro o1, Livro o2) {
							
							if(o1.getInteressados().size() < o2.getInteressados().size()) {
								return 1;
							}
							else if(o1.getInteressados().size() > o2.getInteressados().size()) {
								return -1;
							}
							else {
								return 0;
							}
						}
					});
					
					if(maisSolicitados.size() > 10) {
						maisSolicitados.remove(maisSolicitados.size() -1);
					}
					
					GeradorDeRelatorios.relatorioRequisitados(maisSolicitados);
					
					break;
					
				case "Gerar Relatório: lista de livros":
					
					if(resp == JOptionPane.YES_OPTION) {
						
						GeradorDeRelatorios.relatorioVisualizacoes(central.getLivrosDisponiveis());
					}	
				}
				
				JOptionPane.showMessageDialog(null, "Processo concluído", "Gerar Relatório", JOptionPane.INFORMATION_MESSAGE);
				
			}catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "Erro no processo de gerar relatórios", "Relatório/...", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
}
