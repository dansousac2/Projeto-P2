import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ColecaoUsuario extends PadraoListarLivros{

	public ColecaoUsuario(Usuario user) {
		super(user,false,Imagens.ICONE_COLECAO,"Minha Coleção");
		super.addIconeUsuario();
		super.addNomeIconeUsuario();
		
		addBotaoNota();
		
		add(new BotaoHomePage(125, 10,user,this));
		
		repaint();
	}

	private void addBotaoNota() {
		
		JButton botaoNota = new JButton("Atribuir Nota");
		botaoNota.setBounds(480, 494, 120, 25);
		
		add(botaoNota);
	}
	
	public class OuvinteBotaoNota implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			Integer[] notas = {10,9,8,7,6,5,4,3,2,1};
			
			Integer nota = (int)JOptionPane.showInternalInputDialog(null, "Selecione a nota: ", "Atribuir Nota",
					JOptionPane.INFORMATION_MESSAGE, null, notas, notas[0]);
			
			if(nota != null) {
				
				int linhaSelecionada = jtLivrosDisponiveis.getSelectedRow();
				
				long idSelecionado = (long)modelo.getValueAt(linhaSelecionada, 3);
				
				for(Livro liv : listarLivros) {
					if(idSelecionado == liv.getId()) {
						
						HashMap<String, Integer> hashNotas = liv.getNotasAtribuidas();
						String emailUsuario = usuarioLogado.getEmail();
						
						if(hashNotas.containsKey(emailUsuario)) {
							hashNotas.replace(emailUsuario, nota);
						}
						else {
							hashNotas.put(emailUsuario, nota);
						}
						
						int quantidadeDeVotos = hashNotas.size();
						int somaDosVotos = 0;
						
						Collection<Integer> colecao = hashNotas.values();
						Integer[] notasColecao = colecao.toArray(new Integer[0]);
						
						for(Integer n : notasColecao) {
							somaDosVotos += n;
						}
						
						liv.setNotaMedia(somaDosVotos/quantidadeDeVotos);
						
						jtLivrosDisponiveis.repaint();
						
						PersistenciaLivros persistencia = new PersistenciaLivros();
						
						try {
							
							CentralLivro central = persistencia.recuperarCentral("Dados_Livraria.xml");
							 
							ArrayList<Livro> listaLivros = central.getLivrosDisponiveis();
							
							for(Livro L : listaLivros) {
								if(L == liv) {
									L.setNotaMedia(liv.getNotaMedia());
									L.setNotasAtribuidas(hashNotas);
								}
								break;
							}
							
							persistencia.salvarCentral(central, "Dados_Livraria.xml");
							
						} catch(Exception erro) {
							JOptionPane.showInternalMessageDialog(null, "Erro em atualizar dados", "Salvar alterações",
									JOptionPane.ERROR_MESSAGE);
						}
					}
					
					break;
				}
				
			}
		}
		
	}
}
